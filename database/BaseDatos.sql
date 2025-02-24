CREATE DATABASE IF NOT EXISTS bdd_challenge;
USE bdd_challenge;

-- Table for Customer which inherits from Person
CREATE TABLE Customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    gender ENUM('Hombre', 'Mujer', 'Otro') NOT NULL,
    age INT NOT NULL,
    identification VARCHAR(20) NOT NULL UNIQUE,
    address VARCHAR(255),
    phone VARCHAR(20),
    password VARCHAR(50) NOT NULL,
    status BOOLEAN NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


-- Table for Account
CREATE TABLE Account (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    account_number VARCHAR(20) NOT NULL,
    account_type ENUM('Corriente','Ahorros') NOT NULL,
    initial_balance DECIMAL(10, 2) NOT NULL,
    status BOOLEAN NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);

-- Table for Transactions
CREATE TABLE Transaction (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    transaction_type ENUM('Retiro','Deposito') NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL,
    date DATETIME NOT NULL,
    status BOOLEAN NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES Account(account_id)
);