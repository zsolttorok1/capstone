/*CREATE A FUNCTION, REMOVE THE SEQUENCES, ADD A AUTO INCREMENT FOR ADDRESS 
AND PHONE TABLES, THE FUNCTION CALLS THE CURRENT VALUE OF AN ID AND INSERT THAT 
INTO THE DATA */
DROP DATABASE if exists CapstoneDB;
CREATE DATABASE CapstoneDB;
USE CapstoneDB;


/*Table structure for table `user` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
    `item_name` varchar(100) NOT NULL,
    `quantity` int(5) NOT NULL,
    `category` varchar(30) NOT NULL,
    `description` varchar(2000) NOT NULL,
    PRIMARY KEY (`item_name`)
);



/*Table structure for table `user` */



CREATE TABLE `report` (
    `report_name` varchar(50) NOT NULL,
    `description` varchar(2000) NOT NULL,
    `date_created` date NOT NULL,
    `pdf_filepath` varchar(50) NOT NULL,
    PRIMARY KEY (`report_name`)
);

/*Table structure for table `phone_number` */

CREATE TABLE `phone` (
    `phone_id` int NOT NULL AUTO_INCREMENT,
    `phone_number` bigint NOT NULL,
    PRIMARY KEY (`phone_id`)
);

/*Table structure for table `address` */


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

CREATE TABLE `user` (
    `user_name` varchar(50) NOT NULL,
    `address_id` int NULL,
    `phone_id` int NULL,
    `password` varchar(50) NOT NULL,
    `firstname` varchar(50) NOT NULL,
    `lastname` varchar(50) NOT NULL,
    `role` varchar(20) NOT NULL,
    `email` varchar(100) NOT NULL,
    PRIMARY KEY (`user_name`),
   CONSTRAINT `FK_User_Address_id`  FOREIGN KEY (`address_id`) references `address`(`address_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
   CONSTRAINT `FK_User_Phone_id` FOREIGN KEY (`phone_id`) references `phone`(`phone_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
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
    CONSTRAINT `FK_Customer_Phone_id` FOREIGN KEY (`phone_id`) references `phone`(`phone_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `FK_Customer_Address_id` FOREIGN KEY (`address_id`) references `address`(`address_id`)  ON DELETE RESTRICT ON UPDATE RESTRICT
);



/*Table structure for table `customer` */

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

CREATE PROCEDURE `insert_item_proc`
    (IN p_item_name varchar(100),
    IN p_quantity int(5),
    IN p_categoty varchar(30),
    IN p_description varchar(2000),
    OUT p_return int)
BEGIN
    declare v_item_count int;
    declare v_old_description_count int;
    declare v_new_description_count int;

    select sum(item_name)
        into v_item_count
        from item
        where p_item_name = item_name;

    if v_item_count > 0 then
        select CHAR_LENGTH(description)
            into v_old_description_count
            from item
            where p_item_name = item_name;

        set v_new_description_count = CHAR_LENGTH(p_description);

        if v_new_description_count > v_old_description_count then
            UPDATE item
                SET description = p_description;
        UPDATE item
            SET quantity = quantity + p_quantity, category = p_categoty;
        end if;

        set p_return = 1;
    else
        insert into `item` (`item_name`, `quantity`, `category`, `description`)
            values (p_item_name, p_quantity, p_categoty, p_description);
        set p_return = 1;
    end if;
END;
$$

delimiter ;
 
/*INSERTS-we need to add a trigger for the sequences */

/*USER ONE*/
/*Data for the table `address` */
insert into `address` (`house_number`,  `street`, `city`, `province`, `country`, `postal_code`)
    values (236, '78th Ave NE', 'Calgary', 'Alberta', 'Canada', 'T2K0R4');

/*Data for the table `phone` 4038077189 */
insert into `phone` (`phone_number`)
    values (4038077189);

call address_proc(@address_new);
call phone_proc(@phone_new);

/*Data for the table `user` */
insert into `user` (`user_name`, `address_id`, `phone_id`, `password`, `firstname`, `lastname`, `role`, `email`)
    values ('andrew_grieve', @address_new, @phone_new, 'Green2012', 'Andrew', 'Grieve','owner' , 'agrieve2@hotmail.com');

/* adding some items */ 
insert into `item` (`item_name`, `quantity`, `category`, `description`)
    values ('SuperFine Paint Brush', 22, 'Brushes', 'We use this to paint fur.');

insert into `item` (`item_name`, `quantity`, `category`, `description`)
    values ('Thick Master 2000', 5, 'Brushes', 'Great for making solid straight strokes.');


/*Data for the table `job_user` */
--insert into `job_user` (`user_name`, `job_name`, `hours`)
--    values ('andrew_grieve', 'Brookfield Bathroom on WestTower', 0);

/*USER TWO*/
/*Data for the table `address` */
insert into `address` (`house_number`,  `street`, `city`, `province`, `country`, `postal_code`)
    values (236, '78th Ave NE', 'Calgary', 'Alberta', 'Canada', 'T2K0R4');
-- 
-- /*Data for the table `phone_number` */
insert into `phone` (`phone_number`)
     values (4034879866);

call address_proc(@address_new);
call phone_proc(@phone_new);

-- /*Data for the table `user` */
insert into `user` (`user_name`, `address_id`, `phone_id`, `password`, `firstname`, `lastname`, `role`, `email`)
   values ('james_grieve',  @address_new, @phone_new, 'James11', 'James', 'Grieve','manager' , 'darklink44459@hotmail.com');

-- /*Data for the table `job_user` */
--insert into `job_user` (`user_name`, `job_name`, `hours`)
--   values ('james_grieve', 'Brookfield Bathroom on WestTower', 0);

-- /*USER THREE*/	

-- /*Data for the table `address` */
insert into `address` (`house_number`,  `street`, `city`, `province`, `country`, `postal_code`)
    values (236, '78th Ave NE', 'Calgary', 'Alberta', 'Canada', 'T2K0R4');

call address_proc(@address_new);
call phone_proc(@phone_new);
-- /*Data for the table `phone_number` */
insert into `phone` (`phone_number`)
    values (4037778620);
-- 
-- /*Data for the table `user` */
insert into `user` (`user_name`, `address_id`, `phone_id`, `password`, `firstname`, `lastname`, `role`, `email`)
  values ('kayla_grieve',  @address_new, @phone_new, 'Kayla11', 'Kayla', 'Grieve','employee' , 'link44459@hotmail.com');
-- 
-- /*Data for the table `job_user` */
-- insert into `job_user` (`user_name`, `job_name`, `hours`)
--     values ('kayla_grieve', 'Brookfield Bathroom on WestTower', 0);
-- 

