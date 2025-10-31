package com.cloudtechsolutions.wiki.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cloudtechsolutions.wiki.model.Article;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{
    List<Article> findTop3ByOrderByCreatedAtDesc();
    List<Article> findByKeywordsContainingIgnoreCase(String keyword);
    List<Article> findByCategoryId(Long categoryId);
}

