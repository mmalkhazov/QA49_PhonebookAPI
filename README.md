# Phonebook API Test Automation

## 🚀 Project Overview
This repository contains a professional backend automation suite for the **Phonebook REST API**. The framework is designed to validate core business logic, authentication flows, and contact management operations without the overhead of a UI.

## 🛠 Tech Stack
* **Language:** Java 11/17+
* **Library:** [Rest-Assured](https://rest-assured.io/) (DSL for testing REST services)
* **Test Runner:** TestNG
* **Build System:** Gradle
* **Data Handling:** Lombok (Data, Builder patterns)
* **Reporting:** Allure Framework

## 🏗 Key Features & Architecture
* **DTO (Data Transfer Objects):** Used to represent request and response bodies, ensuring type safety and clean code.
* **Separation of Concerns:** Distinct layers for API configurations, Data Models (Models), and Test Scripts.
* **Token-based Authentication:** Automated extraction and injection of Bearer tokens for secure endpoints.
* **Validation:** Verification of Status Codes, Response Time, and JSON body content.

## 📁 Project Structure
```text
📦 phonebook-api-automation
├── 📂 src
│   ├── 📂 main/java/com/phonebook
│   │   ├── 📂 dto        # Data Transfer Objects (Request/Response models)
│   │   ├── 📂 config     # API Endpoints and Base configurations
│   ├── 📂 test/java/com/phonebook/tests
│       ├── 📂 auth       # Login and Registration tests
│       ├── 📂 contacts   # CRUD operations for contacts
└── 📜 build.gradle       # Dependencies and build script
