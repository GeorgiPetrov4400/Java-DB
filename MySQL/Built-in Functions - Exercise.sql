SELECT `first_name`, `last_name` 
FROM `employees` 
WHERE `first_name` LIKE "Sa%"
ORDER BY `employee_id`;

SELECT `first_name`, `last_name` 
FROM `employees` 
WHERE `last_name` LIKE "%ei%"
ORDER BY `employee_id`;

SELECT `first_name` 
FROM `employees` 
WHERE (`department_id` = 3 OR `department_id` = 10) AND YEAR(`hire_date`) BETWEEN 1995 AND 2005  
ORDER BY `employee_id`;

SELECT `first_name`, `last_name` 
FROM `employees` 
WHERE `job_title` NOT LIKE "%engineer%"
ORDER BY `employee_id`;

SELECT `name` 
FROM `towns` 
WHERE LENGTH(`name`) = 5 OR LENGTH(`name`) = 6
ORDER BY `name`;

SELECT `town_id`, `name` 
FROM `towns` 
WHERE `name` LIKE 'm%' OR `name` LIKE 'k%' OR `name` LIKE 'b%' OR `name` LIKE 'e%'
ORDER BY `name`;

SELECT * FROM `towns` 
WHERE `name` NOT LIKE 'r%' AND `name` NOT LIKE 'b%' AND `name` NOT LIKE 'd%'
ORDER BY `name`;

