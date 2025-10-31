// Alana Cristina Muller S1569092
/* Repository: CategoryRepository
 - Provides basic CRUD for categories used to group articles.
*/
// Alana Cristina Muller S1569092
/* Repository: CategoryRepository
 - Data access for Category entities.
*/
package com.cloudtechsolutions.wiki.repository;
import com.cloudtechsolutions.wiki.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> { }
