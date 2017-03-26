DROP TABLE ORA_G0L0B.DISCOUNTS;
DROP TABLE ORA_G0L0B.SERVICES_ASSIGNS;
DROP TABLE ORA_G0L0B.BILL_HAS_GENERATE_BILL;
DROP TABLE ORA_G0L0B.RESERVE_ROOM_HAS_FLOOR2;
DROP TABLE ORA_G0L0B.HOUSEKEEPER2;
DROP TABLE ORA_G0L0B.MANAGER;
DROP TABLE ORA_G0L0B.RESERVE_ROOM_HAS_FLOOR1;
DROP TABLE ORA_G0L0B.FLOOR;
DROP TABLE ORA_G0L0B.GUEST;
DROP TABLE ORA_G0L0B.USERS;

create table users(userID int, userName varchar(30), password varchar(30), phoneNo varchar(10), name varchar(30), primary key(userID))
SEGMENT CREATION IMMEDIATE;

create table manager(wage int, userID int, primary key(userID), foreign key(userID) references users(userID))
SEGMENT CREATION IMMEDIATE;

create table guest(membershipType varchar(20), homeAddress varchar(40), nights_Stayed_Before int, userID int, primary key(userID),foreign key(userID) references users(userID))
SEGMENT CREATION IMMEDIATE;

create table floor(floorNo int, primary key (floorNo))
SEGMENT CREATION IMMEDIATE;

create table reserve_Room_Has_Floor1(cost decimal(6,2), typeOfRoom varchar(20), primary key(typeOfRoom))
SEGMENT CREATION IMMEDIATE;

create table housekeeper2(wage decimal(4,2),cleaningSpeciality varchar(20), phoneNo varchar(10), name varchar(30), sin int, primary key(sin))
SEGMENT CREATION IMMEDIATE;

create table services_Assigns(sin int, floorNo int,  mUserID int not null, primary key(sin, floorNo), foreign key(mUserID) references manager(UserID), foreign key(sin) references housekeeper2(SIN) on delete cascade, foreign key(floorNo) references floor(floorNo))
SEGMENT CREATION IMMEDIATE;

create table reserve_Room_Has_Floor2(roomNo int, typeOfRoom varchar(20),  floorNo int not null, gUserID int, bookingNo int, fromdate date, todate date, numOfBeds int, primary key(roomNo), foreign key(floorNo) references floor(floorNo), foreign key(gUserID) references guest(userID), foreign key(typeOfRoom) references reserve_Room_Has_Floor1(typeOfRoom))
SEGMENT CREATION IMMEDIATE;

create table bill_Has_Generate_Bill(amountDue decimal(7,2), billID int, amountPaid decimal(7,2), dateOfBill date, gUserID int not null, roomNo int not null, mUserID int not null, primary key(billID), foreign key(gUserID) references guest(userID), foreign key(mUserID) references manager(userID), foreign key(roomNo) references reserve_Room_Has_Floor2(roomNo))
SEGMENT CREATION IMMEDIATE;

create table discounts(amount int, billID int, mUserID int, primary key(billID, mUserID), foreign key(billID) references bill_Has_Generate_Bill(billID), foreign key(mUserID) references manager(userID), check (amount > 0 AND amount <= 100))
SEGMENT CREATION IMMEDIATE;

