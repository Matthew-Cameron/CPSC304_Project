drop table users;
drop table manager;
drop table guest;
drop table floor;
drop table reserve_Room_Has_Floor1;
drop table housekeeper2;
drop table services_Assigns
drop table reserve_Room_Has_Floor2;
drop table bill_Has_Generate_Bill;
drop table discounts;

drop trigger checkAmount;

create table users(userID int, userName varchar(30), password varchar(30), phoneNo varchar(10), name varchar(30), primary key(userID))
SEGMENT CREATION IMMEDIATE;

create table manager(wage int, userID int, primary key(userID), foreign key(userID) references users(userID))
SEGMENT CREATION IMMEDIATE;

create table guest(membershipType varchar(20), homeAddress varchar(40), nights_Stayed_Before int, userID int, primary key(userID),foreign key(userID) references users(userID))
SEGMENT CREATION IMMEDIATE;

create table floor(floorNo int, primary key (floorNo))
SEGMENT CREATION IMMEDIATE;

create table reserve_Room_Has_Floor1(cost decimal(6,2), type varchar(20))
SEGMENT CREATION IMMEDIATE;

create table housekeeper2(wage decimal(4,2),cleaningSpeciality varchar(20), phoneNo varchar(10), name varchar(30), sin int, primary key(sin))
SEGMENT CREATION IMMEDIATE;

create table services_Assigns(sin int, floorNo int,  mUserID int not null, primary key(sin, floorNo), foreign key(mUserID) references manager(UserID), foreign key(sin) references housekeeper2(SIN) on delete cascade, foreign key(floorNo) references floor(floorNo))
SEGMENT CREATION IMMEDIATE;

create table reserve_Room_Has_Floor2(roomNo int, type varchar(20),  floorNo int not null, gUserID int, bookingNo int, fromdate date, todate date, noOfBeds int, primary key(roomNo), foreign key(floorNo) references floor(floorNo), foreign key(gUserID) references guest(userID), foreign key(type) references reserve_Room_Has_Floor1(type))
SEGMENT CREATION IMMEDIATE;

create table bill_Has_Generate_Bill(amountDue decimal(7,2), billID int, amountPaid decimal(7,2), date date, gUserID int not null, roomNo int not null, mUserID int not null, primary key(billID), foreign key(gUserID) references guest(userID), foreign key(mUserID) references manager(userID), foreign key(roomNo) references reserve_Room_Has_Floor2(roomNo))
SEGMENT CREATION IMMEDIATE;

create table discounts(amount int, billID int, mUserID int, primary key(billID, mUserID), foreign key(billID) references bill_Has_Generate_Bill(billID), foreign key(mUserID) references manager(userID), check (amount > 0 AND amount <= 100))
SEGMENT CREATION IMMEDIATE;

CREATE TRIGGER checkAmount before insert on discounts
    for each row
    BEGIN
    if new.amount > 100 then
    signal sqlstate '45000';
     end if;
END checkAmount;
