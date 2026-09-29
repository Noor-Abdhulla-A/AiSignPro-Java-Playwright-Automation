# AiSignPro Playwright Automation

## Overview

This project is an end-to-end UI automation framework developed for the AiSignPro QA Automation Assessment.

The framework automates the customer creation, agreement assignment, document selection, signature placement, email sending, success-message validation, and logout workflow.

## Technology Stack

- Java
- Playwright
- TestNG
- Maven
- Extent Reports
- Git / GitHub

## Framework Design

The framework follows the Page Object Model (POM) design pattern.

### Key Components

- Page Object Model
- TestNG
- TestNG DataProvider
- Dynamic test data
- Playwright automation
- Extent Reports
- TestNG Listener
- Automatic failure screenshots
- Assertions and validations

## Automated Test Flow

The main end-to-end test performs the following steps:

1. Login to AiSignPro
2. Navigate to Customers
3. Add a new customer
4. Enter dynamic contact name
5. Enter dynamic email address
6. Enter dynamic mobile number
7. Assign the customer
8. Handle customer creation confirmation
9. Open Assign Agreements page
10. Open Documents panel
11. Select the first document
12. Drag the Signature field onto the document
13. Click Send Mail
14. Handle Send Anyways confirmation
15. Continue to Send Mail
16. Continue through Assignment Options
17. Verify the success message
18. Logout
19. Verify the Welcome to AiSignPro page

## Project Structure

```text
AiSignPro
│
├── src
│   ├── main
│   │   └── java
│   │       └── com.aisignpro
│   │           ├── base
│   │           ├── pages
│   │           ├── listeners
│   │           └── utils
│   │
│   └── test
│       └── java
│           └── com.aisignpro
│               ├── tests
│               └── testdata
│
├── pom.xml
├── testng.xml
├── README.md
└── .gitignore