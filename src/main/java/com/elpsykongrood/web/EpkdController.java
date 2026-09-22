package com.elpsykongrood.web;

import com.elpsykongrood.articles.ArticleService;
import com.elpsykongrood.comments.CommentPayloads;
import com.elpsykongrood.comments.CommentService;
import jakarta.validation.Valid;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EpkdController {

    private final ArticleService articleService;
    private final CommentService commentService;

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
        model.addAttribute("comments", commentService.findBySlug(slug));
        model.asMap().putIfAbsent("form", new CommentPayloads.Create("", ""));
        return "pages/article";
    }

    @PostMapping("/articles/{slug}/comments")
    String comment(
            @PathVariable String slug,
            @Valid @ModelAttribute("form") CommentPayloads.Create form,
            BindingResult errors,
            RedirectAttributes redirect
    ) {
        String destination = "redirect:/articles/" + slug + "#comments";
        if (errors.hasErrors()) {
            redirect.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "form", errors);
            redirect.addFlashAttribute("notice", new Notice(Notice.Type.ERROR, "notice.invalid"));
        } else {
            try {
                commentService.create(slug, form);
                redirect.addFlashAttribute("notice", new Notice(Notice.Type.SUCCESS, "notice.success"));
                return destination;
            } catch (DataAccessException exception) {
                var cause = exception.getMostSpecificCause();
                var sqlState = cause instanceof SQLException sql ? sql.getSQLState() : null;
                log.error("Comment creation failed: articleSlug={}, exception={}, cause={}, sqlState={}",
                        slug, exception.getClass().getSimpleName(), cause.getClass().getSimpleName(), sqlState);
                redirect.addFlashAttribute("notice", new Notice(Notice.Type.ERROR, "notice.failed"));
            }
        }
        redirect.addFlashAttribute("form", form);
        return destination;
    }
}
