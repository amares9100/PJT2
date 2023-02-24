drop database if exists Flight_reservation;
create database Flight_reservation;
use Flight_reservation;

drop table if exists airline;
create table airline(
	lno int auto_increment primary key,
    lname varchar(20)
);

drop table if exists airplane;
CREATE TABLE airplane (
    ano INT AUTO_INCREMENT PRIMARY KEY,
    aname VARCHAR(20),
    amax int
);

drop table if exists airport;
CREATE TABLE airport (
    pno INT AUTO_INCREMENT PRIMARY KEY,
    pname VARCHAR(20),
    pnation varchar(20)
);

drop table if exists tier_table;
create table tier_table(
	tier varchar(10) primary key,
    Mileage int,
    discount float,
    arate float
);

drop table if exists member;
create table member (
	mno int auto_increment primary key,
    mid varchar(20),
    mpw varchar(20),
    mname varchar(20),
    mphone varchar(20),
    tier varchar(10),
    Mileage int,
    foreign key(tier) references tier_table(tier)
);

drop table if exists LP;
create table LP(
	lpno int auto_increment primary key,
	lno int,
    ano int,
    foreign key(lno) references airline(lno),
    foreign key(ano) references airplane(ano)
);

drop table if exists schedule;
create table schedule(
	sno int auto_increment primary key,
    lpno int,
    spno int,
    dpno  int,
    stime datetime,
    dtime datetime,
    price int,
    rseats int,
    foreign key(lpno) references LP(lpno),
    foreign key(spno) references airport(pno),
    foreign key(dpno) references airport(pno)
);

drop table if exists reservation;
create table reservation(
	rno int auto_increment primary key,
    sno int,
    mno int,
    men int,
    tprice int,
    foreign key(sno) references schedule(sno),
    foreign key(mno) references member(mno)
);


