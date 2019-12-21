drop table if exists reimbursementStatus CASCADE;
drop table if exists reimbursementType CASCADE;
drop table if exists userRoles CASCADE;
drop table if exists users CASCADE;
drop table if exists reimbursements CASCADE;


create table reimbursementStatus (
	statusId numeric PRIMARY KEY,
	reimStatus varchar (15)
);


create table reimbursementType (
	typeId numeric PRIMARY KEY,
	reimType varchar (40)
);


create table userRoles (
	roleId numeric PRIMARY KEY,
	userRole varchar (30)
);


create table users (
	userId Serial Primary key,
	userName varchar (50) not null unique,
	userPass varchar (50) not null,
	firstName varchar (100) not null,
	lastName varchar (100) not null,
	email varchar (150),
	userRole numeric REFERENCES userRoles(roleId)
);


create table reimbursements (
	reimbursementId serial PRIMARY KEY,
	amount numeric (50,2) not null,
	timeSubmitted varchar not null,
	timeResolved varchar,
	discription varchar (250),
	receipt bytea,
	author int references users(userId),
	resolver int references users(userId),
	status numeric references reimbursementStatus(statusId),
	reimType numeric references reimbursementType(typeId)
);

/******************** Populate Foreign Key Tables**************/
insert into reimbursementStatus (statusId, reimstatus)
	values (0, 'pending');
insert into reimbursementStatus (statusId, reimstatus)
	values (1, 'approved');
insert into reimbursementStatus (statusId, reimstatus)
	values (-1, 'denied');

insert into reimbursementType (typeId, reimtype)
	values (1, 'lodging');
insert into reimbursementType (typeId, reimtype)
	values (2, 'travel');
insert into reimbursementType (typeId, reimtype)
	values (3, 'food');
insert into reimbursementType (typeId, reimtype)
	values (4, 'other');

insert into userRoles (roleId, userrole)
	values (1, 'employee');
insert into userRoles (roleId, userrole)
	values (2, 'financeManager');

/*************************COUPLE USERS TO START***********************************/
insert into users (userName, userPass, firstName, lastName, email, userRole)
	values ('Manager', 'Password1', 'Jonathan', 'Wulf', 'yadayada@yada.com', 2);
insert into users (userName, userPass, firstName, lastName, email, userRole)
	values ('Employee', 'Password1', 'Larry', 'King', 'Larry@yada.com', 1);