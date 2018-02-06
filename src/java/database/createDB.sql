CREATE DATABASE `user`, `phone_number`, `job_user`, `address`, `job`, `customer`, `report`, `job_item`, `item`;

/*SEQUENCE FOR INCREMENT ID */
CREATE SEQUENCE seq_phone
MINVALUE 1 
START WITH 1 
INCREMENT BY 1 
CACHE 10;

CREATE SEQUENCE seq_address
MINVALUE 1 
START WITH 1 
INCREMENT BY 1 
CACHE 10;


USE `item`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
    `item_name` varchar(100) NOT NULL,
    `quantity` number(5) NOT NULL,
    `category` varchar(30) NOT NULL,
    `description` varchar(2000) NULL,

    PRIMARY KEY (`item_name`),
) 
ENGINE=InnoDB DEFAULT CHARSET=utf8;


USE `report`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `report`;

CREATE TABLE `report` (
    `report_name` varchar(50) NOT NULL,
    `description` varchar(2000) NOT NULL,
    `date_created` date NOT NULL,
    `pdf_filepath` varchar(50) NOT NULL,

    PRIMARY KEY (`report_name`)
) 
ENGINE=InnoDB DEFAULT CHARSET=utf8;

USE `phone_number`;

/*Table structure for table `phone_number` */

DROP TABLE IF EXISTS `phone_number`;

CREATE TABLE `phone_number` (
    `phone_id` number NOT NULL,
    `phone_number` number NOT NULL,
    PRIMARY KEY (`phone_id`)
) 
ENGINE=InnoDB DEFAULT CHARSET=utf8;

USE `address`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
    `address_id` number NOT NULL,
    `house_number` number NOT NULL,
    `street` varchar(50) NOT NULL,
    `city` varchar(50) NOT NULL,
    `province`varchar(20) NOT NULL,
    `country` varchar(20) NOT NULL,
    `postal_code` varchar(20) NOT NULL,
    PRIMARY KEY (`address_id`),
) 
ENGINE=InnoDB DEFAULT CHARSET=utf8;

USE `user`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
    `user_name` varchar(50) NOT NULL,
    `address_id` number NULL,
    `phone_id` number NULL,
    `password` varchar(50) NOT NULL,
    `firstname` varchar(50) NOT NULL,
    `lastname` varchar(50) NOT NULL,
    `role` varchar(20) NOT NULL,
    `email` varchar(100) NOT NULL,

    PRIMARY KEY (`user_name`),
    FOREIGN KEY (`address_id`) references address(`address_id`),
    FOREIGN KEY (`phone_id`) references phone_number(`phone_id`)
) 
ENGINE=InnoDB DEFAULT CHARSET=utf8;




USE `customer`;

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
    `customer_name` varchar(50) NOT NULL,
    `job_name` varchar(50) NOT NULL,
    `phone_id` number NOT NULL,
    `address_id` number NOT NULL,
    `firstname` varchar(50) NOT NULL,
    `lastname` varchar(50) NOT NULL,
    `company_name` varchar(50) NOT NULL,
    `email` varchar(100) NOT NULL,
    `position` varchar(50) NULL,
    `notes` varchar (2000) NULL,
    
    PRIMARY KEY (`customer_name`),
    FOREIGN KEY (`job_name`) references job(`job_name`),
    FOREIGN KEY (`phone_id`) references phone_number(`phone_id`),
    FOREIGN KEY (`address_id`) references address(`address_id`)
) 
ENGINE=InnoDB DEFAULT CHARSET=utf8;


USE `job`;

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `job`;

CREATE TABLE `job` (
    `job_name` varchar(50) NOT NULL,
    `address_id` number NOT NULL,
    `customer_name` varchar(50) NOT NULL,
    `report_name` varchar(50) NOT NULL,
    `description` varchar(2000) NULL, 
    `date_started` date NOT NULL,
    `date_finished` date NULL,
    `balance` number NOT NULL,
    `status` varchar(50) NULL,
   
    PRIMARY KEY (`job_name`),
    FOREIGN KEY (`address_id`) references address(`address_id`),
    FOREIGN KEY (`customer_name`) references customer(`customer_name`),
    FOREIGN KEY (`report_name`) references report(`report_name`)
) 
ENGINE=InnoDB DEFAULT CHARSET=utf8;

USE `job_user`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `job_user`;

CREATE TABLE `job_user` (
    `user_name` varchar(50) NOT NULL,
    `job_name` varchar(50) NOT NULL,
    `hours` number NOT NULL,
    PRIMARY KEY (`user_name`),
    PRIMARY KEY (`job_name`),
    FOREIGN KEY (`user_name`) references user(`user_name`),
    FOREIGN KEY (`job_name`) references job(`job_name`)
) 
ENGINE=InnoDB DEFAULT CHARSET=utf8;

USE `job_item`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `job_item`;

CREATE TABLE `job_item` (
    `job_name` varchar(50) NOT NULL,
    `item_name` varchar(100) NOT NULL,
    `note` varchar(2000) NULL,
    `quantity` number(5) NOT NULL,

    PRIMARY KEY (`job_name`),
    PRIMARY KEY (`item_name`),
    FOREIGN KEY (`job_name`) references job(`job_name`),
    FOREIGN KEY (`item_name`) references item(`item_name`)
) 
ENGINE=InnoDB DEFAULT CHARSET=utf8;



/*INSERTS-we need to add a trigger for the sequences */
/*Data for the table `user` */

insert into `user` (`user_name`, `address_id`, `phone_id`, `password`, `firstname`, `lastname`, `role`, `email`)
    values ('andrew_grieve', seq_address.NEXTVAL, seq_phone.NEXTVAL, 'Green2012', 'Andrew', 'Grieve','owner' , 'agrieve2@hotmail.com');
insert into `user` (`user_name`, `address_id`, `phone_id`, `password`, `firstname`, `lastname`, `role`, `email`)
    values ('james_grieve',  seq_address.NEXTVAL, seq_phone.NEXTVAL, 'James11', 'James', 'Grieve','manager' , 'darklink44459@hotmail.com');
insert into `user` (`user_name`, `address_id`, `phone_id`, `password`, `firstname`, `lastname`, `role`, `email`)
    values ('kayla_grieve',  seq_address.NEXTVAL, seq_phone.NEXTVAL, 'Kayla11', 'Kayla', 'Grieve','employee' , 'link44459@hotmail.com');

/*Data for the table `job_user` */
insert into `job_user` (`user_name`, `job_name`, `hours`)
    values ('james_grieve', 'Brookfield Bathroom on WestTower', 20);

/*Data for the table `address` */
insert into `address` (`house_number`, `address_id`, `street`, `city`, `province`, `country`, `postal_code`)
    values (236, '78th Ave NE', 'Calgary', 'Alberta', 'Canada', 'T2K0R4');

/*Data for the table `phone_number` */
insert into `phone_number` (`phone_number`)jja
    values ();


