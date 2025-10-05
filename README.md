# BIT235 Assessment 2 - Part 1: Secure User Login System

This project is a secure user login system built with **Spring Boot**, **MySQL**, and **HTML/CSS**, submitted for Part 1 of Assessment 2 in BIT235 (Object-Oriented Programming) at Melbourne Polytechnic.

## 🔧 Technologies Used

- Java 17
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- MySQL
- HTML/CSS
- Jakarta Persistence API

## 🧩 Features

- User registration with **name, email, and encrypted password**
- Login authentication using **email/name and password**
- Passwords securely stored using **BCrypt hashing**
- Redirects to welcome or error page based on login outcome
- Flowchart and code comments included for academic clarity

## 🛡️ Security & Ethics

- Passwords are encrypted using `BCryptPasswordEncoder` to meet modern security standards.
- Sensitive credentials (e.g., database password) are **excluded from this repository** using `.gitignore`.
- A safe version of the config file is provided as `application-example.properties`.
- All test data is anonymized and used solely for demonstration purposes, in line with ICT professional ethics.

## ⚙️ Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/wiki-login-system.git
