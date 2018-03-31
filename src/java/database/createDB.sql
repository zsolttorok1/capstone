/*CREATE A FUNCTION, REMOVE THE SEQUENCES, ADD A AUTO INCREMENT FOR ADDRESS 
AND PHONE TABLES, THE FUNCTION CALLS THE CURRENT VALUE OF AN ID AND INSERT THAT 
INTO THE DATA */
DROP DATABASE if exists CapstoneDB;
CREATE DATABASE CapstoneDB;
USE CapstoneDB;

SET global sql_mode='STRICT_ALL_TABLES';

/* table structures ***********************************************************************/
CREATE TABLE `category` (
    `category_id` int NOT NULL AUTO_INCREMENT,
    `category_name` varchar(30) NOT NULL,
    PRIMARY KEY (`category_id`)
);

CREATE TABLE `item` (
    `item_name` varchar(100) NOT NULL,
    `quantity` int(5) NOT NULL,
    `category_id` int NOT NULL,
    `description` varchar(2000) NOT NULL,
    PRIMARY KEY (`item_name`),
    CONSTRAINT `FK_User_Category_id` FOREIGN KEY (`category_id`) references `category`(`category_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `report` (
    `report_name` varchar(50) NOT NULL,
    `description` varchar(2000) NOT NULL,
    `date_created` date NOT NULL,
    `pdf_filepath` varchar(50) NOT NULL,
    PRIMARY KEY (`report_name`)
);

CREATE TABLE `phone` (
    `phone_id` int NOT NULL AUTO_INCREMENT,
    `phone_number` bigint NOT NULL,
    PRIMARY KEY (`phone_id`)
);

CREATE TABLE `address` (
    `address_id` int NOT NULL AUTO_INCREMENT,
    `house_number` int NOT NULL,
    `street` varchar(50) NOT NULL,
    `city` varchar(50) NOT NULL,
    `province`varchar(20) NOT NULL,
    `country` varchar(20) NOT NULL,
    `postal_code` varchar(20) NOT NULL,
    PRIMARY KEY (`address_id`)
);

CREATE TABLE `role` (
    `role_id` int(10) NOT NULL AUTO_INCREMENT,
    `role_name` varchar(20) NOT NULL,
    PRIMARY KEY (`role_id`)
);

CREATE TABLE `user` (
    `user_name` varchar(50) NOT NULL,
    `address_id` int(10) NOT NULL,
    `password` varchar(128) NOT NULL,
    `firstname` varchar(50) NOT NULL,
    `lastname` varchar(50) NOT NULL,
    `role_id` int(10) NOT NULL,
    `email` varchar(100) NOT NULL,
    `hourly_rate` decimal(10,2) NOT NULL,
    `salt` varchar(32) NOT NULL,
    PRIMARY KEY (`user_name`),
    CONSTRAINT `FK_User_Address_id`  FOREIGN KEY (`address_id`) references `address`(`address_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `FK_User_Role_id`  FOREIGN KEY (`role_id`) references `role`(`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `phone_user` (
    `user_name` varchar(50) NOT NULL,
    `phone_id` int NOT NULL,
    PRIMARY KEY (`user_name`, `phone_id`),
    CONSTRAINT `FK_PU_user_name`  FOREIGN KEY (`user_name`) references `user`(`user_name`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `FK_PU_phone_id` FOREIGN KEY (`phone_id`) references `phone`(`phone_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `position` (
    `position_id` int NOT NULL AUTO_INCREMENT,
    `position_name` varchar(50) NOT NULL,
    PRIMARY KEY (`position_id`)
);

CREATE TABLE `customer` (
    `customer_id` int NOT NULL AUTO_INCREMENT,
    `address_id` int NOT NULL,
    `firstname` varchar(50) NOT NULL,
    `lastname` varchar(50) NOT NULL,
    `company_name` varchar(50) NOT NULL,
    `email` varchar(100) NOT NULL,
    `position_id` int NOT NULL,
    `notes` varchar (2000) NULL,
    PRIMARY KEY (`customer_id`),
    CONSTRAINT `FK_Customer_Address_id` FOREIGN KEY (`address_id`) references `address`(`address_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `FK_Customer_Position_id` FOREIGN KEY (`position_id`) references `position`(`position_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `phone_customer` (
    `customer_id` int NOT NULL,
    `phone_id` int NOT NULL,
    PRIMARY KEY (`customer_id`, `phone_id`),
    CONSTRAINT `FK_PC_customer_id`  FOREIGN KEY (`customer_id`) references `customer`(`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `FK_PC_phone_id` FOREIGN KEY (`phone_id`) references `phone`(`phone_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `job` (
    `job_name` varchar(50) NOT NULL,
    `address_id` int NOT NULL,
    `customer_id` int NOT NULL,
    `description` varchar(2000) NULL, 
    `date_started` date NOT NULL,
    `date_finished` date NULL,
    `balance` decimal(20,2) NOT NULL,
    `status` varchar(50) NOT NULL,
    PRIMARY KEY (`job_name`),
    CONSTRAINT `FK_Job_Address_id` FOREIGN KEY (`address_id`) references `address`(`address_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `FK_Job_Customer_id` FOREIGN KEY (`customer_id`) references `customer`(`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `job_user` (
    `job_name` varchar(50) NOT NULL,
    `user_name` varchar(50) NOT NULL,
    `hours` int NOT NULL,
    PRIMARY KEY (`job_name`, `user_name`),
    CONSTRAINT `FK_JU_Job_name` FOREIGN KEY (`job_name`) references `job`(`job_name`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `FK_JU_User_name` FOREIGN KEY (`user_name`) references `user`(`user_name`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `job_item` (
    `job_name` varchar(50) NOT NULL,
    `item_name` varchar(100) NOT NULL,
    `note` varchar(2000) NULL,
    `quantity` int(5) NOT NULL,
    PRIMARY KEY (`job_name`, `item_name`),
    CONSTRAINT `FK_JI_Job_name` FOREIGN KEY (`job_name`) references `job`(`job_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `FK_JI_Item_name` FOREIGN KEY (`item_name`) references `item`(`item_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE `job_report` (
    `job_name` varchar(50) NOT NULL,
    `report_name` varchar(50) NOT NULL,
    PRIMARY KEY (`job_name`, `report_name`),
    CONSTRAINT `FK_JR_Job_name` FOREIGN KEY (`job_name`) references `job`(`job_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `FK_JR_Report_name` FOREIGN KEY (`report_name`) references `report`(`report_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE `quote` (
    `quote_name` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL,
    `description` varchar(2000) NOT NULL, 
    PRIMARY KEY (`quote_name`)
);

CREATE TABLE `password_change_request` (
  `pcr_uuid` varchar(128) NOT NULL,
  `pcr_time` datetime NOT NULL,
  `user_name` varchar(50) NOT NULL,
  PRIMARY KEY (`pcr_uuid`),
  CONSTRAINT `FK_PCR_User` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`) ON DELETE CASCADE ON UPDATE CASCADE
);

/* cleaning PROCEDURES ***********************************************************************/
DELIMITER $$

CREATE procedure `clean_address_proc` ()
BEGIN
    /* deleting unused address entries */
    DELETE FROM address
        WHERE address_id NOT IN
            (SELECT address_id 
                FROM `user`
            UNION
            SELECT address_id 
                FROM `customer`
            UNION
            SELECT address_id 
                FROM `job`);
-- (SELECT u.address_id 
--     FROM `user` u
--     LEFT OUTER JOIN `customer` c ON c.address_id = u.address_id
-- UNION
-- SELECT u.address_id 
--     FROM `user` u
--     RIGHT JOIN `customer` c ON c.address_id = u.address_id);  
END;
$$

CREATE procedure `clean_phone_proc` ()
BEGIN
    /* deleting unused phone entries */
    DELETE FROM phone
        WHERE phone_id NOT IN
        (SELECT phone_id 
            FROM `phone_user`
        UNION 
        SELECT phone_id 
            FROM `phone_customer`); 
END;
$$

/* function for ITEM ***********************************************************************/

CREATE FUNCTION `insert_item_func`
    (p_item_name varchar(100),
    p_quantity int(5),
    p_categoty_name varchar(30),
    p_description varchar(2000))
    RETURNS varchar(20)
NOT DETERMINISTIC
BEGIN
    DECLARE v_old_description_count int;
    DECLARE v_category_id int;
    DECLARE v_item_name_count int;
    -- DECLARE CONTINUE HANDLER FOR 1062 /*duplicate PK found */
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            return 'error';
        END;

    /* get category_id from category_name */
    set v_category_id = get_category_id_by_name(p_categoty_name);
    if (v_category_id = -1) then
        return 'error';
    end if;

    /* find out if Item is already in the database */
    SELECT count(item_name)
        INTO v_item_name_count
        FROM item
        WHERE p_item_name = item_name;

    /* item is already in the database, just do an update */
    if (v_item_name_count > 0) then
        SELECT CHAR_LENGTH(description)
            INTO v_old_description_count
            FROM item
            WHERE p_item_name = item_name;

            IF CHAR_LENGTH(p_description) > v_old_description_count THEN
                UPDATE item
                    SET description = p_description
                    WHERE p_item_name = item_name;
            END IF;

            UPDATE item
                SET quantity = quantity + p_quantity, category_id = v_category_id
                WHERE p_item_name = item_name;

            return 'updated';
    end if;

    /* item is not in database, so insert it*/
    INSERT INTO `item` (`item_name`, `quantity`, `category_id`, `description`)
        VALUES (p_item_name, p_quantity, v_category_id, p_description);

    /* deleting unused category entries */
    DELETE FROM category
        WHERE category_id NOT IN
        (SELECT category_id FROM item);

    return 'inserted';
END;
$$

CREATE FUNCTION `update_item_func`
    (p_item_name varchar(100),
    p_quantity int(5),
    p_category_name varchar(30),
    p_description varchar(2000))
    RETURNS varchar(20)
NOT DETERMINISTIC
BEGIN
    DECLARE v_category_id int;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            return 'error';
        END;

    /* get category_id from category_name */
    set v_category_id = get_category_id_by_name(p_category_name);
    if (v_category_id = -1) then
        return 'error';
    end if;

    UPDATE item
        SET quantity = p_quantity, category_id = v_category_id, description = p_description
        WHERE p_item_name = item_name;

    /* deleting unused category entries */
    DELETE FROM category
        WHERE category_id NOT IN
        (SELECT category_id FROM item);

    return 'updated';
END;
$$

CREATE procedure `address_proc` 
(OUT address_new int)
BEGIN
    select max(address_id)
    into address_new
    from address;
END;
$$

CREATE procedure `phone_proc`
(OUT phone_new int)
BEGIN
    select max(phone_id)
    into phone_new
    from phone;
END;
$$

CREATE FUNCTION `get_category_id_by_name`
    (p_category_name varchar(30))
    RETURNS int
NOT DETERMINISTIC
BEGIN
    DECLARE v_category_id int;

    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            return -1;
        END;

    DECLARE CONTINUE HANDLER FOR NOT FOUND
        BEGIN
            INSERT INTO `category` (`category_name`)
                VALUES (p_category_name);
            SELECT category_id
                INTO v_category_id
                FROM category
                WHERE p_category_name = category_name;

            return v_category_id;
        END;

    SELECT category_id
        INTO v_category_id
        FROM category
        WHERE p_category_name = category_name;

    return v_category_id;
END;
$$

CREATE FUNCTION `delete_item_func`
    (p_item_name varchar(100))
    RETURNS varchar(20)
NOT DETERMINISTIC
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            return 'error';
        END;

    DELETE FROM `item` 
        WHERE item_name = p_item_name;
    return 'deleted';

    /* deleting unused category entries */
    DELETE FROM category
        WHERE category_id NOT IN
        (SELECT category_id FROM item);
END;
$$

/* function for USER ***********************************************************************/
CREATE FUNCTION `insert_user_func`
    (p_user_name varchar(50),
    p_house_number int(50),
    p_street varchar(50),
    p_city varchar(50),
    p_province varchar(20),
    p_country varchar(20),
    p_postal_code varchar(20),
    p_password varchar(128),
    p_firstname varchar(50),
    p_lastname varchar(50),
    p_role_name varchar(20),
    p_email varchar(100),
    p_hourly_rate decimal(10,2),
    p_salt varchar(32))
    RETURNS varchar(20)
NOT DETERMINISTIC
BEGIN
    DECLARE v_role_id int;
    DECLARE v_address_id int;
    DECLARE v_user_count int;
    DECLARE v_user_phone_return varchar(20);
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            return 'error sql exception';
        END;

    /* get address_id */
    set v_address_id = insert_address_func(p_house_number, p_street, p_city, p_province, p_country, p_postal_code);
    if (v_address_id = -1) then
        return 'error address id';
    end if;

    /* get role_id */
    set v_role_id = get_role_id_by_name(p_role_name);
    if (v_role_id = -1) then
        return 'error role id';
    end if;

    /* find out if User is already in the database */
    SELECT count(user_name)
        INTO v_user_count
        FROM `user`
        WHERE p_user_name = user_name;

    if (v_user_count = 0) then
        /* User is not in database, so insert it */
        INSERT INTO `user` (`user_name`, `address_id`, `password`, `firstname`, `lastname`, `role_id`, `email`, `hourly_rate`,`salt`)
            VALUES (`p_user_name`, `v_address_id`, `p_password`, `p_firstname`, `p_lastname`, `v_role_id`, `p_email`, `p_hourly_rate`,`p_salt`);
    else 
        /* User is already in the database, just do an update */
        UPDATE `user`
            SET address_id = v_address_id, password = p_password, firstname = p_firstname,
                lastname = p_lastname, role_id = v_role_id, email = p_email, hourly_rate = p_hourly_rate
            WHERE p_user_name = user_name;
    end if;

    /* deleting unused address entries */
    call clean_address_proc();

    return 'inserted user';
END;
$$

CREATE FUNCTION `insert_phoneList_user_func`
    (p_user_name varchar(50),
    p_phone_number_list longtext)
    RETURNS varchar(20)
NOT DETERMINISTIC
BEGIN
    DECLARE v_phone_id int;
    DECLARE v_phone_number bigint;
    DECLARE v_phone_number_list_size int;
    DECLARE v_user_phone_return varchar(20);
    DECLARE i int;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            return 'error sql exception';
        END;

    /* deleting user's phone_user entries */
    DELETE FROM phone_user
        WHERE user_name = p_user_name;

    /*break the phone number coma separated string, to integer values*/
    set i = 1;
    set v_phone_number_list_size = LENGTH(p_phone_number_list) - LENGTH(REPLACE(p_phone_number_list, ',', ''));
    while i <= v_phone_number_list_size do

        set v_phone_number =  CAST(SUBSTRING_INDEX(p_phone_number_list, ',', 1) AS integer);
        set p_phone_number_list = TRIM(LEADING concat(v_phone_number,',') FROM p_phone_number_list );

        /* get phone_id */
        set v_phone_id = get_phone_id_by_number(v_phone_number);
        if (v_phone_id = -1) then
            return 'error phone id';
        end if;

        /* Insert to User Phone */
        set v_user_phone_return = insert_phone_user_func(p_user_name, v_phone_id);
        if (v_user_phone_return = 'error') then
            return 'error user phone';
        end if;

        set i = i + 1;
    end while;

    /* deleting unused phone entries */
    call clean_phone_proc();

    return 'inserted user phone';
END;
$$

CREATE FUNCTION `insert_phone_user_func`
    (p_user_name varchar(50),
    `p_phone_id` int(10))
    RETURNS varchar(20)
NOT DETERMINISTIC
BEGIN
    DECLARE v_phone_user_count int;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            return 'error';
        END;

    /* find out if Phone_User is already in the database */
    SELECT count(user_name)
        INTO v_phone_user_count
        FROM `phone_user`
        WHERE p_user_name = user_name and p_phone_id = phone_id;

    /* Phone_User is already in the database, just do an update */
    if (v_phone_user_count > 0) then
        return 'already in database';
    end if;

    /* Phone_User is not in database, so insert it*/
    INSERT INTO `phone_user` (`user_name`, `phone_id`)
        VALUES (`p_user_name`, `p_phone_id`);

    return 'inserted';
END;
$$

CREATE FUNCTION `insert_address_func`
    (p_house_number int(50),
    p_street varchar(50),
    p_city varchar(50),
    p_province varchar(20),
    p_country varchar(20),
    p_postal_code varchar(20))
    RETURNS int(10)
NOT DETERMINISTIC
BEGIN
    DECLARE v_address_count int;
    DECLARE v_address_id int;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            return -1;
        END;

    /* find out if Address is already in the database */
    SELECT count(*)
        INTO v_address_count
        FROM `address`
        WHERE p_house_number = house_number and 
            p_street = street and
            p_city = city and
            p_province = province and
            p_country = country and
            p_postal_code = postal_code;

    -- Address is already in the database, just do an update
    if (v_address_count = 0) then
        -- Address is not in database, so insert it
        INSERT INTO `address` (`house_number`, `street`, `city` , `province`, `country`, `postal_code`)
            VALUES (`p_house_number`, `p_street`, `p_city` , `p_province`, `p_country`, `p_postal_code`);
    end if;

    SELECT address_id
        INTO v_address_id
        FROM `address`
        WHERE p_house_number = house_number and 
            p_street = street and
            p_city = city and
            p_province = province and
            p_country = country and
            p_postal_code = postal_code;

    return v_address_id;
END;
$$

CREATE FUNCTION `get_role_id_by_name`
    (p_role_name varchar(20))
    RETURNS int
NOT DETERMINISTIC
BEGIN
    DECLARE v_role_id int;

    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            return -1;
        END;

    DECLARE CONTINUE HANDLER FOR NOT FOUND
        BEGIN
            INSERT INTO `role` (`role_name`)
                VALUES (p_role_name);
            SELECT role_id
                INTO v_role_id
                FROM `role`
                WHERE p_role_name = role_name;

            return v_role_id;
        END;

    SELECT role_id
        INTO v_role_id
        FROM `role`
        WHERE p_role_name = role_name;

    return v_role_id;
END;
$$

CREATE FUNCTION `get_phone_id_by_number`
    (p_phone_number bigint)
    RETURNS int
NOT DETERMINISTIC
BEGIN
    DECLARE v_phone_id int;

    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            return -1;
        END;

    DECLARE CONTINUE HANDLER FOR NOT FOUND
        BEGIN
            INSERT INTO `phone` (`phone_number`)
                VALUES (p_phone_number);
            SELECT phone_id
                INTO v_phone_id
                FROM phone
                WHERE phone_number = p_phone_number;

            return v_phone_id;
        END;

    SELECT phone_id
        INTO v_phone_id
        FROM phone
        WHERE phone_number = p_phone_number;

    return v_phone_id;
END;
$$

CREATE FUNCTION `delete_user_func`
    (p_user_name varchar(50))
    RETURNS varchar(20)
NOT DETERMINISTIC
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            return 'error';
        END;

    DELETE FROM `user` 
        WHERE user_name = p_user_name;
 
    /* deleting unused address entries */
    call clean_address_proc(); 

    /* deleting unused phone entries */
    call clean_phone_proc();

    return 'deleted';
END;
$$

CREATE FUNCTION `delete_phone_user_func`
    (p_user_name varchar(50))
    RETURNS varchar(20)
NOT DETERMINISTIC
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            return 'error';
        END;

    DELETE FROM `phone_user` 
        WHERE user_name = p_user_name;

    /* deleting unused phone entries */
    call clean_phone_proc();

    return 'deleted';
END;
$$

-- CREATE TRIGGER update_phone_user_clean_trig
--     AFTER UPDATE ON `phone_user` 
--     FOR EACH ROW
-- BEGIN
--     CALL clean_phone_proc();
-- END;
-- $$

/* function for CUSTOMER ***********************************************************************/

CREATE FUNCTION `insert_customer_func`
    (p_customer_id int,
    p_house_number int(50),
    p_street varchar(50),
    p_city varchar(50),
    p_province varchar(20),
    p_country varchar(20),
    p_postal_code varchar(20),
    p_firstname varchar(50),
    p_lastname varchar(50),
    p_company_name varchar(20),
    p_email varchar(100),
    p_position_name varchar(50),
    p_notes varchar (2000))
    RETURNS varchar(20)
NOT DETERMINISTIC
BEGIN
    DECLARE v_position_id int;
    DECLARE v_customer_id int default -1;
    DECLARE v_address_id int;
    DECLARE v_customer_count int;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            return 'error sql exception';
        END;

    /* get address_id */
    set v_address_id = insert_address_func(p_house_number, p_street, p_city, p_province, p_country, p_postal_code);
    if (v_address_id = -1) then
        return 'error address id';
    end if;

    /* get position_id */
    set v_position_id = get_position_id_by_name(p_position_name);
    if (v_position_id = -1) then
        return 'error position id';
    end if;

    /* find out if Customer is already in the database */
    SELECT count(customer_id)
        INTO v_customer_count
        FROM `customer`
        WHERE p_customer_id = customer_id;
    if (v_customer_count = 0) then
        /* Customer is not in database, so insert it */
        INSERT INTO `customer` (`customer_id`, `address_id`, `firstname`, `lastname`, `company_name`, `email`, `position_id`, `notes`)
            VALUES (`p_customer_id`, `v_address_id`, `p_firstname`, `p_lastname`, `p_company_name`, `p_email`, `v_position_id`, `p_notes`);
        set v_customer_id = LAST_INSERT_ID();
    else
        /* Customer is already in the database, just do an update */
        UPDATE `customer`
            SET address_id = v_address_id, firstname = p_firstname, lastname = p_lastname,
                company_name = p_company_name, position_id = v_position_id, email = p_email, notes = p_notes
            WHERE p_customer_id = customer_id;
        set v_customer_id = p_customer_id;
    end if;

    /* deleting unused address entries */
    call clean_address_proc();

    return concat('inserted', v_customer_id);
END;
$$


CREATE FUNCTION `delete_customer_func`
    (p_customer_id int)
    RETURNS varchar(20)
NOT DETERMINISTIC
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            return 'error';
        END;

    DELETE FROM `customer` 
        WHERE customer_id = p_customer_id;
 
    /* deleting unused address entries */
    call clean_address_proc();

    /* deleting unused phone entries */
    call clean_phone_proc();

    return 'deleted';
END;
$$

CREATE FUNCTION `insert_phoneList_customer_func`
    (p_customer_id int,
    p_phone_number_list longtext)
    RETURNS varchar(30)
NOT DETERMINISTIC
BEGIN
    DECLARE v_phone_id int;
    DECLARE v_phone_number bigint;
    DECLARE v_phone_number_list_size int;
    DECLARE v_customer_phone_return varchar(20);
    DECLARE i int;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            return 'error sql exception';
        END;

    /* deleting customer's phone_customer entries */
    DELETE FROM phone_customer
        WHERE customer_id = p_customer_id;

    /*break the phone number coma separated string, to integer values*/
    set i = 1;
    set v_phone_number_list_size = LENGTH(p_phone_number_list) - LENGTH(REPLACE(p_phone_number_list, ',', ''));
    while i <= v_phone_number_list_size do

        set v_phone_number =  CAST(SUBSTRING_INDEX(p_phone_number_list, ',', 1) AS integer);
        set p_phone_number_list = TRIM(LEADING concat(v_phone_number,',') FROM p_phone_number_list );

        /* get phone_id */
        set v_phone_id = get_phone_id_by_number(v_phone_number);
        if (v_phone_id = -1) then
            return 'error phone id';
        end if;

        /* Insert to Customer Phone */
        set v_customer_phone_return = insert_phone_customer_func(p_customer_id, v_phone_id);
        if (v_customer_phone_return = 'error') then
            return 'error customer phone';
        end if;

        set i = i + 1;
    end while;

    /* deleting unused phone entries */
    call clean_phone_proc();

    return 'inserted customer phone';
END;
$$

CREATE FUNCTION `insert_phone_customer_func`
    (p_customer_id int,
    `p_phone_id` int(10))
    RETURNS varchar(20)
NOT DETERMINISTIC
BEGIN
    DECLARE v_phone_customer_count int;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            return 'error';
        END;

    /* find out if Phone_Customer is already in the database */
    SELECT count(customer_id)
        INTO v_phone_customer_count
        FROM `phone_customer`
        WHERE p_customer_id = customer_id and p_phone_id = phone_id;

    /* Phone_Customer is already in the database, just do an update */
    if (v_phone_customer_count > 0) then
        return 'already in database';
    end if;

    /* Phone_Customer is not in database, so insert it */
    INSERT INTO `phone_customer` (`customer_id`, `phone_id`)
        VALUES (`p_customer_id`, `p_phone_id`);

    return 'inserted';
END;
$$

CREATE FUNCTION `delete_phone_customer_func`
    (p_customer_id varchar(50))
    RETURNS varchar(20)
NOT DETERMINISTIC
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            return 'error';
        END;

    DELETE FROM `phone_customer` 
        WHERE customer_id = p_customer_id;

    /* deleting unused phone entries */
    call clean_phone_proc();

    return 'deleted';
END;
$$

CREATE FUNCTION `get_position_id_by_name`
    (p_position_name varchar(50))
    RETURNS int
NOT DETERMINISTIC
BEGIN
    DECLARE v_position_id int;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            return -1;
        END;

    DECLARE CONTINUE HANDLER FOR NOT FOUND
        BEGIN
            INSERT INTO `position` (`position_name`)
                VALUES (p_position_name);
            SELECT position_id
                INTO v_position_id
                FROM `position`
                WHERE position_name = p_position_name;

            return v_position_id;
        END;

    SELECT position_id
        INTO v_position_id
        FROM `position`
        WHERE position_name = p_position_name;

    return v_position_id;
END;
$$

/* function for QUOTE ***********************************************************************/

CREATE FUNCTION `insert_quote_func`
    (p_quote_name varchar(100),
    p_email varchar(30),
    p_description varchar(2000))
    RETURNS varchar(20)
NOT DETERMINISTIC
BEGIN
     DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
         BEGIN
             return 'error';
         END;

    /* Phone_User is not in database, so insert it*/
    INSERT INTO `quote` (`quote_name`, `email`, `description`)
        VALUES (`p_quote_name`, `p_email`, `p_description`);

    return 'inserted';
END;
$$

CREATE FUNCTION `delete_quote_func`
    (p_quote_name varchar(50))
    RETURNS varchar(20)
NOT DETERMINISTIC
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            return 'error';
        END;

    DELETE FROM `quote` 
        WHERE quote_name = p_quote_name;

    return 'deleted';
END;
$$

/* function for PASSWORD_CHANGE_REQUEST *******************************************************/

CREATE FUNCTION `insert_pcr_func`
    (p_pcr_uuid varchar(128),
    p_pcr_time datetime,
    p_user_name varchar(50))
    RETURNS varchar(20)
NOT DETERMINISTIC
BEGIN
    DECLARE v_pcr_count int;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            return 'error sql exception';
        END;

    /* find out if PCR is already in the database */
    SELECT count(pcr_uuid)
        INTO v_pcr_count
        FROM `password_change_request`
        WHERE p_pcr_uuid = pcr_uuid;

    if (v_pcr_count = 0) then
        /* PCR is not in database, so insert it */
        INSERT INTO `password_change_request` (`pcr_uuid`, `pcr_time`, `user_name`)
            VALUES (`p_pcr_uuid`, `p_pcr_time`, `p_user_name`);
    else
        /* PCR is already in the database, just do an update */
        UPDATE `password_change_request`
            SET pcr_time = p_pcr_time
            WHERE p_pcr_uuid = pcr_uuid;
    end if;

    return 'inserted';
END;
$$

CREATE FUNCTION `delete_pcr_func`
    (p_pcr_uuid varchar(128))
    RETURNS varchar(20)
NOT DETERMINISTIC
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            return 'error';
        END;

    DELETE FROM `password_change_request` 
        WHERE pcr_uuid = p_pcr_uuid;

    return 'deleted';
END;
$$

/* function for JOB ***********************************************************************/
CREATE FUNCTION `insert_job_func`
    (p_job_name varchar(50),
    p_house_number int(50),
    p_street varchar(50),
    p_city varchar(50),
    p_province varchar(20),
    p_country varchar(20),
    p_postal_code varchar(20),
    p_customer_id int,
    p_description varchar(2000),
    p_date_started date,
    p_date_finished date,
    p_balance decimal(20,2),
    p_status varchar(50))
    RETURNS varchar(20)
NOT DETERMINISTIC
BEGIN
    DECLARE v_address_id int;
    DECLARE v_job_count int;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            return 'error sql exception';
        END;

    /* get address_id */
    set v_address_id = insert_address_func(p_house_number, p_street, p_city, p_province, p_country, p_postal_code);
    if (v_address_id = -1) then
        return 'error address id';
    end if;

    /* find out if JOB is already in the database */
    SELECT count(job_name)
        INTO v_job_count
        FROM `job`
        WHERE p_job_name = job_name;

    if (v_job_count = 0) then
        /* JOB is not in database, so insert it */
        INSERT INTO `job` (`job_name`, `address_id`, `customer_id`, `description`, `date_started`, `date_finished`, `balance`, `status`)
            VALUES (`p_job_name`, `v_address_id`, `p_customer_id`, `p_description`, `p_date_started`, `p_date_finished`, `p_balance`, `p_status`);
    else 
        /* JOB is already in the database, just do an update */
        UPDATE `job`
            SET address_id = v_address_id, customer_id = p_customer_id, description = p_description,
                date_started = p_date_started, date_finished = p_date_finished, balance = p_balance, status = p_status
            WHERE p_job_name = job_name;
    end if;

    /* deleting unused address entries */
    call clean_address_proc();

    return 'inserted Job';
END;
$$

CREATE FUNCTION `delete_job_func`
    (p_job_name varchar(50))
    RETURNS varchar(20)
NOT DETERMINISTIC
BEGIN
--     DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
--         BEGIN
--             return 'error';
--         END;

    DELETE FROM `job` 
        WHERE job_name = p_job_name;
 
    /* deleting unused address entries */
    call clean_address_proc();

    return 'deleted';
END;
$$

CREATE FUNCTION `allocate_user_func`
    (p_job_name varchar(50),
    p_user_name varchar(50),
    p_hours int)
    RETURNS varchar(20)
NOT DETERMINISTIC
BEGIN
    DECLARE v_job_user_count int;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
            return 'error sql exception';
        END;

    /* find out if JOB_USER is already in the database */
    SELECT count(job_name)
        INTO v_job_user_count
        FROM `job_user`
        WHERE job_name = p_job_name
            AND user_name = p_user_name;

    if (v_job_user_count = 0) then
        /* JOB_USER is not in database, so insert it */
        INSERT INTO `job_user` (`job_name`, `user_name`, `hours`)
            VALUES (`p_job_name`, `p_user_name`, `p_hours`);
    else 
        /* JOB_USER is already in the database, just do an update */
        UPDATE `job_user`
            SET hours = p_hours
            WHERE job_name = p_job_name
                AND user_name = p_user_name;
    end if;

    return 'allocated user hours';
END;
$$

CREATE FUNCTION `allocate_item_func`
    (p_job_name varchar(50),
    p_item_name varchar(50),
    p_note varchar(2000),
    p_quantity int(5))
    RETURNS varchar(20)
NOT DETERMINISTIC
BEGIN
    DECLARE v_job_item_count int;
    DECLARE v_quantity int;
--     DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
--         BEGIN
--             return 'error sql exception';
--         END;

    /* find out if enough quantiry in ITEM */
    SELECT quantity
        INTO v_quantity
        FROM `item`
            WHERE item_name = p_item_name;

    if (v_quantity >= p_quantity) then
        /* subtracting quantity from ITEM */
        UPDATE `item`
            SET quantity = quantity - p_quantity
            WHERE item_name = p_item_name;
    else 
        return 'insufficient quantity at the inventory!';
    end if;
        
    /* find out if JOB_ITEM is already in the database */
    SELECT count(job_name)
        INTO v_job_item_count
        FROM `job_item`
        WHERE job_name = p_job_name
            AND item_name = p_item_name;

    if (v_job_item_count = 0) then
        /* JOB_ITEM is not in database, so insert it */
        INSERT INTO `job_item` (`job_name`, `item_name`, `note`, `quantity`)
            VALUES (`p_job_name`, `p_item_name`, `p_note`, `p_quantity`);
    else 
        /* JOB_ITEM is already in the database, just do an update */
        UPDATE `job_item`
            SET note = p_note, quantity = p_quantity
            WHERE job_name = p_job_name
                AND item_name = p_item_name;
    end if;

    return 'allocated item';
END;
$$

delimiter ;
