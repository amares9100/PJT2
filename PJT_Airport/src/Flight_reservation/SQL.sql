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
	lpname varchar(10),
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

-- 항공사 입력
insert into airline (lname) values ('magpieAIR');
insert into airline (lname) values ('sparrowAIR');
insert into airline (lname) values ('hawkAIR');
insert into airline (lname) values ('pelicanAIR');
-- 비행기 기종 입력
insert into airplane (aname,amax) values ('BOEING',155);
insert into airplane (aname,amax) values ('AIRBUS',177);
-- LP 입력
insert into LP (lpname ,lno,ano) values ('MA123456',1,1);
insert into LP (lpname ,lno,ano) values ('MA123457',1,1);
insert into LP (lpname ,lno,ano) values ('MA123458',1,1);
insert into LP (lpname ,lno,ano) values ('MA987654',1,2);
insert into LP (lpname ,lno,ano) values ('MA987955',1,2);
insert into LP (lpname ,lno,ano) values ('SA234567',2,1);
insert into LP (lpname ,lno,ano) values ('SA234568',2,1);
insert into LP (lpname ,lno,ano) values ('SA234569',2,1);
insert into LP (lpname ,lno,ano) values ('SA876543',2,2);
insert into LP (lpname ,lno,ano) values ('SA876544',2,2);
insert into LP (lpname ,lno,ano) values ('SA876545',2,2);
insert into LP (lpname ,lno,ano) values ('HA345678',3,1);
insert into LP (lpname ,lno,ano) values ('HA345679',3,1);
insert into LP (lpname ,lno,ano) values ('HA765432',3,2);
insert into LP (lpname ,lno,ano) values ('HA765433',3,2);
insert into LP (lpname ,lno,ano) values ('PA456789',4,1);
insert into LP (lpname ,lno,ano) values ('PA654321',4,2);
insert into LP (lpname ,lno,ano) values ('PA654322',4,2);
-- 공항 입력
insert into airport (pnation, pname) values ('대한민국','김포공항');
insert into airport (pnation, pname) values ('대한민국','인천공항');
insert into airport (pnation, pname) values ('대한민국','제주공항');
insert into airport (pnation, pname) values ('일본','하네다공항');
insert into airport (pnation, pname) values ('중국','푸동공항');
insert into airport (pnation, pname) values ('싱가포르','창이공항');
insert into airport (pnation, pname) values ('프랑스','샤롤드골공항');
insert into airport (pnation, pname) values ('영국','히드로공항');
insert into airport (pnation, pname) values ('미국','덜레스공항');

