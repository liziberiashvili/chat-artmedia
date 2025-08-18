# Chat Widget & CRM Automation Suite
## Overview
This project contains automated UI and API tests for a chat widget and chat CRM platform.  
The automation framework is built using Java, Selenium, RestAssured, and TestNG.  
It follows the Page Object Model (POM) for UI automation and a modular helper structure for API testing.
## Project Structure
```
src
 ├── main
 │    ├── java/ge/chat/artmedia
 │    │    ├── api               # API-related classes and request handling
 │    │    ├── pages             # Page Object Model classes for UI automation
 │    │    └── utils             # Utility classes (drivers, data readers, helpers, etc.)
 │    │         └── apiHelper    # API helper methods (auth, invite, etc.)
 │    └── resources              # Test data and configuration files
 │
 ├── test
 │    ├── java/ge/chat/artmedia
 │    │    ├── api               # API test classes
 │    │    ├── setup             # Test setup and suite configuration
 │    │    └── ui                # UI test classes
 │    └── resources              # Test data, configuration, and assets
 │
 ├── target                      # Compiled classes & reports
 ├── test-output                 # Generated HTML reports (ExtentReports)
 ├── pom.xml                     # Maven dependencies & configuration
 ├── testng.xml                  # TestNG suite configuration
 └── .gitignore
```
## Test coverage
- UI
   - Widget interactions
   - CRM login & messaging
   - Agent invitation flow
- API
   - FAQ management
   - Reporting
   - Dashboard metrics
   - Department management




## Tech Stack 

- Language: Java
- Build Tool: Maven
- Test Framework: TestNG
- UI Automation: Selenium WebDriver
- API Testing: RestAssured
- Reporting: ExtentReports
- Data Handling: JSON, Properties
- Architecture: Page Object Model (POM)


## Setup & Installation
1. Clone the repository
   ```bash
   git clone https://github.com/https://github.com/liziberiashvili/chat-artmedia
   cd chat-artmedia
2. Install dependencies
   ```bash
   mvn clean install
## Run all tests
```bash
   mvn clean test