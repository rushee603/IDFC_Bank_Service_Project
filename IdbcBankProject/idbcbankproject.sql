create database bankproject;
use bankproject;

----------------------------------------- Customer table -----------------------------------------
create table Customer
(

emailid varchar(50) not null primary key ,
fullName char(25) not null,
mobile numeric(10) not null,
age int not null,
accountNo bigint not null ,
foreign key(accountNo) references Accounts(accountNo) on update cascade on delete cascade 
);
select * from Customer;
insert into customer values();

-------------------------------------------- Accounts table -----------------------------------------------------
create table Accounts
(

accountNo bigint not null primary key,
balance bigint,
accountType char(10) not null

);

insert into Customer values();
----------------------------------------- -- Opration Table --------------------------------------------------------
create table Opration
(
accountNo bigint not null,
tits  timestamp,
type char(15) not null,
foreign key(accountNo) references Accounts(accountNo) on update cascade on delete cascade
);
-- --------------------------------------------Display table--------------------------------------------------------------------     
select * from Customer;
select * from Accounts;
select * from Opration;



