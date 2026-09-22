package com.elpsykongrood.comments;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("""
            select c.id, c.nickname, c.content, c.createdAt
            from Comment c
            where c.article.slug = :slug
            order by c.createdAt, c.id
            """)
    List<CommentPayloads.Item> findCommentsByArticleSlug(@Param("slug") String slug);

}
