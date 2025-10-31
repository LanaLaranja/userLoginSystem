// Alana Cristina Muller S1569092
/* Controller: AdminController
 - Handles admin CRUD operations for articles and categories.
 - Endpoints under /admin/** are protected by Spring Security (ROLE_ADMIN).
 - Uses ArticleService and CategoryRepository to perform data modifications.
*/
// Alana Cristina Muller S1569092
/* Controller: AdminController
 - Admin-facing controller for article management. Provides endpoints for
     creating, editing, deleting and toggling visibility of articles.
*/
package com.cloudtechsolutions.wiki.controller;

import com.cloudtechsolutions.wiki.model.Article;
import com.cloudtechsolutions.wiki.model.Category;
import com.cloudtechsolutions.wiki.service.ArticleService;
import com.cloudtechsolutions.wiki.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ArticleService articleService;
    private final CategoryRepository categoryRepo;

    public AdminController(ArticleService articleService, CategoryRepository categoryRepo) {
        this.articleService = articleService;
        this.categoryRepo = categoryRepo;
    }

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        model.addAttribute("categories", categoryRepo.findAll());
        return "admin/dashboard";
    }

    // --- Create ---
    @GetMapping("/articles/new")
    public String newArticle(Model model) {
        model.addAttribute("article", new Article());
        model.addAttribute("categories", categoryRepo.findAll());
        return "admin/article-form";
    }

    @PostMapping("/articles/save")
    public String saveArticle(@ModelAttribute Article article) {
        articleService.save(article);
        return "redirect:/admin";
    }

    // --- Edit ---
    @GetMapping("/articles/edit/{id}")
    public String editArticle(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.getArticleById(id));
        model.addAttribute("categories", categoryRepo.findAll());
        return "admin/article-form";
    }

    // --- Delete ---
    @GetMapping("/articles/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleService.delete(id);
        return "redirect:/admin";
    }

    // --- Hide/Show ---
    @GetMapping("/articles/toggleVisibility/{id}")
    public String toggleVisibility(@PathVariable Long id) {
        articleService.toggleVisibility(id);
        return "redirect:/admin";
    }

    // --- Category ---
    @PostMapping("/categories/save")
    public String saveCategory(@ModelAttribute Category category) {
        categoryRepo.save(category);
        return "redirect:/admin";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryRepo.deleteById(id);
        return "redirect:/admin";
    }
}

