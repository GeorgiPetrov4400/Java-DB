CREATE DATABASE `instd`;

#1 Table design
CREATE TABLE `users` (
`id` INT PRIMARY KEY,
`username` VARCHAR(30) NOT NULL UNIQUE,
`password` VARCHAR(30) NOT NULL,
`email` VARCHAR(50) NOT NULL,
`gender` CHAR(1) NOT NULL,
`age` INT NOT NULL,
`job_title` VARCHAR(40) NOT NULL,
`ip` VARCHAR(30) NOT NULL
);

CREATE TABLE `addresses` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`address` VARCHAR(30) NOT NULL,
`town` VARCHAR(30) NOT NULL,
`country` VARCHAR(30) NOT NULL,
`user_id` INT NOT NULL,
CONSTRAINT `fk_addresses_users`
FOREIGN KEY (`user_id`)
REFERENCES `users`(`id`)
);

CREATE TABLE `photos` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`description` TEXT NOT NULL,
`date` DATETIME NOT NULL,
`views` INT NOT NULL DEFAULT 0
);

CREATE TABLE `comments` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`comment` VARCHAR(255) NOT NULL,
`date` DATETIME NOT NULL,
`photo_id` INT NOT NULL,
CONSTRAINT `fk_comments_photos`
FOREIGN KEY (`photo_id`)
REFERENCES `photos`(`id`)
);

CREATE TABLE `users_photos` (
`user_id` INT NOT NULL,
`photo_id` INT NOT NULL,
CONSTRAINT `fk_users_photos_users`
FOREIGN KEY (`user_id`)
REFERENCES `users`(`id`),
CONSTRAINT `fk_users_photos_photos`
FOREIGN KEY (`photo_id`)
REFERENCES `photos`(`id`)
);

CREATE TABLE `likes` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`photo_id` INT,
`user_id` INT,
CONSTRAINT `fk_likes_photos`
FOREIGN KEY (`photo_id`)
REFERENCES `photos`(`id`),
CONSTRAINT `fk_likes_users`
FOREIGN KEY (`user_id`)
REFERENCES `users`(`id`)
);


#2 Insert
INSERT INTO `addresses` (`address`, `town`, `country`, `user_id`) 
	(SELECT u.`username`, u.`password`, u.`ip`, u.`age` FROM `users` AS u
	 WHERE u.`gender` = 'M');
     
#3 Update
UPDATE `addresses` AS a 
SET a.`country` = (CASE
        WHEN a.`country` LIKE 'B%' THEN 'Blocked'
        WHEN a.`country` LIKE 'T%' THEN 'Test'
        WHEN a.`country` LIKE 'P%' THEN 'In Progress'
				   END)
WHERE `country` LIKE 'B%' OR `country` LIKE 'T%' OR `country` LIKE 'P%';

#4 Delete
DELETE a FROM `addresses` AS a
WHERE a.`id` % 3 = 0;

#5 Users
SELECT `username`, `gender`, `age` FROM `users`
ORDER BY `age` DESC, `username`;

#6 Extract 5 most commented photos
SELECT p.`id`, p.`date` AS 'date_and_time', p.`description`, COUNT(c.`id`) AS 'commentsCount' FROM `photos` AS p
JOIN `comments` AS c ON p.`id` = c.`photo_id`
GROUP BY p.`id`
ORDER BY `commentsCount` DESC, p.`id`
LIMIT 5;

#7 Lucky users
SELECT CONCAT(u.`id`, ' ', u.`username`) AS 'id_username', u.`email` FROM `users` AS u
JOIN `users_photos` AS up ON u.`id` = up.`user_id`
WHERE up.`photo_id` = up.`user_id`
ORDER BY u.`id`;

#8 Count likes and comments
SELECT p.`id` AS 'photo_id', 
(SELECT COUNT(l.`id`) FROM `likes` AS l WHERE p.`id` = l.`photo_id`) AS 'likes_count', 
(SELECT COUNT(c.`id`) FROM `comments` AS c WHERE p.`id` = c.`photo_id`) AS 'comments_count' 
FROM `photos` AS p
GROUP BY p.`id`
ORDER BY `likes_count` DESC, `comments_count` DESC, p.`id`;

#9 The photo on the tenth day of the month
SELECT CONCAT(SUBSTRING(`description`, 1, 30), '...') AS 'summary', `date` FROM `photos`
WHERE DAY(`date`) = 10
ORDER BY `date` DESC;

#10 Get user’s photos count
DELIMITER %%
CREATE FUNCTION udf_users_photos_count(username VARCHAR(30)) 
RETURNS INT
DETERMINISTIC
BEGIN
	RETURN (SELECT COUNT(p.`id`) FROM `users` AS u
			JOIN `users_photos` AS up ON u.`id` = up.`user_id`
			JOIN `photos` AS p ON up.`photo_id` = p.`id`
			WHERE u.`username` = username);
END %%

#11 Increase user age
CREATE PROCEDURE udp_modify_user (address VARCHAR(30), town VARCHAR(30)) 
BEGIN
	UPDATE `users` AS u
	JOIN `addresses` AS a ON u.`id` = a.`user_id`
	SET u.`age` = u.`age` + 10
	WHERE a.`address` = address AND a.`town` = town;
END %%