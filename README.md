# Banking & Transaction Management System

This is a Spring Boot based application that provides a simple banking system to manage customer accounts and transactions. It supports creating/Closing/Updating accounts, depositing, withdrawing, transferring money, and viewing transaction history/Balance and status.

## Features

- Create, update, and close customer accounts
- Deposit and withdraw funds with validations
- Transfer funds between accounts with balance checks
- View transaction history for any account
- Check transaction status
- Validation rules including:
  - Customer name must not be empty
  - Phone number must contain exactly 13 digits
  - Email must contain "@" and end with ".com"
  - Balance must be greater than zero
  - Email and phone number must be unique per account
  - No operations allowed on closed accounts

## Technologies Used

- Java 
- Spring Boot 
- Pom.xml(Spring Data JPA ,Spring Web, Maria Driver)
- Maven
- Heidi SQL
- RESTful API design

## Setup, Build, and Run Instructions

### Prerequisites

- **Java** or higher installed on your system.
- **Maven** build tool installed.
- **Heidi SQL** installed and running.
- **Git** installed for version control.

### Setup Your Database

1. Create a new database in Heidi SQL.
2. Update your application's `src/main/resources/application.properties` with your database details.

### Run Spring Boot Application of Banking****

Use tools like **Postman** to test the REST API endpoints.

### Optional: Run Tests 

Execute the following to run unit/integration tests:

## API Endpoints

### Account Management

- `POST /accounts` - Create a new account
- `GET /accounts` - Get all accounts
- `GET /accounts/{id}` - Get account details by ID
- `PUT /accounts/{id}` - Update account details
- `DELETE /accounts/{id}` - Close an account (marks as closed)
- `GET /accounts/{id}/balance` - Check account balance

### Transaction Management

- `POST /transactions/deposit` - Deposit amount to an account
- `POST /transactions/withdraw` - Withdraw amount from an account
- `POST /transactions/transfer` - Transfer amount between accounts
- `GET /transactions/{accountId}` - Get transaction history for an account
- `GET /transactions/{txnId}/status` - Get status of a transaction

## Validation and Error Handling

- The application performs input validation for all critical operations and returns meaningful error messages with appropriate HTTP status codes.
- Operations on closed accounts are denied with a clear message.
- Attempts to create duplicate accounts by phone or email are rejected.



