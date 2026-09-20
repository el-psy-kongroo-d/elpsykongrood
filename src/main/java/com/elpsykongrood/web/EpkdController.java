package com.elpsykongrood.web;

import com.elpsykongrood.articles.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class EpkdController {

    private final ArticleService articleService;

    @GetMapping("/")
    String home(Model model) {
        model.addAttribute("articles", articleService.findRecentArticles(5));
        return "pages/home";
    }

    @GetMapping("/articles")
    String articles(
            @RequestParam(defaultValue = "") String q,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "desc") String sort,
            Model model
    ) {
        model.addAttribute("results", articleService.findArticles(q, page, sort));
        return "pages/articles";
    }

    @GetMapping("/articles/{slug}")
    String article(@PathVariable String slug, Model model) {
        model.addAttribute("article", articleService.findBySlug(slug));
        return "pages/article";
    }

}
