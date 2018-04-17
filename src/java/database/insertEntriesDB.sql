/* 
 * This script adds some initial values to tables to make the database usable and testable.
 */

/* adding USERS ***********************************************************************/
select insert_user_func ('terminator_bro', 236, '44th Ave NW', 'Calgary', 'Alberta', 'Canada', 'T1E0R4', '80A3A371F15D8C03FE6691CFB46F46D479346C3EE63F81CFCBAF32C81CA0D9C6EB8857FE18C1D169753FBE9DBABB74551F1BEC824003D178A5DAD5FB6DEB4F25', 'Hill', 'Billy', 'employee', 'walltermin@hotmail.com', 30.12, '4d5eac44fd0a56b786b0f2fe40ff3561');
select insert_phoneList_user_func ('terminator_bro', '4031112211,4032221122,');

select insert_user_func ('andrew', 236, '78th Ave NE', 'Calgary', 'Alberta', 'Canada', 'T2K0R4', '6708E2B3387887457208DADA0E4D579E7CB69FAC79647AB817974A75668DBD9FF129771932642523B5C83EF70A6DDA02651C06893E9112581D3680C3F42639A6', 'Andrew', 'Grieve', 'owner', 'agrieve2@hotmail.com', 90.32, '67844c34e0de7183a88fc836d3d949e3');
select insert_phoneList_user_func ('andrew', '4038177199,4038077111,4038077222,');

select insert_user_func ('james', 236, '78th Ave NE', 'Calgary', 'Alberta', 'Canada', 'T2K0R4', '5E00FF893727F8E79007B47963585C8CA9347F3E9D018D6FFCA2067A4704C2EA8970C99725944334A67CED062DF7FB246573EC4756D190AABB8745DD60596B5D', 'James', 'Grieve', 'manager', 'darklink44459@hotmail.com', 23.41, 'bb2642cd9e01fe3a178d73e865771c3d');
select insert_phoneList_user_func  ('james', '4034871811,');

select insert_user_func ('kayla', 236, '78th Ave NE', 'Calgary', 'Alberta', 'Canada', 'T2K0R4', '9F9D0635E4F9213EF0AC90FEB5C40B2B9F454B3C314AB2BE6E31F2DE6FB6CEBB7BA2B8F75A094B5F27EBD6270E77EADE0E68AC305CC7D534C1F8E6B80CF8AADC', 'Kayla', 'Grieve', 'employee', 'link44459@hotmail.com', 18.32, '2b6b55a74fedd32349f7cfe65b9477a5');
select insert_phoneList_user_func  ('kayla', '4031771620,');

select insert_user_func ('zsto', 236, '28th Ave NE', 'Calgary', 'Alberta', 'Canada', 'T111R4', 'DF40835B6D3527A30EFB611AD090EEA608D35160655F846C91ECBCBA4883DEDC67B63260E58EE1AE88B1D7033BF703AA4BCEC5E7C52ECFD797653EBC9044818B', 'Zsolt', 'Torok', 'owner', 'psi.zsolt@gmail.com', 11.11, '67844c34e0de7113a88fc136d3d949e3');
select insert_phoneList_user_func ('zsto', '4035555559,');

/* adding CUSTOMERS ***********************************************************************/
select insert_customer_func (1, 222, '40th Ave NW', 'Calgary', 'Alberta', 'Canada', 'T1E5E1', 'Debra', 'Frank', 'Heavy Grip Corp.', 'dbeater@hotmail.com', 'CEO', 'Red hair, blue eyes. Talks very fast.');
select insert_phoneList_customer_func (1, '4031231234,40399880011,');

select insert_customer_func (2, 232, '10th Ave NW', 'Calgary', 'Alberta', 'Canada', 'T1E521', 'Fukushima', 'John', 'Lollers Corp.', 'lollers@hotmail.com', 'Manager', 'The dude.');
select insert_phoneList_customer_func (2, '4032223333,4039822222,5879999429,');

/* adding ITEMS ***********************************************************************/
select insert_item_func ('SuperFine Paint Brush', 22, 'Brushes', 'We use this to paint fur.');
select insert_item_func ('Thick Master 2000', 5, 'Brushes', 'Great for making solid straight strokes.');
select insert_item_func ('Hairy Harold', 5, 'Brushes', 'We dont use this on walls.');
select insert_item_func ('Hairy Harold', 2, 'Brushes', 'We dont use this on walls, way too hairy.');
select insert_item_func ('Devil Beater', 5, 'Brushes', 'Bob Ross favorite.');

-- INSERT INTO `category` (`category_id`, `category_name`) VALUES ('3', 'Spoiled Milk');
-- INSERT INTO `category` (`category_id`, `category_name`) VALUES ('10', 'Doesnt exist anymore');

/* adding JOBS ***********************************************************************/
/*Date: YY-MM-DD */
select insert_job_func ('First Job on main street', 555, '123th Ave NE', 'Calgary', 'Alberta', 'Canada', 'T5K2R8', 2, 'This is a nice and detailed job description.', '18/03/28', '18/03/30', 0, 'unpaid');
select insert_job_func ('First Job on main street', 555, '123th Ave NE', 'Calgary', 'Alberta', 'Canada', 'T5K2R8', 2, 'This is a nice and detailed job description, updated.', '18-03-28', '18-03-30', 0, 'unpaid');
select insert_job_func ('Second Huge Job', 555, '999th Ave SW', 'Calgary', 'Alberta', 'Canada', 'T9D8Y1', 1, 'Sent from future ya.', '19-04-10', '19-04-20', 29000, 'paid');
select insert_job_func ('Brookfield Bathroom on WestTower', 100, '120th Ave SW', 'Calgary', 'Alberta', 'Canada', 'T2D8E1', 1, 'Debra want us to paint her newly built bathroom. She wants this job to get done in three days, but she will pay us very well. The wall sizes are standard 6x20m^2, there are 3 of them. She wants the famous Dusty Red color combination, so we need those Devil Beaters for the job, also several buckets of pure colored paint to mix them up.', '17-02-25', '17-02-28', 50000, 'paid');

/* assigning JOB_USER HOURS ***********************************************************************/
select assign_user_func ('Brookfield Bathroom on WestTower', 'andrew', 40);
select assign_user_func ('Brookfield Bathroom on WestTower', 'james', 20);
select assign_user_func ('Brookfield Bathroom on WestTower', 'kayla', 25);

/* allocating JOB_ITEM ***********************************************************************/
select allocate_item_func ('Brookfield Bathroom on WestTower', 'SuperFine Paint Brush', 'Wash them before returning plz.',4);
select allocate_item_func ('Brookfield Bathroom on WestTower', 'Devil Beater', 'There are no mistakes, only happy accidents. Thats what my father told me.',4);

/* allocating JOB_REPORT ***********************************************************************/
-- select generate_report_func ('Brookfield Bathroom on WestTower', '2015-10-30 01:02:03');
-- select generate_report_func ('Brookfield Bathroom on WestTower', '2015-10-31 01:04:15');
-- select generate_report_func ('Second Huge Job', '2015-10-30 01:02:10');

/* allocating QUOTES ***********************************************************************/
select insert_quote_func('Paul Brutallus', 'sweatydude@yahoo.ca','yo Kayla! I need my wall to be painted!');
select insert_quote_func('Maria Fromage', 'insecurechick@gmail.com','plz help, can you paint my cat green?');
select insert_quote_func('Boris Slav', 'bot1985@russia.ru','Can you paint cars as well? I have a Lada.');
