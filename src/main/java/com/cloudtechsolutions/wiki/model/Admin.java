//Alana Cristina Muller S1569092
/* Admin Model - This model represents an admin user in the system, including fields for id, name, email, and password. */
package com.cloudtechsolutions.wiki.model;

//Jakarta is used instead of javax for JPA in Spring Boot 3+
import jakarta.persistence.*;

//Entity annotation marks this class as a JPA entity (a table in the database)
@Entity
public class Admin {

    //Primary key with auto-generated value
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Human-readable display name / username
    private String name;
    // User's email (used as primary login identifier in this app)
    private String email;
    // Stored password hash (BCrypt). Never store plaintext passwords.
    private String password; // encrypted

    //Getters and Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
