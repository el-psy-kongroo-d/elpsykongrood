package com.elpsykongrood.comments;

import com.elpsykongrood.articles.Article;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "article_slug", nullable = false)
    private Article article;

    @Column(nullable = false, columnDefinition = "text")
    private String nickname;

    @Column(name = "body", nullable = false, columnDefinition = "text")
    private String content;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Builder
    public Comment(Article article, String nickname, String content) {
        this.article = article;
        this.nickname = nickname;
        this.content = content;
        this.createdAt = Instant.now();
    }

}
