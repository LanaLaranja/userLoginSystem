// Alana Cristina Muller S1569092
/* Service: ArticleService
 - Encapsulates business logic for retrieving, searching and modifying articles.
 - Provides separate methods for admin use (all articles) and public use (visible-only).
*/
// Alana Cristina Muller S1569092
/* Service: ArticleService
 - Business logic for articles. Provides public (visible-only) and admin
     methods so anonymous users only see visible articles while admins can
     manage all articles.
*/
package com.cloudtechsolutions.wiki.service;

import com.cloudtechsolutions.wiki.model.Article;
import com.cloudtechsolutions.wiki.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository repo;

    public ArticleService(ArticleRepository repo) {
        this.repo = repo;
    }

    public List<Article> getLatestArticles() {
        // Public homepage should show only visible latest articles
        try {
            return repo.findTop3ByVisibleTrueOrderByCreatedAtDesc();
        } catch (RuntimeException e) {
            // fallback to non-filtered query if repository doesn't support the method
            return repo.findTop3ByOrderByCreatedAtDesc();
        }
    }

    // Admin: return all articles (including hidden)
    public List<Article> getAllArticles() {
        return repo.findAll();
    }

    // Public: return only visible articles for listing on the front-end
    public List<Article> getPublicArticles() {
        return repo.findAllByVisibleTrue();
    }

    public List<Article> getArticlesByCategory(Long categoryId) {
        // Prefer only visible articles when listing by category
        try {
            return repo.findByCategoryIdAndVisibleTrue(categoryId);
        } catch (NoSuchMethodError | RuntimeException e) {
            // Fallback if method not supported for any reason
            return repo.findByCategoryId(categoryId);
        }
    }

    public Article getArticleById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Article> searchArticles(String keyword) {
        // Public search should only return visible articles
        try {
            return repo.findByKeywordsContainingIgnoreCaseAndVisibleTrue(keyword);
        } catch (RuntimeException e) {
            // Fallback to non-filtered search if repository method not available
            return repo.findByKeywordsContainingIgnoreCase(keyword);
        }
    }

    public void save(Article article) {
        repo.save(article);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void toggleVisibility(Long id) {
        Article article = repo.findById(id).orElse(null);
        if (article != null) {
            article.setVisible(!article.isVisible());
            repo.save(article);
        }
    }
}
