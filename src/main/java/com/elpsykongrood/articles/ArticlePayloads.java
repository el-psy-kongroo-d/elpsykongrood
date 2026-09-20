package com.elpsykongrood.articles;

import java.time.Instant;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ArticlePayloads {

    @Builder
    public record Item(
            String slug,
            String title,
            String excerpt,
            Instant date,
            Instant updatedAt,
            long views
    ) {}

    @Builder
    public record Detail(
            String slug,
            String title,
            String excerpt,
            String content,
            Instant date,
            Instant updatedAt,
            long views
    ) {
        static Detail from(Article article, String content) {
            return Detail.builder()
                    .slug(article.getSlug())
                    .title(article.getTitle())
                    .excerpt(article.getExcerpt())
                    .content(content)
                    .date(article.getDate())
                    .updatedAt(article.getUpdatedAt())
                    .views(article.getViews())
                    .build();
        }
    }
}
