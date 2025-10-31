package com.cloudtechsolutions.wiki.service;

import com.cloudtechsolutions.wiki.model.Article;
import com.cloudtechsolutions.wiki.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository repo;

    @BeforeEach
    void cleanup() {
        repo.deleteAll();
    }

    @Test
    void testToggleVisibility() {
        Article a = new Article();
        a.setTitle("Test");
        a.setContent("content");
        a.setVisible(true);
        repo.saveAndFlush(a);

        articleService.toggleVisibility(a.getId());

        Article updated = repo.findById(a.getId()).orElse(null);
        assertNotNull(updated, "Article should exist after toggle");
        assertFalse(updated.isVisible(), "Article visibility should be toggled to false");
    }

    @Test
    void testCreateArticle() {
        Article a = new Article();
        a.setTitle("New Article");
        a.setContent("Hello World");

        articleService.save(a);

        // verify repository contains the saved article
        boolean found = repo.findAll().stream().anyMatch(x -> "New Article".equals(x.getTitle()));
        assertTrue(found, "Saved article should be present in repository");
    }
}
