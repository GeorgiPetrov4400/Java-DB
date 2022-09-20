USE `Movies`;

CREATE TABLE `directors` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `director_name` VARCHAR(50) NOT NULL,
    `notes` TEXT
);

INSERT INTO `directors` (`director_name`, `notes`) 
VALUES ('Name1', 'Note'), ('Name2', 'Note'), ('Name3', 'Note'), ('Name4', 'Note'), ('Name5', 'Note');


CREATE TABLE `genres` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `genre_name` VARCHAR(20) NOT NULL,
    `notes` TEXT
);

INSERT INTO `genres` (`genre_name`, `notes`)
VALUES ('Name1', 'Note'), ('Name2', 'Note'), ('Name3', 'Note'), ('Name4', 'Note'), ('Name5', 'Note');

CREATE TABLE `categories` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `category_name` VARCHAR(20) NOT NULL,
    `notes` TEXT
);

INSERT INTO `categories` (`category_name`, `notes`)
VALUES ('Name1', 'Note'), ('Name2', 'Note'), ('Name3', 'Note'), ('Name4', 'Note'), ('Name5', 'Note');

CREATE TABLE `movies` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `title` VARCHAR(40) NOT NULL,
    `director_id` INT,
    `copyright_year` INT,
    `lenght` INT,
    `genre_id` INT,
    `category_id` INT,
    `rating` DOUBLE,
    `notes` TEXT
);

INSERT INTO `movies` (`title`) VALUES ('Movie1'), ('Movie2'), ('Movie3'), ('Movie4'), ('Movie5');