USE `minions`;

CREATE TABLE `minions` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50),
`age` INT
);

CREATE TABLE `towns` (
`town_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50)
);

ALTER TABLE `minions`
ADD COLUMN `town_id` INT NOT NULL,
ADD CONSTRAINT `fk_minions_towns`
FOREIGN KEY `minions` (`town_id`)
REFERENCES `towns` (`id`);

INSERT INTO `towns` (`name`) VALUES ("Sofia"), ("Plovdiv"), ("Varna");

INSERT INTO `minions` (`name`, `age`, `town_id`) 
VALUES ("Kevin", 22, 1), ("Bob", 15, 3), ("Steward", NULL, 2);

/*INSERT INTO `towns` (`id`, `name`) VALUES (1, "Sofia"), (2, "Plovdiv"), (3, "Varna"); for Judge

INSERT INTO `minions` (`id`, `name`, `age`, `town_id`) 
VALUES (1, "Kevin", 22, 1), (2, "Bob", 15, 3), (3, "Steward", NULL, 2);*/ 

TRUNCATE TABLE `minions`;

DROP TABLE `minions`;
DROP TABLE `towns`;
