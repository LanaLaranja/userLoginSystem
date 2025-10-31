package com.cloudtechsolutions.wiki.controller;

import org.springframework.stereotype.Controller;
import com.cloudtechsolutions.wiki.service.ArticleService;
import com.cloudtechsolutions.wiki.repository.CategoryRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private final ArticleService articleService;
    private final CategoryRepository categoryRepo;

    public HomeController(ArticleService articleService, CategoryRepository categoryRepo) {
        this.articleService = articleService;
        this.categoryRepo = categoryRepo;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("latestArticles", articleService.getLatestArticles());
        return "index";
    }

    @GetMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("categories", categoryRepo.findAll());
        return "categories";
    }

    @GetMapping("/articles")
    public String allArticles(Model model,
                              @RequestParam(value = "q", required = false) String query,
                              @RequestParam(value = "category", required = false) Long categoryId) {
        if (query != null && !query.isEmpty()) {
            model.addAttribute("articles", articleService.searchArticles(query));
        } else if (categoryId != null) {
            model.addAttribute("articles", articleService.getArticlesByCategory(categoryId));
        } else {
            model.addAttribute("articles", articleService.getAllArticles());
        }
        return "articles";
    }
}

