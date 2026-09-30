package com.elpsykongrood.articles;

import java.util.List;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<Article, String> {

    @Query("""
            select a.slug, a.title, a.excerpt, a.date, a.updatedAt, a.views
            from Article a
            order by a.date desc, a.slug
            """)
    List<ArticlePayloads.Item> findRecentArticles(Limit limit);

    Page<ArticlePayloads.Item> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    @Query("""
            select a.slug, a.updatedAt
            from Article a
            order by a.slug
            """)
    List<ArticlePayloads.Location> findArticleLocations();

}
