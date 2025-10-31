// Alana Cristina Muller S1569092
/* Model: Category
 - Represents an article category. Categories are referenced by articles and
     used for filtering on the public site.
*/
package com.cloudtechsolutions.wiki.model;
import jakarta.persistence.*;


@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}