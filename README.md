# SoloStock Hub

SoloStock Hub is a Spring Boot inventory management web application designed for e-commerce warehouse operations.

The application allows users to manage products, brands, stock quantities, authentication, user roles, and administrative operations through a responsive web interface.

## Developer

Abdul Daas

Individual Project — CPAN 228 Web Application Development

## Technology Stack

* Java 21
* Spring Boot
* Spring MVC
* Spring Data JPA
* Spring Security
* Thymeleaf
* Bootstrap 5
* H2 Database
* MySQL
* Maven

## Main Features

* Home, About, and Contact pages
* Product inventory management
* Product creation with validation
* Product editing and deletion
* Product search
* Category and brand filtering
* Server-side sorting and pagination
* Product details pages
* Database persistence
* User registration
* BCrypt password encoding
* Custom login and logout
* Role-based authorization
* Customer, Staff, and Admin roles
* Admin-only dashboard
* Development and QA database profiles
* YAML-based application configuration

## User Roles

### Customer

Customers can:

* Log in
* View products
* Search products
* Filter products
* Sort products
* View product details

### Staff

Staff members can:

* Perform all Customer actions
* Add products
* Edit products

### Administrator

Administrators can:

* Perform all Staff actions
* Delete products
* Access the administrator dashboard
* View registered users

## Test Accounts

### Administrator

Username:

`admin`

Password:

`Admin123!`

### Staff

Username:

`staff`

Password:

`Staff123!`

### Customer

Username:

`customer`

Password:

`Customer123!`

The passwords are encoded using BCrypt before being stored in the database.

# Configuration Profiles

SoloStock Hub uses Spring Boot YAML configuration files.

The project contains:

`application.yml`

Common application configuration.

`application-dev.yml`

Development configuration using an in-memory H2 database.

`application-qa.yml`

QA configuration using a persistent MySQL database.

# Running the DEV Profile

The DEV profile uses an in-memory H2 database.

From Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=dev"
```

From Git Bash, Linux, or macOS:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

Open:

`http://localhost:8080`

## H2 Console

Open:

`http://localhost:8080/h2-console`

Use:

JDBC URL:

`jdbc:h2:mem:solostockhub`

Username:

`sa`

Password:

Leave blank.

# Running the QA Profile

The QA profile uses MySQL.

## Step 1 — Install and Start MySQL

Ensure that MySQL Server is installed and running.

## Step 2 — Create the Database

Run:

```sql
CREATE DATABASE solostockhub;
```

## Step 3 — Configure Environment Variables

The QA profile supports these environment variables:

`DB_HOST`

Default:

`localhost`

`DB_PORT`

Default:

`3306`

`DB_NAME`

Default:

`solostockhub`

`DB_USERNAME`

Default:

`root`

`DB_PASSWORD`

Default:

`root`

Example using Windows PowerShell:

```powershell
$env:DB_HOST="localhost"
$env:DB_PORT="3306"
$env:DB_NAME="solostockhub"
$env:DB_USERNAME="root"
$env:DB_PASSWORD="your_mysql_password"
```

## Step 4 — Start the QA Profile

Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=qa"
```

Git Bash, Linux, or macOS:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=qa
```

Open:

`http://localhost:8080`

The navbar displays `QA PROFILE` when the QA environment is active.

# Alternative Profile Switching

The application can also be started using the Spring Boot command-line profile property.

DEV:

```powershell
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.arguments=--spring.profiles.active=dev"
```

QA:

```powershell
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.arguments=--spring.profiles.active=qa"
```

No Java source code needs to be changed when switching database environments.

# Build the Project

Windows:

```powershell
.\mvnw.cmd clean package
```

Git Bash, Linux, or macOS:

```bash
./mvnw clean package
```

A successful build displays:

`BUILD SUCCESS`

# Project Structure

```text
src/main/java/com/abdul/solostockhub
├── config
├── controller
├── model
├── repository
├── security
└── service
```

Configuration files:

```text
src/main/resources
├── application.yml
├── application-dev.yml
└── application-qa.yml
```

# Database Initialization

The application initializes realistic sample brands and products when it starts.

The application also creates the following demonstration users when required:

* Administrator
* Warehouse Staff
* Customer

Java-based initialization is used so startup data works consistently with both H2 and MySQL.

# Security

SoloStock Hub uses Spring Security.

Passwords are encoded using BCrypt.

Public pages include:

* Home
* About
* Contact
* Registration
* Login

Authenticated users can access product pages.

Staff and Administrators can create and edit products.

Only Administrators can delete products and access the administration dashboard.

# Deliverable 3 Configuration

Deliverable 3 transitions the application from traditional `.properties` configuration to hierarchical YAML configuration.

The development environment uses H2.

The QA environment uses MySQL.

The active database environment can be changed using a Spring profile without editing application source code.

# Contribution

This project was completed individually by Abdul Daas.

All design, implementation, testing, configuration, database integration, security, and documentation were completed by the developer.
