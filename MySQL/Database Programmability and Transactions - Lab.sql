#1 Count Employees by Town
DELIMITER %%

CREATE FUNCTION ufn_count_employees_by_town(town_name VARCHAR(50))
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE id_for_town INT;
    DECLARE count_by_town INT;
    
    SET id_for_town := (SELECT `town_id` FROM `towns` WHERE `name` = town_name);
    SET count_by_town := (
			SELECT COUNT(*) FROM `employees` AS e
            WHERE e.`address_id` IN (
				SELECT `address_id` FROM `addresses` WHERE `town_id` = id_for_town)
		);
        
	RETURN count_by_town;
END%%

SELECT ufn_count_employees_by_town('Sofia')%%

#2 Employees Promotion
CREATE PROCEDURE usp_raise_salaries(department_name VARCHAR(50))
BEGIN
	UPDATE `employees`
	SET `salary` = `salary` * 1.05
    WHERE `department_id` = (
    SELECT `department_id` FROM `departments` WHERE `name` = department_name
		)
    ORDER BY `first_name`, `salary`;
END%%

CALL usp_raise_salaries('Finance')%%

#3 Employees Promotion By ID
CREATE PROCEDURE usp_raise_salary_by_id(id INT)
BEGIN
	DECLARE count_by_id INT;
    
	START TRANSACTION;
    SET count_by_id := (SELECT COUNT(*) FROM `employees` WHERE `employee_id` = id);
    
    UPDATE `employees` SET `salary` = `salary` * 1.05 WHERE `employee_id` = id;
    
    IF (count_by_id < 1) THEN
		ROLLBACK;
	ELSE 
		COMMIT;
	END IF;
END%%

CALL usp_raise_salary_by_id(17)%%

