CREATE DATABASE servlet;
USE servlet;

CREATE TABLE PRODUCTS (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  price DECIMAL(10,2) NOT NULL,
  description VARCHAR(255) NOT NULL,
  name VARCHAR(20) DEFAULT NULL,
);

CREATE TABLE USERS (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  email VARCHAR(20) NOT NULL,
  password VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id)
);
