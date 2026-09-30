package com.elpsykongrood.articles;

import com.elpsykongrood.util.MarkdownUtils;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<ArticlePayloads.Item> findRecentArticles(int limit) {
        limit = limit > 0 ? limit : 5;
        return articleRepository.findRecentArticles(Limit.of(limit));
    }

    public Page<ArticlePayloads.Item> findArticles(String query, int page, String sort) {
        var direction = "asc".equals(sort) ? Sort.Direction.ASC : Sort.Direction.DESC;
        var pageable = PageRequest.of(
                    Math.max(page, 1) - 1,
                    10,
                    Sort.by(new Sort.Order(direction, "date"), Sort.Order.asc("slug")));

        return articleRepository.findByTitleContainingIgnoreCase(query.strip(), pageable);
    }

    @Transactional
    public ArticlePayloads.Detail findBySlug(String slug) {
        Article article = articleRepository.findById(slug)
                            .map(Article::increaseViews)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ArticlePayloads.Detail.from(
            article,
            MarkdownUtils.toHtml(article.getContent())
        );
    }

    public List<ArticlePayloads.Location> findArticleLocations() {
        return articleRepository.findArticleLocations();
    }
}
