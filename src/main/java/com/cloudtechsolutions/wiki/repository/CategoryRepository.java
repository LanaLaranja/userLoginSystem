package com.cloudtechsolutions.wiki.repository;
import com.cloudtechsolutions.wiki.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> { }
