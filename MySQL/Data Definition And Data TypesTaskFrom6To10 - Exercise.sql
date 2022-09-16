USE `exercise`;

CREATE TABLE `people` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(200) NOT NULL,
`picture` BLOB,
`height` DECIMAL(10, 2),
`weight` DECIMAL(10, 2),
`gender` CHAR(1) NOT NULL,
`birthdate` DATE NOT NULL,
`biography` TEXT
);

INSERT INTO `people` (`name`, `gender`, `birthdate`) 
VALUES ("Georgi", 'm', DATE(NOW())), 
("Simona", 'f', DATE(NOW())), 
("Siyana", 'f', DATE(NOW())), 
("Martin", 'm', DATE(NOW())), 
("Kucho", 'm', DATE(NOW()));



CREATE TABLE `users` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`username` VARCHAR(30) NOT NULL,
`password` VARCHAR(26) NOT NULL,
`profile_picture` BLOB,
`last_login_time` TIME,
`is_deleted` BOOLEAN
);

INSERT INTO `users` (`username`, `password`) 
VALUES  ("Joro", "1234"),
		("Simona", "4321"),
		("Siyana", "4123"),
		("Marti", "3241"),
		("Test", "0123");
        
        
ALTER TABLE `users` 
DROP PRIMARY KEY,
ADD PRIMARY KEY `pk_users` (`id`, `username`);

ALTER TABLE `users` MODIFY COLUMN `last_login_time` DATETIME DEFAULT NOW();

ALTER TABLE `users`
DROP PRIMARY KEY,
ADD CONSTRAINT `pk_users`
PRIMARY KEY `users` (`id`),
MODIFY COLUMN `username` VARCHAR(30) UNIQUE;
