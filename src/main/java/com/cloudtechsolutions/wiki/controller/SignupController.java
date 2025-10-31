// Alana Cristina Muller S1569092
/* Signup Controller - this controller handles user signup via the /signup endpoint */
// Alana Cristina Muller S1569092
/* Controller: SignupController
 - Displays the signup form and accepts registration submissions.
 - Note: password hashing and user creation are delegated to the AdminService.
*/
// Alana Cristina Muller S1569092
/* Controller: SignupController
 - Handles user signup view and form submission for creating admin users.
*/
package com.cloudtechsolutions.wiki.controller;

import com.cloudtechsolutions.wiki.security.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignupController {

    private final AdminService adminService;

    public SignupController(AdminService adminService) {
        this.adminService = adminService;
    }

    //@GetMapping is a Spring annotation that maps HTTP GET requests to specific handler methods in a controller class. It is used to define endpoints that respond to GET requests, typically for retrieving data or rendering views.
    @GetMapping("/signup")
    public String showSignupPage() {
        // Render the signup form
        return "signup";
    }

    //@PostMapping is a Spring annotation that maps HTTP POST requests to specific handler methods in a controller class. It is used to define endpoints that respond to POST requests, typically for submitting data to the server, such as form submissions or creating new resources.
    @PostMapping("/signup")
    public String registerAdmin(@RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        // Register via service which handles password encoding.
        adminService.registerAdmin(username, password, email);
        return "redirect:/login?registered";
    }

}
