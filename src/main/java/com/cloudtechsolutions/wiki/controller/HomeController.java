// Alana Cristina Muller S1569092
/* Controller: HomeController
 - Handles public pages: homepage, categories and articles listing.
 - Uses the ArticleService to provide public (visible-only) article lists.
*/
// Alana Cristina Muller S1569092
/* Controller: HomeController
 - Serves the home page and article listing. Uses ArticleService public APIs
     to ensure only visible articles are shown to anonymous users.
*/
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
            // public-facing list: only visible articles
            model.addAttribute("articles", articleService.getPublicArticles());
        }
        return "articles";
    }
}

