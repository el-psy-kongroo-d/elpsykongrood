package com.elpsykongrood.site;

import com.elpsykongrood.articles.ArticleService;
import java.io.StringWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SiteController {

    private final SiteUrls urls;
    private final ArticleService articles;

    @GetMapping(value = "/robots.txt", produces = MediaType.TEXT_PLAIN_VALUE)
    String robots() {
        return "User-agent: *\nAllow: /\nSitemap: " + urls.url("/sitemap.xml") + "\n";
    }

    @GetMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE)
    String sitemap() throws XMLStreamException {
        var output = new StringWriter();
        var xml = XMLOutputFactory.newFactory().createXMLStreamWriter(output);
        try {
            xml.writeStartDocument("UTF-8", "1.0");
            xml.writeStartElement("urlset");
            xml.writeDefaultNamespace("http://www.sitemaps.org/schemas/sitemap/0.9");
            writeUrl(xml, urls.url("/"), null);
            writeUrl(xml, urls.url("/articles"), null);
            writeUrl(xml, urls.url("/projects"), null);
            for (var article : articles.findArticleLocations()) {
                writeUrl(xml, urls.article(article.slug()), article.updatedAt().toString());
            }
            xml.writeEndElement();
            xml.writeEndDocument();
            xml.flush();
            return output.toString();
        } finally {
            xml.close();
        }
    }

    private static void writeUrl(XMLStreamWriter xml, String location, String modified) throws XMLStreamException {
        xml.writeStartElement("url");
        xml.writeStartElement("loc");
        xml.writeCharacters(location);
        xml.writeEndElement();
        if (modified != null) {
            xml.writeStartElement("lastmod");
            xml.writeCharacters(modified);
            xml.writeEndElement();
        }
        xml.writeEndElement();
    }
}
