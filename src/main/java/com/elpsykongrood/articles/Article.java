package com.elpsykongrood.articles;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.Instant;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Entity
@DynamicUpdate
@Table(name = "articles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    private String slug;

    @Column(nullable = false, columnDefinition = "text")
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    @Column(nullable = false)
    private Instant date;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    private long views;

    @Column(columnDefinition = "text")
    private String excerpt;

    public Article increaseViews() {
        this.views += 1;
        return this;
    }

}
