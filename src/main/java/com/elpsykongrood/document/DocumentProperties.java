package com.elpsykongrood.document;

import java.nio.file.Path;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.documents")
public record DocumentProperties(
        Path directory,
        Map<DocumentType, String> displayNames
) {

    public DocumentProperties {
        directory = directory.toAbsolutePath().normalize();
        displayNames = Map.copyOf(displayNames);
    }
}
