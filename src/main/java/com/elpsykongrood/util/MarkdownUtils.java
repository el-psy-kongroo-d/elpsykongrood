package com.elpsykongrood.util;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MarkdownUtils {

    private static final Parser PARSER = Parser.builder()
            .extensions(List.of(TablesExtension.create()))
            .build();

    private static final HtmlRenderer RENDERER = HtmlRenderer.builder()
            .extensions(List.of(TablesExtension.create()))
            .escapeHtml(true)
            .sanitizeUrls(true)
            .build();

    public static String toHtml(String markdown) {
        return RENDERER.render(PARSER.parse(markdown));
    }
}
