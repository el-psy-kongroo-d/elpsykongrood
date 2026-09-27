package com.elpsykongrood.projects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "projects")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {

    @Id
    private String slug;

    @Column(nullable = false, columnDefinition = "text")
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text")
    private String kind;

    @Column(name = "website_url", columnDefinition = "text")
    private String websiteUrl;

    @Column(name = "portfolio_url", columnDefinition = "text")
    private String portfolioUrl;

    @Column(name = "preview_image", columnDefinition = "text")
    private String previewImage;

    @Column(name = "display_order", nullable = false)
    private int displayOrder;

}
