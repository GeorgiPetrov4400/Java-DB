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

#5 Salary Level Function
CREATE FUNCTION ufn_get_salary_level (employee_salary DECIMAL(19, 4))
RETURNS VARCHAR(10)
DETERMINISTIC
BEGIN
	DECLARE salary_level VARCHAR(10);
    
    IF employee_salary < 30000 THEN SET salary_level := 'Low';
    ELSEIF employee_salary <= 50000 THEN SET salary_level = 'Average';
    ELSE SET salary_level = 'High';
    END IF;

	RETURN salary_level;
END%%

#6 Employees by Salary Level
CREATE PROCEDURE usp_get_employees_by_salary_level (salary_level VARCHAR(10))
BEGIN
	SELECT `first_name`, `last_name` FROM `employees`
    WHERE ufn_get_salary_level(`salary`) LIKE salary_level
    ORDER BY `first_name` DESC, `last_name` DESC;
END%%

#7 Define Function
CREATE FUNCTION ufn_is_word_comprised(set_of_letters VARCHAR(50), word VARCHAR(50))
RETURNS INT
DETERMINISTIC
BEGIN
	RETURN word REGEXP (CONCAT('^[', set_of_letters, ']+$'));
END%%


#8 Find Full Name
CREATE PROCEDURE usp_get_holders_full_name ()
BEGIN 
	SELECT CONCAT(`first_name`,' ', `last_name`) AS 'full_name' FROM `account_holders`
    ORDER BY `full_name`;
END%%

#9 People with Balance Higher Than
CREATE PROCEDURE usp_get_holders_with_balance_higher_than (supplied_number DECIMAL(19, 4))
BEGIN 
	SELECT ah.`first_name`, ah.`last_name` FROM `account_holders` AS ah
    JOIN `accounts` AS a ON ah.`id` = a.`account_holder_id`
    GROUP BY ah.`id`
    HAVING SUM(a.`balance`) > supplied_number
    ORDER BY a.`account_holder_id`;
END%%

#10 Future Value Function
CREATE FUNCTION ufn_calculate_future_value (initial_sum DECIMAL(19, 4), yearly_interest_rate DOUBLE, years INT)
RETURNS DECIMAL(19, 4)
DETERMINISTIC
BEGIN
	DECLARE future_value_sum DECIMAL(19, 4);
    
    SET future_value_sum := initial_sum * POW((1 + yearly_interest_rate), years);
    RETURN future_value_sum;
END%%

#11 Calculating Interest
CREATE PROCEDURE usp_calculate_future_value_for_account (account_id INT, interest_rate DECIMAL(19, 4))
BEGIN
	SELECT a.`id` AS 'account_id', ah.`first_name`, ah.`last_name`, a.`balance` AS 'current_balance', 
    ufn_calculate_future_value(a.`balance`, interest_rate, 5) AS 'balance_in_5_years'
    FROM `accounts` AS a 
    JOIN `account_holders` AS ah ON ah.`id` = a.`account_holder_id`
    WHERE a.`id` = account_id;
END%%

