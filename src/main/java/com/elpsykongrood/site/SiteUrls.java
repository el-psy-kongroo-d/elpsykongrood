package com.elpsykongrood.site;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class SiteUrls {

    private final String siteUrl;

    public SiteUrls(@Value("${app.site-url}") String siteUrl) {
        this.siteUrl = siteUrl.replaceAll("/+$", "");
    }

    public String url(String path) {
        return siteUrl + path;
    }

    public String article(String slug) {
        return UriComponentsBuilder.fromUriString(siteUrl)
                .pathSegment("articles", slug)
                .build().encode().toUriString();
    }
}