-- schedule 입력
-- 김포 -> 제주
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (1 ,2,3,'2023-03-07 06:15:00','2023-03-07 07:25:00',100000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (6 ,2,3,'2023-03-07 08:20:00','2023-03-07 09:30:00',100000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (6 ,2,3,'2023-03-07 13:05:00','2023-03-07 14:15:00',100000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (1 ,2,3,'2023-03-07 22:45:00','2023-03-07 23:55:00',100000);
-- 제주 -> 김포
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (1 ,3,2,'2023-03-07 08:10:00','2023-03-07 09:20:00',100000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (6 ,3,2,'2023-03-07 10:40:00','2023-03-07 11:50:00',100000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (6 ,3,2,'2023-03-07 15:10:00','2023-03-07 16:20:00',100000);
-- 인천 -> 도쿄
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (7 ,2,4,'2023-03-07 07:15:00','2023-03-07 09:30:00',250000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (1 ,2,4,'2023-03-07 10:15:00','2023-03-07 12:30:00',250000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (7 ,2,4,'2023-03-07 13:10:00','2023-03-07 15:25:00',250000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (1 ,2,4,'2023-03-07 16:35:00','2023-03-07 18:50:00',250000);
-- 도쿄 -> 인천
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (7 ,4,2,'2023-03-07 10:25:00','2023-03-07 12:40:00',250000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (1 ,4,2,'2023-03-07 13:25:00','2023-03-07 15:40:00',250000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (7 ,4,2,'2023-03-07 16:25:00','2023-03-07 18:40:00',250000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (1 ,4,2,'2023-03-07 19:25:00','2023-03-07 21:40:00',250000);
-- 인천 -> 상하이
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (2 ,2,5,'2023-03-07 04:15:00','2023-03-07 06:15:00',320000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (11,2,5,'2023-03-07 07:25:00','2023-03-07 09:25:00',320000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (16,2,5,'2023-03-07 09:05:00','2023-03-07 11:05:00',320000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (2 ,2,5,'2023-03-07 12:45:00','2023-03-07 14:45:00',320000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (11,2,5,'2023-03-07 14:35:00','2023-03-07 16:35:00',320000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (16,2,5,'2023-03-07 16:15:00','2023-03-07 18:15:00',320000);
-- 상하이 -> 인천
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (2 ,5,2,'2023-03-07 08:05:00','2023-03-07 10:05:00',320000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (11,5,2,'2023-03-07 10:35:00','2023-03-07 12:35:00',320000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (16,5,2,'2023-03-07 13:05:00','2023-03-07 14:05:00',320000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (2 ,5,2,'2023-03-07 15:55:00','2023-03-07 17:55:00',320000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (11,5,2,'2023-03-07 18:05:00','2023-03-07 20:05:00',320000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (16,5,2,'2023-03-07 20:00:00','2023-03-07 22:00:00',320000);
-- 인천 -> 싱가포르
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (17,2,6,'2023-03-07 07:10:00','2023-03-07 13:30:00',280000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (17,2,6,'2023-03-07 12:30:00','2023-03-07 18:50:00',280000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (17,2,6,'2023-03-07 15:45:00','2023-03-07 22:05:00',280000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (17,2,6,'2023-03-07 20:00:00','2023-03-08 02:20:00',280000);
-- 싱가포르 -> 인천
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (17,6,2,'2023-03-07 08:15:00','2023-03-07 14:35:00',280000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (17,6,2,'2023-03-07 13:30:00','2023-03-07 19:50:00',280000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (17,6,2,'2023-03-07 16:10:00','2023-03-07 22:30:00',280000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (17,6,2,'2023-03-07 19:55:00','2023-03-08 02:15:00',280000);
-- 인천 -> 파리
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (17,2,7,'2023-03-07 08:10:00','2023-03-07 22:30:00',800000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (17,2,7,'2023-03-07 14:45:00','2023-03-08 05:05:00',800000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (17,2,7,'2023-03-07 20:05:00','2023-03-08 10:25:00',800000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (17,2,7,'2023-03-08 08:10:00','2023-03-08 22:30:00',800000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (17,2,7,'2023-03-08 14:45:00','2023-03-09 05:05:00',800000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (17,2,7,'2023-03-08 20:05:00','2023-03-09 10:25:00',800000);
-- 파리 -> 인천
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (17,7,2,'2023-03-07 07:55:00','2023-03-07 22:15:00',800000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (17,7,2,'2023-03-07 13:15:00','2023-03-08 03:35:00',800000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (17,7,2,'2023-03-07 18:40:00','2023-03-08 09:00:00',800000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (17,7,2,'2023-03-08 07:55:00','2023-03-08 22:15:00',800000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (17,7,2,'2023-03-08 13:15:00','2023-03-09 03:35:00',800000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (17,7,2,'2023-03-08 18:40:00','2023-03-09 09:00:00',800000);
-- 인천 -> 런던
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (4,2,8,'2023-03-07 08:15:00','2023-03-07 22:55:00',800000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (4,2,8,'2023-03-07 12:45:00','2023-03-08 03:25:00',800000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (4,2,8,'2023-03-07 16:00:00','2023-03-08 06:40:00',800000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (4,2,8,'2023-03-07 20:45:00','2023-03-08 11:25:00',800000);
-- 런던 <- 인천
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (4,8,2,'2023-03-08 04:15:00','2023-03-08 18:55:00',800000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (4,8,2,'2023-03-08 08:15:00','2023-03-08 22:55:00',800000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (4,8,2,'2023-03-08 12:30:00','2023-03-09 03:10:00',800000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (4,8,2,'2023-03-08 17:20:00','2023-03-09 08:00:00',800000);
-- 인천  	-> 워싱턴
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (9 ,2,9,'2023-03-07 07:25:00','2023-03-07 21:15:00',1100000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (15,2,9,'2023-03-07 13:25:00','2023-03-08 03:15:00',1100000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (18,2,9,'2023-03-07 17:25:00','2023-03-08 07:15:00',1100000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (5 ,2,9,'2023-03-08 07:25:00','2023-03-08 21:15:00',1100000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (9 ,2,9,'2023-03-08 13:25:00','2023-03-09 03:15:00',1100000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (15,2,9,'2023-03-08 17:25:00','2023-03-09 07:15:00',1100000);
-- 워싱턴 -> 인천
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (9 ,9,2,'2023-03-07 23:35:00','2023-03-08 12:35:00',1100000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (15,9,2,'2023-03-08 06:35:00','2023-03-08 20:25:00',1100000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (18,9,2,'2023-03-08 10:35:00','2023-03-09 00:25:00',1100000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (5 ,9,2,'2023-03-08 23:35:00','2023-03-09 12:35:00',1100000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (9 ,9,2,'2023-03-09 06:35:00','2023-03-09 20:25:00',1100000);
insert into schedule (lpno,spno,dpno,stime,dtime,price) values (15,9,2,'2023-03-09 10:35:00','2023-03-10 00:25:00',1100000);
-- 도쿄 	-> 상하이
-- 상하이 -> 도쿄
-- 도쿄 	-> 파리
-- 파리 	-> 도쿄
-- 도쿄 	-> 런던
-- 런던 	-> 도쿄
-- 도쿄 	-> 워싱턴
-- 워싱턴 -> 도쿄
-- 상하이 -> 파리
-- 파리 	-> 상하이
-- 상하이 - > 런던
-- 런던 	-> 상하이
-- 상하이 -> 워싱턴
-- 워싱턴 -> 상하이
-- 파리 	-> 런던
-- 런던	-> 파리
-- 파리	-> 워싱턴
-- 워싱턴 -> 파리
-- 런던 	-> 워싱턴
-- 워싱턴 -> 런던

-- Tear_table 입력
insert into tier_table (tier,Mileage,discount,arate) values ( 'BRONZE'	,0		,0.1,0.1);
insert into tier_table (tier,Mileage,discount,arate) values ( 'SILVER'	,2000	,0.2,0.2);
insert into tier_table (tier,Mileage,discount,arate) values ( 'GOLD'	,5000	,0.3,0.3);
insert into tier_table (tier,Mileage,discount,arate) values ( 'PLATINUM',10000	,0.4,0.4);
insert into tier_table (tier,Mileage,discount,arate) values ( 'DIAMOND'	,16000	,0.5,0.5);
-- member 입력
insert into member ( mid,mpw,mname,mphone) values ('adimn','1234','관리자','010-1234-5678');
insert into member ( mid,mpw,mname,mphone,tier,Mileage) values ('qweqwe','1234','유재석','010-1111-1111','SILVER'	,2500);
insert into member ( mid,mpw,mname,mphone,tier,Mileage) values ('asdasd','4567','강호동','010-2222-2222','BRONZE'	,1200);
insert into member ( mid,mpw,mname,mphone,tier,Mileage) values ('zxczxc','8901','이수근','010-3333-3333','GOLD'	,8000);
insert into member ( mid,mpw,mname,mphone,tier,Mileage) values ('cxzcxz','7410','서장훈','010-4444-4444','BRONZE'	,200);
insert into member ( mid,mpw,mname,mphone,tier,Mileage) values ('dsadsa','8520','민경훈','010-5555-5555','DIAMOND',12000);



select * from airline;
select * from airplane;
select * from airport;
select * from tier_table;
select * from member;
select * from LP;
select * from schedule;
select * from reservation;