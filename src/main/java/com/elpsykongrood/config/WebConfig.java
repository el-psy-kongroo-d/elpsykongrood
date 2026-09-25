package com.elpsykongrood.config;

import com.elpsykongrood.document.DocumentType;
import java.util.Locale;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(String.class, DocumentType.class, source -> DocumentType.valueOf(source.toUpperCase(Locale.ROOT)));
    }
}
