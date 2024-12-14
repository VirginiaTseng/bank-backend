# BankDemo Application

## Overview
BankDemo is a Spring Boot application for managing bank accounts. It provides functionalities for registering new accounts, updating account information, and handling transactions.

## Prerequisites
- Java 11 or higher
- Maven 3.6.0 or higher
- A database (e.g., MySQL) configured and running

## Setup Instructions

### Clone the Repository
git clone https://github.com/VirginiaTseng/bankdemo.git 
cd bankdemo


### Configure the Database
Update the `application.properties` file located in `src/main/resources` with your database configuration:

spring.datasource.url=jdbc:mysql://localhost:3306/bankdemo spring.datasource.username=yourdbusername spring.datasource.password=yourdbpassword spring.jpa.hibernate.ddl-auto=update


### Build the Application
mvn clean install


### Run the Application
mvn spring-boot:run


### Access the Application
The application will be accessible at `http://localhost:8080`.

## API Endpoints

### Register a New Account
- **URL:** `/api/accounts/register`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "username": "john_doe",
    "password": "password123",
    "confirmPassword": "password123",
    "gender": 1,
    "address": "123 Main St",
    "phoneNumber": "123-456-7890",
    "email": "john.doe@example.com",
    "accountType": 1,
    "initialDeposit": 1000.00,
    "idProofType": "Passport",
    "idProofNumber": "A1234567",
    "occupation": "Engineer",
    "annualIncome": 75000.00,
    "dateOfBirth": "1990-01-01"
  }
  ```

### Get Account Information
- **URL:** `/api/accounts/{id}/info`
- **Method:** `GET`

### Update Account Information
- **URL:** `/api/accounts/{id}/update`
- **Method:** `PUT`
- **Request Body:** `BankAccountInfo` object

### Delete an Account
- **URL:** `/api/accounts/{id}/delete`
- **Method:** `PUT`

## Additional Notes
- Ensure that the database is running and accessible before starting the application.
- The application uses SLF4J for logging. Logs can be found in the console output.
- For any issues or contributions, please open an issue or a pull request on the GitHub repository.

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.
