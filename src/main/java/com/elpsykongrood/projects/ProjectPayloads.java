package com.elpsykongrood.projects;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProjectPayloads {

    @Builder
    public record Item(
            String slug,
            String title,
            String description,
            String kind,
            String websiteUrl,
            String portfolioUrl,
            String previewImage
    ) {}
}
