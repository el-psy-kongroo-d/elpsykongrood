package com.elpsykongrood.document;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@EnableConfigurationProperties(DocumentProperties.class)
public class DocumentController {

    private final DocumentProperties properties;

    @GetMapping("/documents/{docType}")
    ResponseEntity<Resource> document(@PathVariable DocumentType docType) throws IOException {
        var path = properties.directory()
                    .resolve(docType.filename());

        return pdf(path, properties.displayNames().get(docType));
    }

    @GetMapping("/documents/projects/{slug:[a-z0-9-]+}/portfolio")
    ResponseEntity<Resource> project(@PathVariable String slug) throws IOException {
        var path = properties.directory()
                        .resolve("projects")
                        .resolve(slug)
                        .resolve(DocumentType.PORTFOLIO.filename());

        return pdf(path, slug + "_포트폴리오.pdf");
    }

    private ResponseEntity<Resource> pdf(Path path, String filename) throws IOException {
        var resource = new FileSystemResource(path);
        if (!resource.isReadable()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var disposition = ContentDisposition.inline().filename(filename, StandardCharsets.UTF_8).build();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, disposition.toString())
                .header("X-Robots-Tag", "noindex")
                .cacheControl(CacheControl.noCache())
                .lastModified(resource.lastModified())
                .body(resource);
    }
}
