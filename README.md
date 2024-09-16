# Game Web Shop

## Overview
This application enables users to browse, add to cart, and purchase products online. It supports two roles: user (customer) and administrator. The application is built using Spring MVC, Thymeleaf, and Spring Security.

## Features

### User (Customer)
- **Anonymous Browsing**: Browse product categories and view product details without logging in.
- **Shopping Cart**: Add products to the cart, define quantities, and modify the cart contents.
- **Online Purchase**: Purchase products using cash on delivery or PayPal (authentication required).
- **Order History**: View historical purchase data including what was purchased, when, and how (authentication required).

### Administrator
- **Manage Products and Categories**: Perform CRUD operations on product categories and products.
- **Login History**: View historical data of user logins including who logged in, when, and from which IP address.
- **Purchase Overview**: View complete historical data of all purchases with search filters for customer and time period.

## Technologies Used
- **Spring MVC**: For the MVC architecture.
- **Thymeleaf**: As the template engine.
- **Spring Security**: For authentication and authorization.
- **Bootstrap**: For responsive visual design.
- **PayPal Sandbox**: For implementing PayPal payment option.

## Setup Instructions

### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Apache Maven
- MySQL or any other preferred database
- Git

### Installation

1. **Clone the repository:**
    

2. **Configure the Database:**
    - Create a new database in MySQL.
    - Update the `application.properties` file in the `src/main/resources` directory with your database configuration.
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/yourdatabase
    spring.datasource.username=yourusername
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **Install Dependencies:**
    ```sh
    mvn clean install
    ```

4. **Deploy to Tomcat:**
    - Package the application:
    ```sh
    mvn package
    ```
    - Copy the generated WAR file from the `target` directory to the `webapps` directory of your Tomcat server.
    - Start the Tomcat server.

5. **Access the Application:**
    - Open a web browser and navigate to `http://localhost:8080/`.


## Usage

### User (Customer)
1. **Browse Categories and Products:**
    - Visit the homepage to browse categories and view product details.

2. **Add to Cart:**
    - Add desired products to the cart and specify quantities.

3. **Modify Cart:**
    - Update quantities or remove items from the cart as needed.

4. **Purchase Products:**
    - Proceed to checkout and choose payment method (cash on delivery or PayPal).
    - Login is required to complete the purchase.

5. **View Order History:**
    - After logging in, view the historical overview of purchases.

### Administrator
1. **Manage Products and Categories:**
    - Login with admin credentials.
    - Perform CRUD operations on products and categories through the admin panel.

2. **View Login History:**
    - Access the admin dashboard to view login history including user information and IP addresses.

3. **View Purchase Overview:**
    - Use the admin panel to view and filter historical purchase data.


