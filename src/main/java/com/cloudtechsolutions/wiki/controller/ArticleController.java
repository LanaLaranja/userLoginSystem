package com.cloudtechsolutions.wiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import com.cloudtechsolutions.wiki.service.ArticleService;

@Controller
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/article/{id}")
    public String viewArticle(@PathVariable Long id, Model model) {
        var article = articleService.getArticleById(id);
        if (article == null) {
            model.addAttribute("status", 404);
            model.addAttribute("error", "Article not found");
            model.addAttribute("message", "No article exists with id: " + id);
            model.addAttribute("path", "/article/" + id);
            return "error";
        }
        model.addAttribute("article", article);
        return "article";
    }
}

