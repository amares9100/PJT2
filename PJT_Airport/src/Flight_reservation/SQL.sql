drop database if exists Flight_reservation;
create database Flight_reservation;
use Flight_reservation;

drop table if exists airline;
create table airline(
	lno int auto_increment primary key,
    lname varchar(20) not null
);

drop table if exists airplane;
CREATE TABLE airplane (
    ano INT AUTO_INCREMENT PRIMARY KEY,
    aname VARCHAR(20) not null,
    amax int not null
);

drop table if exists airport;
CREATE TABLE airport (
    pno INT AUTO_INCREMENT PRIMARY KEY,
    pname VARCHAR(20) not null,
    pnation varchar(20) not null
);

drop table if exists tier_table;
create table tier_table(
	tier varchar(10) primary key,
    Mileage int not null,
    discount float not null,
    arate float not null
);

drop table if exists member;
create table member (
	mno int auto_increment primary key,
    mid varchar(20) not null,
    mpw varchar(20) not null,
    mname varchar(20) not null,
    mphone varchar(20) not null,
    tier varchar(10) default 'bronze',
    Mileage int default 0,
    foreign key(tier) references tier_table(tier)
);

drop table if exists LP;
create table LP(
	lpno int auto_increment primary key,
	lno int not null,
    ano int not null,
    foreign key(lno) references airline(lno),
    foreign key(ano) references airplane(ano)
);

drop table if exists schedule;
create table schedule(
	sno int auto_increment primary key,
    lpno int not null,
    spno int not null,
    dpno  int not null,
    stime datetime not null,
    dtime datetime not null,
    price int not null,
    rseats int not null,
    foreign key(lpno) references LP(lpno),
    foreign key(spno) references airport(pno),
    foreign key(dpno) references airport(pno)
);

drop table if exists reservation;
create table reservation(
	rno int auto_increment primary key,
    sno int not null,
    mno int not null,
    men int not null,
    tprice int not null,
    foreign key(sno) references schedule(sno),
    foreign key(mno) references member(mno)
);


