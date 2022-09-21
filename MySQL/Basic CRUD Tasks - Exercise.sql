USE `soft_uni`;

SELECT * FROM `departments`
ORDER BY `department_id`;

SELECT `name` FROM `departments`
ORDER BY `department_id`;

SELECT `first_name`, `last_name`, `salary` FROM `employees`
ORDER BY `employee_id`;

SELECT `first_name`, `middle_name`, `last_name` FROM `employees`
ORDER BY `employee_id`;

SELECT CONCAT(`first_name`, '.', `last_name`, '@softuni.bg') AS `full_email_address` FROM `employees`
ORDER BY `employee_id`;

SELECT DISTINCT `salary` FROM `employees`;

SELECT * FROM `employees`
WHERE `job_title` = 'Sales Representative'
ORDER BY `employee_id`;

SELECT `first_name`, `last_name`, `job_title` FROM `employees`
WHERE `salary` >= 20000 AND `salary` <= 30000
ORDER BY `employee_id`;

SELECT CONCAT_WS(' ', `first_name`, `middle_name`, `last_name`) AS "Full Name" 
FROM `employees`
WHERE `salary` = 25000 OR `salary` = 14000 OR `salary` = 12500 OR `salary` = 23600
ORDER BY `employee_id`;

SELECT `first_name`, `last_name` FROM `employees` 
WHERE `manager_id` IS NULL
ORDER BY `employee_id`;

