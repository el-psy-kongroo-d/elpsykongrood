package com.elpsykongrood.comments;

import com.elpsykongrood.articles.ArticleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final ArticleRepository articles;
    private final CommentRepository comments;

    @Transactional(readOnly = true)
    public List<CommentPayloads.Item> findBySlug(String slug) {
        return comments.findCommentsByArticleSlug(slug);
    }

    @Transactional
    public void create(String slug, CommentPayloads.Create form) {
        var article = articles.findById(slug).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        comments.save(Comment.builder()
                .article(article)
                .nickname(form.nickname())
                .content(form.content())
                .build());
    }
}
