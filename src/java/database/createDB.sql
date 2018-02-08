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
    `description` varchar(2000) NULL,
    PRIMARY KEY (`item_name`)
);



/*Table structure for table `user` */

DROP TABLE IF EXISTS `report`;

CREATE TABLE `report` (
    `report_name` varchar(50) NOT NULL,
    `description` varchar(2000) NOT NULL,
    `date_created` date NOT NULL,
    `pdf_filepath` varchar(50) NOT NULL,
    PRIMARY KEY (`report_name`)
);

/*Table structure for table `phone_number` */

DROP TABLE IF EXISTS `phone_number`;

CREATE TABLE `phone_number` (
    `phone_id` int NOT NULL AUTO_INCREMENT,
    `phone_number` int NOT NULL,
    PRIMARY KEY (`phone_id`)
);

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
    `address_id` int NOT NULL AUTO_INCREMENT,
    `house_int` int NOT NULL,
    `street` varchar(50) NOT NULL,
    `city` varchar(50) NOT NULL,
    `province`varchar(20) NOT NULL,
    `country` varchar(20) NOT NULL,
    `postal_code` varchar(20) NOT NULL,
    PRIMARY KEY (`address_id`)
) 


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
   CONSTRAINT `FK_User_Address_id`  FOREIGN KEY (`address_id`) references address(`address_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
   CONSTRAINT `FK_User_Phone_id` FOREIGN KEY (`phone_id`) references phone_number(`phone_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) 

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

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
    CONSTRAINT `FK_Customer_Job_name` FOREIGN KEY (`job_name`) references job(`job_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `FK_Customer_Phone_id` FOREIGN KEY (`phone_id`) references phone_number(`phone_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `FK_Customer_Address_id` FOREIGN KEY (`address_id`) references address(`address_id`)  ON DELETE RESTRICT ON UPDATE RESTRICT
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
    CONSTRAINT `FK_Job_Address_id` FOREIGN KEY (`address_id`) references address(`address_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `FK_Job_Customer_name` FOREIGN KEY (`customer_name`) references customer(`customer_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `FK_Job_Report_name` FOREIGN KEY (`report_name`) references report(`report_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `job_user`;

CREATE TABLE `job_user` (
    `user_name` varchar(50) NOT NULL,
    `job_name` varchar(50) NOT NULL,
    `hours` int NOT NULL,
    PRIMARY KEY (`user_name`),
    PRIMARY KEY (`job_name`),
    CONSTRAINT `FK_Job_User_User_name` FOREIGN KEY (`user_name`) references user(`user_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `FK_Job_User_Job_name` FOREIGN KEY (`job_name`) references job(`job_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `job_item`;

CREATE TABLE `job_item` (
    `job_name` varchar(50) NOT NULL,
    `item_name` varchar(100) NOT NULL,
    `note` varchar(2000) NULL,
    `quantity` int(5) NOT NULL,
    PRIMARY KEY (`job_name`),
    PRIMARY KEY (`item_name`),
    CONSTRAINT `FK_Job_Item_Job_name` FOREIGN KEY (`job_name`) references job(`job_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `FK_Job_Item_Item_name` FOREIGN KEY (`item_name`) references item(`item_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
);
/*FUNCTION FOR INSERTING ON THE PHONE AND ADDRESS */

CREATE OR REPLACE procedure addressProc
 (address_id OUT number)
 
 RETURN int
 IS
 addressNew int;
 
BEGIN
	Select address_id
	INTO addressNew
	from address;
 
END;
/

CREATE OR REPLACE procedure phoneProc
 (phone_id OUT number)
 
 RETURN int
 IS
 phoneNew int;
 
BEGIN
	Select phone_id
	INTO phoneNew
	from phone_number;
 
END;
/

/*INSERTS-we need to add a trigger for the sequences */

/*USER ONE*/
/*Data for the table `address` */
insert into `address` (`house_number`,  `street`, `city`, `province`, `country`, `postal_code`)
    values (236, '78th Ave NE', 'Calgary', 'Alberta', 'Canada', 'T2K0R4');

/*Data for the table `phone_number` */
insert into `phone_number` (`phone_number`)
    values (4038077189);

/*Data for the table `user` */
insert into `user` (`user_name`, `address_id`, `phone_id`, `password`, `firstname`, `lastname`, `role`, `email`)
    values ('andrew_grieve', addressProc(), seq_phone.CURVAL, 'Green2012', 'Andrew', 'Grieve','owner' , 'agrieve2@hotmail.com');

/*Data for the table `job_user` */
insert into `job_user` (`user_name`, `job_name`, `hours`)
    values ('andrew_grieve', 'Brookfield Bathroom on WestTower', 0);


/*USER TWO*/
/*Data for the table `address` */
insert into `address` (`house_number`,  `street`, `city`, `province`, `country`, `postal_code`)
    values (236, '78th Ave NE', 'Calgary', 'Alberta', 'Canada', 'T2K0R4');

/*Data for the table `phone_number` */
insert into `phone_number` (`phone_number`)
    values (4034879866);

/*Data for the table `user` */
insert into `user` (`user_name`, `address_id`, `phone_id`, `password`, `firstname`, `lastname`, `role`, `email`)
    values ('james_grieve',  seq_address.CURVAL, seq_phone.CURVAL, 'James11', 'James', 'Grieve','manager' , 'darklink44459@hotmail.com');

/*Data for the table `job_user` */
insert into `job_user` (`user_name`, `job_name`, `hours`)
    values ('james_grieve', 'Brookfield Bathroom on WestTower', 0);



/*USER THREE*/	

/*Data for the table `address` */
insert into `address` (`house_number`,  `street`, `city`, `province`, `country`, `postal_code`)
    values (236, '78th Ave NE', 'Calgary', 'Alberta', 'Canada', 'T2K0R4');

/*Data for the table `phone_number` */
insert into `phone_number` (`phone_number`)
    values (4037778620);

/*Data for the table `user` */
insert into `user` (`user_name`, `address_id`, `phone_id`, `password`, `firstname`, `lastname`, `role`, `email`)
    values ('kayla_grieve',  seq_address.CURVAL, seq_phone.CURVAL, 'Kayla11', 'Kayla', 'Grieve','employee' , 'link44459@hotmail.com');

/*Data for the table `job_user` */
insert into `job_user` (`user_name`, `job_name`, `hours`)
    values ('kayla_grieve', 'Brookfield Bathroom on WestTower', 0);


