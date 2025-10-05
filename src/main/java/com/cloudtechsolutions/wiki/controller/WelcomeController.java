// Alana Cristina Muller S1569092
/* Welcome Controller - This controller handles requests to the welcome page via the /welcome endpoint */
package com.cloudtechsolutions.wiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    // Map /welcome to the welcome template
    @GetMapping({"/welcome"})
    public String showWelcome() {
        return "welcome";
    }

}
