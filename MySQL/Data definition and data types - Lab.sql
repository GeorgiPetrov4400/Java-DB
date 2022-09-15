CREATE DATABASE `gamebar`;

CREATE TABLE `gamebar`.`employees` (
`id` INT AUTO_INCREMENT PRIMARY KEY,
`first_name` VARCHAR(50) NOT NULL,
`last_name` VARCHAR(50) NOT NULL
);

CREATE TABLE `gamebar`.`categories` (
`id` INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(50) NOT NULL
);

CREATE TABLE `gamebar`.`products` (
`id` INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(50) NOT NULL,
`category_id` INT NOT NULL
);

INSERT INTO `gamebar`.`employees` (`first_name`, `last_name`) VALUES ("Georgi", "Petrov");
INSERT INTO `gamebar`.`employees` (`first_name`, `last_name`) VALUES ("Simona", "Petrova");
INSERT INTO `gamebar`.`employees` (`first_name`, `last_name`) VALUES ("Siyana", "Petrova");

ALTER TABLE `gamebar`.`employees` 
ADD COLUMN `middle_name` VARCHAR(50);

ALTER TABLE `gamebar`.`employees` 
MODIFY COLUMN `middle_name` VARCHAR(100);

ALTER TABLE `gamebar`.`products`
ADD CONSTRAINT `fk_products_categories`
FOREIGN KEY `products`(`category_id`)
REFERENCES `categories`(`id`);