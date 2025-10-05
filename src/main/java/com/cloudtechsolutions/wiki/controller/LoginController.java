/* Alana Cristina Muller S1569092 */
 /* Login Controller - This controller handles login requests, redirecting it to the login.html */
package com.cloudtechsolutions.wiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller is a Spring annotation that indicates that the class serves the role of a web controller in the MVC (Model-View-Controller) pattern. It is used to define request handling methods that process incoming HTTP requests, interact with the service layer, and return views (typically HTML pages) or data (like JSON or XML) as responses.
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String showLoginPage() {
        // Render the login page. Note: POST /login is processed by Spring Security
        // (UsernamePasswordAuthenticationFilter). We only provide the GET view here.
        return "login";
    }

}
