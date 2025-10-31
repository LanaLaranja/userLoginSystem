// Alana Cristina Muller S1569092
/* Repository: ArticleRepository
 - Spring Data JPA repository for Article entities. Custom finder methods
     are declared here to support visible-only queries used by the public site.
*/
// Alana Cristina Muller S1569092
/* Repository: ArticleRepository
 - Data access for Article entities. Includes admin and public (visible-only)
     finder methods used by ArticleService.
*/
package com.cloudtechsolutions.wiki.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cloudtechsolutions.wiki.model.Article;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{
    List<Article> findTop3ByOrderByCreatedAtDesc();
    // latest visible articles for public homepage
    List<Article> findTop3ByVisibleTrueOrderByCreatedAtDesc();
    List<Article> findByKeywordsContainingIgnoreCase(String keyword);
    List<Article> findByCategoryId(Long categoryId);
    List<Article> findByCategoryIdAndVisibleTrue(Long categoryId);
    // visible-only methods for public-facing lists
    List<Article> findAllByVisibleTrue();
    List<Article> findByKeywordsContainingIgnoreCaseAndVisibleTrue(String keyword);
}

