DELIMITER %%

#1 Employees with Salary Above 35000
CREATE PROCEDURE usp_get_employees_salary_above_35000 ()
BEGIN
	SELECT `first_name`, `last_name` FROM `employees`
    WHERE `salary` > 35000
    ORDER BY `first_name`, `last_name`, `employee_id`;
END%%

#2 Employees with Salary Above Number
CREATE PROCEDURE usp_get_employees_salary_above (salary_number DECIMAL(19, 4))
BEGIN 
	SELECT `first_name`, `last_name` FROM `employees`
    WHERE `salary` >= salary_number
    ORDER BY `first_name`, `last_name`, `employee_id`;
END%%

#3 Town Names Starting With
CREATE PROCEDURE usp_get_towns_starting_with (enter_string VARCHAR(50))
BEGIN
	SELECT `name` FROM `towns`
    WHERE `name` LIKE CONCAT(enter_string, '%')
    ORDER BY `name`;
END%%

#4 Employees from Town
CREATE PROCEDURE usp_get_employees_from_town (enter_town VARCHAR(50))
BEGIN
	SELECT e.`first_name`, e.`last_name` FROM `employees` AS e
    JOIN `addresses` AS a ON e.`address_id` = a.`address_id`
    JOIN `towns` AS t ON a.`town_id` = t.`town_id`
    WHERE t.`name` = enter_town
    ORDER BY e.`first_name`, e.`last_name`, e.`employee_id`;
END%%

