// Alana Cristina Muller S1569092
/* Model: Article
 - JPA entity representing an article. Added convenience accessors used by
     Thymeleaf templates and service layer.
*/
package com.cloudtechsolutions.wiki.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 5000) // allow longer content
    private String content;
    private String keywords;
    private LocalDateTime createdAt = LocalDateTime.now();
    private boolean visible = true;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // category getter/setter
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Provide a safe summary accessor used by templates. If an explicit
    // summary field is not present, derive a short snippet from content.
    public String getSummary() {
        if (this.content == null) return null;
        int max = 150;
        if (this.content.length() <= max) return this.content;
        return this.content.substring(0, max) + "...";
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
