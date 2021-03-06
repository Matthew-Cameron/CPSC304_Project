insert into users
values(1893, 'bob', 'bob', '6046789012', 'Bob_Smith');
insert into users
values(1801, 'john', 'abced', '6047890123', 'John_Liu');
insert into users
values(1798, 'dave', 'qatd', '6048901234', 'Dave_Johnson');
insert into users
values(1892, 'jeff', 'abc', '6049012345', 'Jeff_Zhao');
insert into users
values(1791, 'bobby', 'df3456', '6040123456', 'Bobby_Lin');
insert into users
values(1111, 'joe', 'joe', '6048888888', 'Joe_Pratt');
insert into users
values(2222, 'erica11', '16ab', '6043333331', 'Erica_Lewis');
insert into users
values(3333, 'will', 'willa', '6045555551', 'Will_Trump');
insert into users
values(5555, 'jon', 'jonjon', '6048888881', 'Jon_Hong');
insert into users
values(7777, 'mark', 'bev', '6049879871', 'Mark_Sedin');
 
insert into manager
values(90000, 1893);
insert into manager
values(80000, 1801);
insert into manager
values(70000, 1798);
insert into manager
values(50000, 1892);
insert into manager
values(40000, 1791);

insert into guest
values('Standard', '9_Downing_Street', 0, 1111);
insert into guest
values('VIP', '10_Downing_Street', 30, 2222);
insert into guest
values('VIP', '14_Gaglardi_Way', 25, 3333);
insert into guest
values('Standard', '24_Halifax_Street', 16, 5555);
insert into guest
values('Standard', '1334_1st_Avenue', 5, 7777);

insert into floor
values(1);
insert into floor
values(2);
insert into floor
values(3);
insert into floor
values(4);
insert into floor
values(5);
 

insert into reserve_Room_Has_Floor1
values(696.96, 'Honeymoon_Suite');
insert into reserve_Room_Has_Floor1
values(199.99, 'Standard');
insert into reserve_Room_Has_Floor1
values(499.99, 'Luxury');
insert into reserve_Room_Has_Floor1
values(299.99, 'Suite');
insert into reserve_Room_Has_Floor1
values(399.99, 'Double');

insert into housekeeper2
values(20.00,'Beds', '6041234567', 'Isaac_Newton',171717);
insert into housekeeper2
values(15.00,'Bathrooms',  '6042345678', 'Gottfried_Leibniz',717171);
insert into housekeeper2
values(17.00,'Bathrooms','6043456789', 'Claude_Shannon',454444);
insert into housekeeper2
values(18.00,'Tidying', '6044567890', 'Yurii_Nesterov',456868);
insert into housekeeper2
values(11.00,'Laundry',  '6045678901', 'Ada_Lovelace',489898);
insert into housekeeper2
values(11.00,'Laundry',  '6044567890', 'Yurii_Nesterov',489899);
 
insert into services_Assigns
values(171717, 1,1801);
insert into services_Assigns
values(717171, 2,1801);
insert into services_Assigns
values(454444,3, 1798);
insert into services_Assigns
values(456868, 4,1892);
insert into services_Assigns
values(489898, 1,1791);
insert into services_Assigns
values(489898, 2,1791);
insert into services_Assigns
values(489898, 3,1791);
insert into services_Assigns
values(489898, 4,1791);
insert into services_Assigns
values(489898, 5,1791);

insert into reserve_Room_Has_Floor2
values(213, 'Standard', 2, 1111,132,'2017-01-01', '2017-02-02', 2);
insert into reserve_Room_Has_Floor2
values(313, 'Luxury', 3,2222, 133, '2017-01-01', '2017-02-02',3);
insert into reserve_Room_Has_Floor2
values(413, 'Honeymoon_Suite', 4,3333, 134, '2017-01-01', '2017-02-02',1);
insert into reserve_Room_Has_Floor2
values(513, 'Suite',  5, 1111, 135, '2017-02-03', '2017-02-04',2);
insert into reserve_Room_Has_Floor2
values(113, 'Double',   1,  1111, 136, '2017-02-05', '2017-02-06',3);
insert into reserve_Room_Has_Floor2
values(613, 'Honeymoon_Suite',  3, NULL, NULL, NULL, NULL,4);
insert into reserve_Room_Has_Floor2
values(214, 'Honeymoon_Suite',  2, NULL, NULL, NULL, NULL,4);
insert into reserve_Room_Has_Floor2
values(215, 'Standard',  4, NULL, NULL, NULL, NULL,4);
insert into reserve_Room_Has_Floor2
values(100, 'Double',  5, NULL, NULL, NULL, NULL,3);
 
insert into bill_Has_Generate_Bill
values(599.99, 1010, 20.00, '2017-05-02', 1111,213,1893);
insert into bill_Has_Generate_Bill
values(699.99, 1011, 40.00, '2017-05-02', 2222,113,1801);
insert into bill_Has_Generate_Bill
values(499.99, 1012, 60.00, '2017-05-02', 3333,213,1798);
insert into bill_Has_Generate_Bill
values(599.99, 1013, 100.00, '2017-05-02', 5555,113,1798);
insert into bill_Has_Generate_Bill
values(399.99, 1014, 80.00, '2017-05-02', 7777,113,1798);
insert into bill_Has_Generate_Bill
values(456.5, 1054, 456.5, '2017-05-02',5555,213,1798);
 
insert into discounts
values(10, 1010, 1801);
insert into discounts
values(20, 1011, 1801);
insert into discounts
values(30, 1012, 1801);
insert into discounts
values(40, 1013, 1801);
insert into discounts
values(50, 1013, 1798);





