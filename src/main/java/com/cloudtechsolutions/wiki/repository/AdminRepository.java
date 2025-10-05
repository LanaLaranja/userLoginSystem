// Alana Cristina Muller S1569092
/* Admin Repository - This interface handles database operations for Admin entities */
package com.cloudtechsolutions.wiki.repository;

import com.cloudtechsolutions.wiki.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    // Look up users by email (used by authentication)
    Admin findByEmail(String email);

    // Look up by display name / username (fallback)
    Admin findByName(String name);
}
