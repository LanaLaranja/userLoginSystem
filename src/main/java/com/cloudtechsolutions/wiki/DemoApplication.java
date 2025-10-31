//Alana Cristina Muller S1569092
/* Main Application Class - This class bootstraps the Spring Boot application */

package com.cloudtechsolutions.wiki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// Launch the Spring Boot application. This starts the embedded Tomcat server,
		// creates the ApplicationContext, and scans for Spring components.
		SpringApplication.run(DemoApplication.class, args);
	}
}
