package com.cloudtechsolutions.wiki.service;
import com.cloudtechsolutions.wiki.model.Article;
import com.cloudtechsolutions.wiki.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArticleService{
    private final ArticleRepository repo;

    public ArticleService(ArticleRepository repo) {
        this.repo = repo;
    }

    public List<Article> getLatestArticles() {
        return repo.findTop3ByOrderByCreatedAtDesc();
    }

    public List<Article> getAllArticles(){
        return repo.findAll();
    }

    public List<Article> getArticlesByCategory(Long categoryId) {
        return repo.findByCategoryId(categoryId);
    }

    public Article getArticleById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Article> searchArticles(String keyword) {
        return repo.findByKeywordsContainingIgnoreCase(keyword);
    }
}