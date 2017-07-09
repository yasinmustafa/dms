insert into user (name, password) values ('a', 'a');

insert into person (first_name, surname, sort_order, company_ind) values ('Test1', 'Test1', '10', '0');
insert into person (first_name, surname, sort_order, company_ind) values ('Test2', 'Test2', '20', '0');
insert into person (company_name, sort_order, company_ind) values ('Test3', '10', '1');
INSERT INTO `type` (`TYP_ID`,`CREATED_BY`,`MODIFIED_BY`,`modified_date`,`CATEGORY`,`NAME`,`SORT_ORDER`) VALUES (1,NULL,NULL,NULL,NULL,'Doctor',40);
INSERT INTO `type` (`TYP_ID`,`CREATED_BY`,`MODIFIED_BY`,`modified_date`,`CATEGORY`,`NAME`,`SORT_ORDER`) VALUES (2,NULL,NULL,NULL,NULL,'Bank',20);
INSERT INTO `type` (`TYP_ID`,`CREATED_BY`,`MODIFIED_BY`,`modified_date`,`CATEGORY`,`NAME`,`SORT_ORDER`) VALUES (3,NULL,NULL,NULL,NULL,'Utilities',50);
INSERT INTO `type` (`TYP_ID`,`CREATED_BY`,`MODIFIED_BY`,`modified_date`,`CATEGORY`,`NAME`,`SORT_ORDER`) VALUES (4,NULL,NULL,NULL,NULL,'Car',30);
INSERT INTO `type` (`TYP_ID`,`CREATED_BY`,`MODIFIED_BY`,`modified_date`,`CATEGORY`,`NAME`,`SORT_ORDER`) VALUES (5,NULL,NULL,NULL,NULL,'None',10);
INSERT INTO `type` (`TYP_ID`,`CREATED_BY`,`MODIFIED_BY`,`modified_date`,`CATEGORY`,`NAME`,`SORT_ORDER`) VALUES (6,NULL,NULL,NULL,NULL,'Passport',60);
INSERT INTO `type` (`TYP_ID`,`CREATED_BY`,`MODIFIED_BY`,`modified_date`,`CATEGORY`,`NAME`,`SORT_ORDER`) VALUES (7,NULL,NULL,NULL,NULL,'Council',35);
INSERT INTO `type` (`TYP_ID`,`CREATED_BY`,`MODIFIED_BY`,`modified_date`,`CATEGORY`,`NAME`,`SORT_ORDER`) VALUES (8,NULL,NULL,NULL,NULL,'Insurance',45);
INSERT INTO `type` (`TYP_ID`,`CREATED_BY`,`MODIFIED_BY`,`modified_date`,`CATEGORY`,`NAME`,`SORT_ORDER`) VALUES (9,NULL,NULL,NULL,NULL,'Phone',90);
INSERT INTO `type` (`TYP_ID`,`CREATED_BY`,`MODIFIED_BY`,`modified_date`,`CATEGORY`,`NAME`,`SORT_ORDER`) VALUES (10,NULL,NULL,NULL,NULL,'Work',100);
INSERT INTO `type` (`TYP_ID`,`CREATED_BY`,`MODIFIED_BY`,`modified_date`,`CATEGORY`,`NAME`,`SORT_ORDER`) VALUES (11,NULL,NULL,NULL,NULL,'Cable',27);
INSERT INTO `type` (`TYP_ID`,`CREATED_BY`,`MODIFIED_BY`,`modified_date`,`CATEGORY`,`NAME`,`SORT_ORDER`) VALUES (12,NULL,NULL,NULL,NULL,'House',120);
INSERT INTO `type` (`TYP_ID`,`CREATED_BY`,`MODIFIED_BY`,`modified_date`,`CATEGORY`,`NAME`,`SORT_ORDER`) VALUES (13,NULL,NULL,NULL,NULL,'School',130);
INSERT INTO `type` (`TYP_ID`,`CREATED_BY`,`MODIFIED_BY`,`modified_date`,`CATEGORY`,`NAME`,`SORT_ORDER`) VALUES (14,NULL,NULL,NULL,NULL,'Tax',140);
INSERT INTO `type` (`TYP_ID`,`CREATED_BY`,`MODIFIED_BY`,`modified_date`,`CATEGORY`,`NAME`,`SORT_ORDER`) VALUES (15,NULL,NULL,NULL,NULL,'TV',150);
INSERT INTO `type` (`TYP_ID`,`CREATED_BY`,`MODIFIED_BY`,`modified_date`,`CATEGORY`,`NAME`,`SORT_ORDER`) VALUES (16,NULL,NULL,NULL,NULL,'Shopping',160);
insert into document ( com_text , date_on_letter , ext, filename , indexed_date , sent_from_per_id , sent_to_per_id , typ_id ) values ('test1', DATE_SUB(curdate(), INTERVAL 1 DAY) , 'pdf', 'test1.pdf', DATE_SUB(curdate(), INTERVAL 1 DAY) , 1, 2, 1);

insert into document ( com_text , date_on_letter , ext, filename , indexed_date , sent_from_per_id , sent_to_per_id , typ_id ) values ('test2', DATE_SUB(curdate(), INTERVAL 30 DAY) , 'pdf', 'test2.pdf', DATE_SUB(curdate(), INTERVAL 30 DAY) , 2, 1, 1);

insert into document ( com_text , date_on_letter , ext, filename , indexed_date , sent_from_per_id , sent_to_per_id , typ_id ) values ('test3', curdate() , 'pdf', 'test3.pdf', curdate() , 1, 2, 1);

insert into document ( com_text , date_on_letter , ext, filename , indexed_date , sent_from_per_id , sent_to_per_id , typ_id ) values ('test4', curdate() , 'pdf', 'test4.pdf', curdate() , 2, 1, 1);

