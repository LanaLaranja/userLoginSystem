// Alana Cristina Muller S1569092
/* Admin Service - This service handles business logic related to Admin entities, including authentication and registration */
package com.cloudtechsolutions.wiki.security;

import com.cloudtechsolutions.wiki.model.Admin;
import com.cloudtechsolutions.wiki.repository.AdminRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//@Service is a Spring annotation that indicates that the annotated class is a service component in the service layer of the application. It is used to define business logic and can be automatically detected and registered as a Spring bean during component scanning.
@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, BCryptPasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean authenticate(String email, String rawPassword) {
        // Simple authentication helper used by legacy code paths. It verifies
        // the raw password against the stored BCrypt hash. Prefer using the
        // Spring Security authentication flow which integrates with the
        // SecurityContext and session management.
        Admin user = adminRepository.findByEmail(email);
        if (user != null) {
            return passwordEncoder.matches(rawPassword, user.getPassword());
        }
        return false;
    }

    public Admin registerAdmin(String name, String rawPassword, String email) {
        // Centralized registration logic. Encodes the password with BCrypt
        // before persisting to ensure passwords are never stored in plaintext.
        String encodedPassword = passwordEncoder.encode(rawPassword);
        Admin user = new Admin();
        user.setName(name);
        user.setPassword(encodedPassword);
        user.setEmail(email);
        return adminRepository.save(user);
    }
}
