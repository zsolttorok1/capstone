/*CREATE A FUNCTION, REMOVE THE SEQUENCES, ADD A AUTO INCREMENT FOR ADDRESS 
AND PHONE TABLES, THE FUNCTION CALLS THE CURRENT VALUE OF AN ID AND INSERT THAT 
INTO THE DATA */
DROP DATABASE if exists CapstoneDB;
CREATE DATABASE CapstoneDB;
USE CapstoneDB;

SET global sql_mode='STRICT_ALL_TABLES';

/*Table structure for table `item` */

CREATE TABLE `category` (
    `category_id` int NOT NULL AUTO_INCREMENT,
    `category_name` varchar(30) NOT NULL,
    PRIMARY KEY (`category_id`)
);

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
    `item_name` varchar(100) NOT NULL,
    `quantity` int(5) NOT NULL,
    `category_id` int NOT NULL,
    `description` varchar(2000) NOT NULL,
    PRIMARY KEY (`item_name`),
    CONSTRAINT `FK_User_Category_id` FOREIGN KEY (`category_id`) references `category`(`category_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

/* Table structure for table `user` */
CREATE TABLE `report` (
    `report_name` varchar(50) NOT NULL,
    `description` varchar(2000) NOT NULL,
    `date_created` date NOT NULL,
    `pdf_filepath` varchar(50) NOT NULL,
    PRIMARY KEY (`report_name`)
);

/* Table structure for table `phone_number` */
CREATE TABLE `phone` (
    `phone_id` int NOT NULL AUTO_INCREMENT,
    `phone_number` bigint NOT NULL,
    PRIMARY KEY (`phone_id`)
);

/* Table structure for table `position` */
CREATE TABLE `position` (
    `position_id` int NOT NULL AUTO_INCREMENT,
    `position_number` bigint NOT NULL,
    PRIMARY KEY (`position_id`)
);

/* Table structure for table `address` */
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

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `role` (
    `role_id` int(10) NOT NULL AUTO_INCREMENT,
    `role_name` varchar(20) NOT NULL,
    PRIMARY KEY (`role_id`)
);

CREATE TABLE `user` (
    `user_name` varchar(50) NOT NULL,
    `address_id` int(10) NOT NULL,
    `password` varchar(50) NOT NULL,
    `firstname` varchar(50) NOT NULL,
    `lastname` varchar(50) NOT NULL,
    `role_id` int(10) NOT NULL,
    `email` varchar(100) NOT NULL,
    `hourly_rate` decimal(10,2) NOT NULL,
    PRIMARY KEY (`user_name`),
    CONSTRAINT `FK_User_Address_id`  FOREIGN KEY (`address_id`) references `address`(`address_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `FK_User_Role_id`  FOREIGN KEY (`role_id`) references `role`(`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- CONSTRAINT `FK_User_Address_id`  FOREIGN KEY (`address_id`) references `address`(`address_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
--     CONSTRAINT `FK_User_Role_id`  FOREIGN KEY (`role_id`) references `role`(`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT

CREATE TABLE `phone_user` (
    `user_name` varchar(50) NOT NULL,
    `phone_id` int NOT NULL,
    PRIMARY KEY (`user_name`, `phone_id`),
    CONSTRAINT `FK_PU_user_name`  FOREIGN KEY (`user_name`) references `user`(`user_name`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `FK_PU_phone_id` FOREIGN KEY (`phone_id`) references `phone`(`phone_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

/*Table structure for table `customer` */

CREATE TABLE `customer` (
    `customer_name` varchar(50) NOT NULL,
    `job_name` varchar(50) NOT NULL,
    `phone_id` int NOT NULL,
    `address_id` int NOT NULL,
    `firstname` varchar(50) NOT NULL,
    `lastname` varchar(50) NOT NULL,
    `company_name` varchar(50) NOT NULL,
    `email` varchar(100) NOT NULL,
    `position` varchar(50) NULL,
    `notes` varchar (2000) NULL,
    PRIMARY KEY (`customer_name`),
    --CONSTRAINT `FK_Customer_Job_name` FOREIGN KEY (`job_name`) references `job`(`job_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `FK_Customer_Phone_id` FOREIGN KEY (`phone_id`) references `phone`(`phone_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `FK_Customer_Address_id` FOREIGN KEY (`address_id`) references `address`(`address_id`)  ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `phone_customer` (
    `customer_name` varchar(50) NOT NULL,
    `phone_id` int NOT NULL,
    PRIMARY KEY (`customer_name`, `phone_id`),
    CONSTRAINT `FK_PC_customer_name`  FOREIGN KEY (`customer_name`) references `customer`(`customer_name`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `FK_PC_phone_id` FOREIGN KEY (`phone_id`) references `phone`(`phone_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

/*Table structure for table `job` */

DROP TABLE IF EXISTS `job`;

CREATE TABLE `job` (
    `job_name` varchar(50) NOT NULL,
    `address_id` int NOT NULL,
    `customer_name` varchar(50) NOT NULL,
    `report_name` varchar(50) NOT NULL,
    `description` varchar(2000) NULL, 
    `date_started` date NOT NULL,
    `date_finished` date NULL,
    `balance` int NOT NULL,
    `status` varchar(50) NULL,
    PRIMARY KEY (`job_name`),
    CONSTRAINT `FK_Job_Address_id` FOREIGN KEY (`address_id`) references `address`(`address_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `FK_Job_Customer_name` FOREIGN KEY (`customer_name`) references `customer`(`customer_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `FK_Job_Report_name` FOREIGN KEY (`report_name`) references `report`(`report_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `job_user`;

CREATE TABLE `job_user` (
    `user_name` varchar(50) NOT NULL,
    `job_name` varchar(50) NOT NULL,
    `hours` int NOT NULL,
    PRIMARY KEY (`user_name`, `job_name`),
    --PRIMARY KEY (`job_name`),
    CONSTRAINT `FK_Job_User_User_name` FOREIGN KEY (`user_name`) references `user`(`user_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `FK_Job_User_Job_name` FOREIGN KEY (`job_name`) references `job`(`job_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `job_item`;

CREATE TABLE `job_item` (
    `job_name` varchar(50) NOT NULL,
    `item_name` varchar(100) NOT NULL,
    `note` varchar(2000) NULL,
    `quantity` int(5) NOT NULL,
    PRIMARY KEY (`job_name`, `item_name`),
    --PRIMARY KEY (`item_name`),
    CONSTRAINT `FK_Job_Item_Job_name` FOREIGN KEY (`job_name`) references `job`(`job_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `FK_Job_Item_Item_name` FOREIGN KEY (`item_name`) references `item`(`item_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

/* Table structure for table `quote` */
CREATE TABLE `quote` (
    `name` varchar(100) NOT NULL,
    `description` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL,
    PRIMARY KEY (`name`)
);

--Add Contraint for job 
--CONSTRAINT `FK_Customer_Job_name` FOREIGN KEY (`job_name`) references `job`(`job_name`) ON DELETE RESTRICT ON UPDATE RESTRICT

ALTER TABLE customer
ADD CONSTRAINT `FK_Customer_Job_name`
FOREIGN KEY (`job_name`) references `job`(`job_name`);

/*FUNCTION FOR INSERTING ON THE PHONE AND ADDRESS */
DELIMITER $$
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

-- /*duplicate PK found */ DECLARE CONTINUE HANDLER FOR 1062

/* functions for ITEM */
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
    return 'updated';
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
END;
$$

CREATE PROCEDURE `clean_category_proc`()
BEGIN
    /* deleting unused category entries */
    DELETE FROM category
        WHERE category_id NOT IN
        (SELECT category_id FROM item);
END;
$$

CREATE TRIGGER insert_item_clean_trig
    AFTER INSERT ON `item` 
    FOR EACH ROW
BEGIN
    CALL clean_category_proc();
END;
$$

CREATE TRIGGER update_item_clean_trig
    AFTER UPDATE ON `item` 
    FOR EACH ROW
BEGIN
    CALL clean_category_proc();
END;
$$

CREATE TRIGGER delete_item_clean_trig
    AFTER DELETE ON `item` 
    FOR EACH ROW
BEGIN
    CALL clean_category_proc();
END;
$$

/* function for USER */
CREATE FUNCTION `insert_user_func`
    (p_user_name varchar(50),
    p_house_number int(50),
    p_street varchar(50),
    p_city varchar(50),
    p_province varchar(20),
    p_country varchar(20),
    p_postal_code varchar(20),
    p_password varchar(50),
    p_firstname varchar(50),
    p_lastname varchar(50),
    p_role_name varchar(20),
    p_email varchar(100),
    p_hourly_rate decimal(10,2))
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
        INSERT INTO `user` (`user_name`, `address_id`, `password`, `firstname`, `lastname`, `role_id`, `email`, `hourly_rate`)
            VALUES (`p_user_name`, `v_address_id`, `p_password`, `p_firstname`, `p_lastname`, `v_role_id`, `p_email`, `p_hourly_rate`);
    else 
        /* User is already in the database, just do an update */
        UPDATE `user`
            SET address_id = v_address_id, password = p_password, firstname = p_firstname,
                lastname = p_lastname, role_id = v_role_id, email = p_email, hourly_rate = p_hourly_rate
            WHERE p_user_name = user_name;
    end if;

    CALL clean_address_proc();

    return 'inserted user';
END;
$$

/* function for USER_PHONE */
CREATE FUNCTION `insert_user_phone_func`
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

    CALL clean_phone_proc();

    return 'inserted user phone';
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

    CALL clean_phone_proc();

    return 'inserted';
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
    return 'deleted';

    CALL clean_address_proc();
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
    return 'deleted';

    CALL clean_phone_proc();
END;
$$

CREATE PROCEDURE `clean_phone_proc`()
BEGIN
    /* deleting unused phone entries */
    DELETE FROM phone
        WHERE phone_id NOT IN
        (SELECT phone_id FROM `phone_user`);
END;
$$

CREATE PROCEDURE `clean_address_proc`()
BEGIN
    /* deleting unused address entries */
    DELETE FROM address
        WHERE address_id NOT IN
        (SELECT address_id FROM `user`);
END;
$$

-- CREATE TRIGGER update_phone_user_clean_trig
--     AFTER UPDATE ON `phone_user` 
--     FOR EACH ROW
-- BEGIN
--     CALL clean_phone_proc();
-- END;
-- $$

/*
    `customer_name` varchar(50) NOT NULL,
    `job_name` varchar(50) NOT NULL,
    `phone_id` int NOT NULL,
    `address_id` int NOT NULL,
    `firstname` varchar(50) NOT NULL,
    `lastname` varchar(50) NOT NULL,
    `company_name` varchar(50) NOT NULL,
    `email` varchar(100) NOT NULL,
    `position` varchar(50) NULL,
    `notes` varchar (2000) NULL,
*/

/* function for CUSTOMER */
-- CREATE FUNCTION `insert_customer_func`
--     (p_customer_name varchar(50),
--     p_house_number int(50),
--     p_street varchar(50),
--     p_city varchar(50),
--     p_province varchar(20),
--     p_country varchar(20),
--     p_postal_code varchar(20),
--     p_phone_number bigint,
--     p_password varchar(50),
--     p_firstname varchar(50),
--     p_lastname varchar(50),
--     p_role_name varchar(20),
--     p_email varchar(100),
--     p_position_name varchar(50),
--     p_notes varchar (2000))
--     RETURNS varchar(20)
-- NOT DETERMINISTIC
-- BEGIN
--     DECLARE v_position_id int;
--     DECLARE v_phone_id int;
--     DECLARE v_address_id int;
--     DECLARE v_customer_count int;
--     DECLARE v_customer_phone_return varchar(20);
--     DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
--         BEGIN
--             return 'error sql exception';
--         END;
-- 
--     /* get address_id */
--     set v_address_id = insert_address_func(p_house_number, p_street, p_city, p_province, p_country, p_postal_code);
--     if (v_address_id = -1) then
--         return 'error address id';
--     end if;
-- 
--     /* get position_id */
--     set v_position_id = get_position_id_by_name(p_position_name);
--     if (v_position_id = -1) then
--         return 'error position id';
--     end if;
-- 
--     /* get phone_id */
--     set v_phone_id = get_phone_id_by_number(p_phone_number);
--     if (v_phone_id = -1) then
--         return 'error phone id';
--     end if;
-- 
--     /* find out if Customer is already in the database */
--     SELECT count(customer_name)
--         INTO v_customer_count
--         FROM `customer`
--         WHERE p_customer_name = customer_name;
-- 
--     if (v_customer_count = 0) then
--         /* Customer is not in database, so insert it */
--         INSERT INTO `user` (`customer_name`, `address_id`, `password`, `firstname`, `lastname`, `position_id`, `email`, `notes`)
--             VALUES (`p_customer_name`, `v_address_id`, `p_password`, `p_firstname`, `p_lastname`, `v_position_id`, `p_email`, `p_notes`);
--     else 
--         /* Customer is already in the database, just do an update */
--         UPDATE `customer`
--             SET customer_id = v_customer_id, password = p_password, firstname = p_firstname,
--                 lastname = p_lastname, position_id = v_position_id, email = p_email, notes = p_notes
--             WHERE p_customer_name = customer_name;
--     end if;
-- 
--     /* Insert to Customer Phone */
--     set v_customer_phone_return = insert_phone_customer_func(p_customer_name, v_customer_id);
--     if (v_customer_phone_return = 'error') then
--         return 'error user phone';
--     end if;
-- 
--     return 'inserted';
-- END;
-- $$

-- CREATE FUNCTION `get_position_id_by_name`
--     (p_position_name varchar(50))
--     RETURNS int
-- NOT DETERMINISTIC
-- BEGIN
--     DECLARE v_position_id int;
-- 
--     DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
--         BEGIN
--             return -1;
--         END;
-- 
--     DECLARE CONTINUE HANDLER FOR NOT FOUND
--         BEGIN
--             INSERT INTO `position` (`position_name`)
--                 VALUES (p_position_name);
--             SELECT position_id
--                 INTO v_position_id
--                 FROM `position`
--                 WHERE position_name = p_position_name;
-- 
--             return v_position_id;
--         END;
-- 
--     SELECT position_id
--         INTO v_position_id
--         FROM position
--         WHERE position_name = p_position_name;
-- 
--     return v_position_id;
-- END;
-- $$


delimiter ;

/* adding USERS */
select insert_user_func ('andrew_grieve', 236, '78th Ave NE', 'Calgary', 'Alberta', 'Canada', 'T2K0R4', 'Green2012', 'Andrew', 'Grieve', 'owner', 'agrieve2@hotmail.com', 90.32);
select insert_user_phone_func ('andrew_grieve', '4038077189,4038077111,4038077222,');
--insert into `job_user` (`user_name`, `job_name`, `hours`)
--    values ('andrew_grieve', 'Brookfield Bathroom on WestTower', 0);

-- select insert_user_func ('james_grieve', 236, '78th Ave NE', 'Calgary', 'Alberta', 'Canada', 'T2K0R4', 'James11', 'James', 'Grieve', 'manager', 'darklink44459@hotmail.com', 23.41);
-- select insert_user_phone_func ('andrew_grieve', '4034879866,');
-- --insert into `job_user` (`user_name`, `job_name`, `hours`)
-- --   values ('james_grieve', 'Brookfield Bathroom on WestTower', 0);
-- 
-- select insert_user_func ('kayla_grieve', 236, '78th Ave NE', 'Calgary', 'Alberta', 'Canada', 'T2K0R4', 'Kayla11', 'Kayla', 'Grieve', 'employee', 'link44459@hotmail.com', 18.32);
-- select insert_user_phone_func ('andrew_grieve', '4037778620,');
-- -- insert into `job_user` (`user_name`, `job_name`, `hours`)
-- --     values ('kayla_grieve', 'Brookfield Bathroom on WestTower', 0);
-- 
-- /* adding ITEMS */
-- select insert_item_func ('SuperFine Paint Brush', 22, 'Brushes', 'We use this to paint fur.');
-- select insert_item_func ('Thick Master 2000', 5, 'Brushes', 'Great for making solid straight strokes.');
-- select insert_item_func ('Hairy Harold', 5, 'Brushes', 'We dont use this on walls.');
-- select insert_item_func ('Hairy Harold', 2, 'Brushes', 'We dont use this on walls, way too hairy.');
-- select insert_item_func ('Devil Beater', 5, 'Brushes', 'Bob Ross favorite.');
-- 
-- INSERT INTO `category` (`category_id`, `category_name`) VALUES ('3', 'Spoiled Milk');
-- INSERT INTO `category` (`category_id`, `category_name`) VALUES ('10', 'Doesnt exist anymore');
