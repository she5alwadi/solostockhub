# SoloStock Hub

## Project Overview

SoloStock Hub is an inventory management web application for an e-commerce warehouse business. The system helps manage products, brands, inventory levels, distribution centres, customers, orders, and users.

## Project Category

E-Commerce Platform

## Developer

Abdul Daas — Individual developer working on this project.

## Why I Chose This Category

I chose the E-Commerce Platform category because it has clear real-world use cases and strong CRUD functionality. It also connects well with inventory, logistics, and warehouse management, which makes it practical and useful for future development.

## Main Entities

1. Product
2. Brand
3. Inventory
4. Distribution Centre
5. Customer
6. Order
7. User

## Entity Attributes

### Product
- productId
- name
- description
- price
- size
- color
- quantity

### Brand
- brandId
- brandName
- country
- contactEmail

### Inventory
- inventoryId
- quantityAvailable
- reorderLevel
- lastUpdated

### Distribution Centre
- centreId
- name
- address
- city
- capacity

### Customer
- customerId
- firstName
- lastName
- email
- phone

### Order
- orderId
- orderDate
- status
- totalAmount

### User
- userId
- username
- password
- role

## ER Diagram Relationships

- One Brand can have many Products.
- One Product can appear in many Inventory records.
- One Distribution Centre can store many Inventory records.
- One Customer can place many Orders.
- One Order can contain many Products.
- One User can manage many Orders.

## UI Layout Pages

The planned application will include:

- Home/Dashboard page
- Product list page
- Create product form
- Login page

## Technologies Used

- Java
- Spring Boot
- Spring Web
- Thymeleaf
- Spring Data JPA
- Spring Security
- Spring Data REST
- H2 Database
- GitHub

## How to Run the Project

1. Clone the repository:

```bash
git clone https://github.com/she5alwadi/solostockhub.git