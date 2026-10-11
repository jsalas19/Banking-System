# Banking System

A Java-based terminal banking application built for CS 3331: Advanced Object-Oriented Programming. The project simulates a small banking system with customer and manager access, account management, transaction logging, and CSV-backed data storage.

## Overview

This project was created as a group assignment to explore object-oriented design, file I/O, inheritance, and user interaction through a command-line interface. It models a simplified bank system where users can view account information, inspect balances, and manage customer records.

## Quick Start

### Prerequisites

- Java 20+
- Maven 3.9+

### Run the application

From the repository root:

```bash
cd projectTwo-2
mvn clean compile
mvn exec:java -Dexec.mainClass=projectTwo.Run_Bank
```

If Maven is not installed or not on your PATH, install it and ensure your Java environment is configured before running the commands above.

## Features

- Terminal-based banking interface
- Customer and manager access modes
- Account lookup by customer name or ID
- Support for multiple account types:
  - Checking
  - Savings
  - Credit
- Transaction logging and bank statement generation
- CSV-based storage for user and account records
- Object-oriented design using Java classes and inheritance

## How the Application Works

When the program starts, it loads a CSV file containing bank users and displays a menu in the terminal. The user can choose to access the system as a manager or a customer, then perform actions based on the selected account type.

The application uses a combination of Java classes to model customers, accounts, and transaction behavior. It stores records in CSV files and writes transaction activity to log files for later review.

## Example Menu

```text
1. Access as Manager
2. Access as Customer
3. Exit the system, type [EXIT]
4. Add User
```

## Project Structure

```text
Banking-System/
├── README.md
├── projectTwo-2/
│   ├── pom.xml
│   ├── src/
│   │   └── main/java/projectTwo/
│   │       ├── Run_Bank.java
│   │       ├── Customer.java
│   │       ├── CustomerFinder.java
│   │       ├── CustomerMap.java
│   │       ├── Checking.java
│   │       ├── Savings.java
│   │       ├── Credit.java
│   │       ├── BankOperationCalls.java
│   │       ├── GenerateBankStatement.java
│   │       ├── CSV.java
│   │       └── ...
│   └── src/main/java/projectTwo/CS 3331 - Bank Users (2).csv
├── testCSV.csv
└── ...
```

## Main Classes and Responsibilities

- `Run_Bank` - Main entry point and terminal menu flow
- `Customer` - Stores customer information and account data
- `CustomerMap` - Handles customer data mapping and CSV persistence
- `CustomerFinder` - Searches for customers by name or account ID
- `Checking` - Checking account behavior
- `Savings` - Savings account behavior
- `Credit` - Credit account behavior
- `GenerateBankStatement` - Produces account statements
- `BankOperationCalls` - Handles transaction-related actions

## Tech Stack

- Java 20
- Maven
- OpenCSV
- JUnit support for testing

## Known Limitations

This project is intended for learning and demonstration rather than production banking use. Some notable limitations include:

- file paths are sensitive to the working directory
- all interaction happens in the terminal/command line
- account data is stored in CSV files rather than a database
- validation and error handling are basic compared to a real financial system

## Future Improvements

Potential improvements for a later version of the project include:

- database-backed persistence instead of CSV files
- a graphical user interface (GUI)
- stronger validation and input checking
- improved security and account access controls
- transaction history analytics and reporting
- better support for user authentication and authorization

## Authors

- Joshua Salas
- Diana Olivas
- Ruth Cazares-Bravo

## Notes

This is a classroom project focused on object-oriented programming, file handling, and command-line user interaction. It is best suited for educational purposes and small-scale demonstration work.
