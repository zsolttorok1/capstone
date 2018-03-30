/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  725899
 * Created: Mar 27, 2018
 */

/* adding USERS ***********************************************************************/
select insert_user_func ('aa', 236, '44th Ave NW', 'Calgary', 'Alberta', 'Canada', 'T1E0R4', '76C4094ECAB56B29E811F2B39C16C927099E9F547086F324DB3CC7EB7942EDBD9AA16FFA3E4523E6A35373D85A00577010FA94F6479C23300485E5669F845E91', 'Admin', 'Administ', 'owner', 'admin@hotmail.com', 90.32, '4d5eac44fd0a56b786b0f2fe40ff3561');
select insert_phoneList_user_func ('aa', '4031112211,4032221122,');

select insert_user_func ('andrew_grieve', 236, '78th Ave NE', 'Calgary', 'Alberta', 'Canada', 'T2K0R4', 'Green2012', 'Andrew', 'Grieve', 'owner', 'agrieve2@hotmail.com', 90.32, '67844c34e0de7183a88fc836d3d949e3');
select insert_phoneList_user_func ('andrew_grieve', '4038077189,4038077111,4038077222,');
--insert into `job_user` (`user_name`, `job_name`, `hours`)
--    values ('andrew_grieve', 'Brookfield Bathroom on WestTower', 0);

select insert_user_func ('james_grieve', 236, '78th Ave NE', 'Calgary', 'Alberta', 'Canada', 'T2K0R4', 'James11', 'James', 'Grieve', 'manager', 'darklink44459@hotmail.com', 23.41, 'bb2642cd9e01fe3a178d73e865771c3d');
select insert_phoneList_user_func  ('james_grieve', '4034879866,');
--insert into `job_user` (`user_name`, `job_name`, `hours`)
--   values ('james_grieve', 'Brookfield Bathroom on WestTower', 0);

select insert_user_func ('kayla_grieve', 236, '78th Ave NE', 'Calgary', 'Alberta', 'Canada', 'T2K0R4', 'Kayla11', 'Kayla', 'Grieve', 'employee', 'link44459@hotmail.com', 18.32, '2b6b55a74fedd32349f7cfe65b9477a5');
select insert_phoneList_user_func  ('kayla_grieve', '4037778620,');
-- insert into `job_user` (`user_name`, `job_name`, `hours`)
--     values ('kayla_grieve', 'Brookfield Bathroom on WestTower', 0);

select insert_user_func ('zsto', 236, '28th Ave NE', 'Calgary', 'Alberta', 'Canada', 'T111R4', 'password', 'Satomi', 'Test', 'owner', 'internet@gmail.com', 11.11, '67844c34e0de7113a88fc136d3d949e3');
select insert_phoneList_user_func ('zsto', '4035555559,');

/* adding CUSTOMERS ***********************************************************************/
select insert_customer_func (1, 222, '40th Ave NW', 'Calgary', 'Alberta', 'Canada', 'T1E5E1', 'Debra', 'Frank', 'Heavy Grip Corp.', 'dbeater@hotmail.com', 'CEO', 'Red hair, blue eyes. Talks very fast.');
select insert_phoneList_customer_func (1, '4031231234,40399880011,');

select insert_customer_func (2, 232, '10th Ave NW', 'Calgary', 'Alberta', 'Canada', 'T1E521', 'Fukushima', 'John', 'Lollers Corp.', 'lollers@hotmail.com', 'Manager', 'The dude.');
select insert_phoneList_customer_func (2, '403222333,403982222,');

/* adding ITEMS ***********************************************************************/
select insert_item_func ('SuperFine Paint Brush', 22, 'Brushes', 'We use this to paint fur.');
select insert_item_func ('Thick Master 2000', 5, 'Brushes', 'Great for making solid straight strokes.');
select insert_item_func ('Hairy Harold', 5, 'Brushes', 'We dont use this on walls.');
select insert_item_func ('Hairy Harold', 2, 'Brushes', 'We dont use this on walls, way too hairy.');
select insert_item_func ('Devil Beater', 5, 'Brushes', 'Bob Ross favorite.');

INSERT INTO `category` (`category_id`, `category_name`) VALUES ('3', 'Spoiled Milk');
INSERT INTO `category` (`category_id`, `category_name`) VALUES ('10', 'Doesnt exist anymore');

/* adding JOBS ***********************************************************************/
/*Date: YY-MM-DD */
select insert_job_func ('First Job on main street', 555, '123th Ave NE', 'Calgary', 'Alberta', 'Canada', 'T5K2R8', 2, 'This is a nice and detailed job description.', '18-03-28', '18-03-30', 0, 'unpayed');

select insert_job_func ('First Job on main street', 555, '123th Ave NE', 'Calgary', 'Alberta', 'Canada', 'T5K2R8', 2, 'This is a nice and detailed job description, updated.', '18-03-28', '18-03-30', 0, 'unpayed');
select insert_job_func ('Second Huge Job', 555, '999th Ave SW', 'Calgary', 'Alberta', 'Canada', 'T9D8Y1', 1, 'Sent from future ya.', '19-04-10', '19-04-20', 29000, 'payed');