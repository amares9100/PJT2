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
	lpname varchar(10),
    foreign key(lno) references airline(lno),
    foreign key(ano) references airplane(ano)
);



drop table if exists schedule;
create table schedule(
	sno int auto_increment primary key,
    lpno int not null,
    dpno int not null,
    apno  int not null,
    dtime datetime not null,
    atime datetime not null,
    price int not null,
    rseats int not null,
    foreign key(lpno) references LP(lpno),
    foreign key(dpno) references airport(pno),
    foreign key(apno) references airport(pno)
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


select LP.lpno, LP.lpname, AL.lname, AP.aname, AP.amax 
from  LP ,airline AL,airplane AP
where LP.lno = AL.lno and LP.ano = AP.ano 
order by lpno asc;

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
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (1 ,1,3,'2023-03-07 06:15:00','2023-03-07 07:25:00',100000,10);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (6 ,1,3,'2023-03-07 08:20:00','2023-03-07 09:30:00',100000,5);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (6 ,1,3,'2023-03-07 13:05:00','2023-03-07 14:15:00',100000,2);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (1 ,1,3,'2023-03-07 22:45:00','2023-03-07 23:55:00',100000,1);
-- 제주 -> 김포
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (1 ,3,1,'2023-03-07 08:10:00','2023-03-07 09:20:00',100000,20);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (6 ,3,1,'2023-03-07 10:40:00','2023-03-07 11:50:00',100000,10);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (6 ,3,1,'2023-03-07 15:10:00','2023-03-07 16:20:00',100000,1);
-- 인천 -> 도쿄
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (7 ,2,4,'2023-03-07 07:15:00','2023-03-07 09:30:00',250000,20);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (1 ,2,4,'2023-03-07 10:15:00','2023-03-07 12:30:00',250000,10);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (7 ,2,4,'2023-03-07 13:10:00','2023-03-07 15:25:00',250000,0);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (1 ,2,4,'2023-03-07 16:35:00','2023-03-07 18:50:00',250000,3);
-- 도쿄 -> 인천
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (7 ,4,2,'2023-03-07 10:25:00','2023-03-07 12:40:00',250000,20);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (1 ,4,2,'2023-03-07 13:25:00','2023-03-07 15:40:00',250000,10);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (7 ,4,2,'2023-03-07 16:25:00','2023-03-07 18:40:00',250000,1);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (1 ,4,2,'2023-03-07 19:25:00','2023-03-07 21:40:00',250000,5);
-- 인천 -> 상하이
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (2 ,2,5,'2023-03-07 04:15:00','2023-03-07 06:15:00',320000,4);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (11,2,5,'2023-03-07 07:25:00','2023-03-07 09:25:00',320000,6);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (16,2,5,'2023-03-07 09:05:00','2023-03-07 11:05:00',320000,80);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (2 ,2,5,'2023-03-07 12:45:00','2023-03-07 14:45:00',320000,10);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (11,2,5,'2023-03-07 14:35:00','2023-03-07 16:35:00',320000,30);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (16,2,5,'2023-03-07 16:15:00','2023-03-07 18:15:00',320000,1);
-- 상하이 -> 인천
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (2 ,5,2,'2023-03-07 08:05:00','2023-03-07 10:05:00',320000,20);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (11,5,2,'2023-03-07 10:35:00','2023-03-07 12:35:00',320000,50);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (16,5,2,'2023-03-07 13:05:00','2023-03-07 14:05:00',320000,30);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (2 ,5,2,'2023-03-07 15:55:00','2023-03-07 17:55:00',320000,10);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (11,5,2,'2023-03-07 18:05:00','2023-03-07 20:05:00',320000,1);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (16,5,2,'2023-03-07 20:00:00','2023-03-07 22:00:00',320000,20);
-- 인천 -> 싱가포르
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values ( 2,2,6,'2023-03-07 07:10:00','2023-03-07 13:30:00',280000,20);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values ( 9,2,6,'2023-03-07 12:30:00','2023-03-07 18:50:00',280000,1);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (13,2,6,'2023-03-07 15:45:00','2023-03-07 22:05:00',280000,20);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (17,2,6,'2023-03-07 20:00:00','2023-03-08 02:20:00',280000,4);
-- 싱가포르 -> 인천
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (17,6,2,'2023-03-07 08:15:00','2023-03-07 14:35:00',280000,100);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values ( 2,6,2,'2023-03-07 13:30:00','2023-03-07 19:50:00',280000,10);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values ( 9,6,2,'2023-03-07 16:10:00','2023-03-07 22:30:00',280000,2);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (13,6,2,'2023-03-07 19:55:00','2023-03-08 02:15:00',280000,3);
-- 인천 -> 파리
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (17,2,7,'2023-03-07 08:10:00','2023-03-07 22:30:00',800000,1);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (1 ,2,7,'2023-03-07 14:45:00','2023-03-08 05:05:00',800000,2);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (5 ,2,7,'2023-03-07 20:05:00','2023-03-08 10:25:00',800000,50);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (9 ,2,7,'2023-03-08 08:10:00','2023-03-08 22:30:00',800000,20);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (15,2,7,'2023-03-08 14:45:00','2023-03-09 05:05:00',800000,90);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (18,2,7,'2023-03-08 20:05:00','2023-03-09 10:25:00',800000,6);
-- 파리 -> 인천
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (17,7,2,'2023-03-07 07:55:00','2023-03-07 22:15:00',800000,7);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (3,7,2,'2023-03-07 13:15:00','2023-03-08 03:35:00',800000,21);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (5,7,2,'2023-03-07 18:40:00','2023-03-08 09:00:00',800000,20);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (6,7,2,'2023-03-08 07:55:00','2023-03-08 22:15:00',800000,50);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (9,7,2,'2023-03-08 13:15:00','2023-03-09 03:35:00',800000,4);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (16,7,2,'2023-03-08 18:40:00','2023-03-09 09:00:00',800000,2);
-- 인천 -> 런던
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (18,2,8,'2023-03-07 08:15:00','2023-03-07 22:55:00',800000,1);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (5,2,8,'2023-03-07 12:45:00','2023-03-08 03:25:00',800000,5);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (1,2,8,'2023-03-07 16:00:00','2023-03-08 06:40:00',800000,4);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (12,2,8,'2023-03-07 20:45:00','2023-03-08 11:25:00',800000,3);
-- 런던 <- 인천
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (12,8,2,'2023-03-08 04:15:00','2023-03-08 18:55:00',800000,14);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (4,8,2,'2023-03-08 08:15:00','2023-03-08 22:55:00',800000,52);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (7,8,2,'2023-03-08 12:30:00','2023-03-09 03:10:00',800000,44);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (18,8,2,'2023-03-08 17:20:00','2023-03-09 08:00:00',800000,3);
-- 인천  	-> 워싱턴
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (9 ,2,9,'2023-03-07 07:25:00','2023-03-07 21:15:00',1100000,2);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (15,2,9,'2023-03-07 13:25:00','2023-03-08 03:15:00',1100000,4);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (18,2,9,'2023-03-07 17:25:00','2023-03-08 07:15:00',1100000,3);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (5 ,2,9,'2023-03-08 07:25:00','2023-03-08 21:15:00',1100000,31);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (9 ,2,9,'2023-03-08 13:25:00','2023-03-09 03:15:00',1100000,51);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (15,2,9,'2023-03-08 17:25:00','2023-03-09 07:15:00',1100000,96);
-- 워싱턴 -> 인천
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (9 ,9,2,'2023-03-07 23:35:00','2023-03-08 12:35:00',1100000,20);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (15,9,2,'2023-03-08 06:35:00','2023-03-08 20:25:00',1100000,4);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (18,9,2,'2023-03-08 10:35:00','2023-03-09 00:25:00',1100000,6);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (5 ,9,2,'2023-03-08 23:35:00','2023-03-09 12:35:00',1100000,71);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (9 ,9,2,'2023-03-09 06:35:00','2023-03-09 20:25:00',1100000,31);
insert into schedule (lpno,dpno,apno,dtime,atime,price,rseats) values (15,9,2,'2023-03-09 10:35:00','2023-03-10 00:25:00',1100000,2);

-- Tear_table 입력
insert into tier_table (tier,Mileage,discount,arate) values ( 'BRONZE'	,0		,0.1,0.1);
insert into tier_table (tier,Mileage,discount,arate) values ( 'SILVER'	,2000	,0.2,0.2);
insert into tier_table (tier,Mileage,discount,arate) values ( 'GOLD'	,5000	,0.3,0.3);
insert into tier_table (tier,Mileage,discount,arate) values ( 'PLATINUM',10000	,0.4,0.4);
insert into tier_table (tier,Mileage,discount,arate) values ( 'DIAMOND'	,16000	,0.5,0.5);
-- member 입력
insert into member ( mid,mpw,mname,mphone) values ('admin','1234','관리자','010-1234-5678');
insert into member ( mid,mpw,mname,mphone,tier,Mileage) values ('qweqwe','1234','유재석','010-1111-1111','SILVER'	,2500);
insert into member ( mid,mpw,mname,mphone,tier,Mileage) values ('asdasd','4567','강호동','010-2222-2222','BRONZE'	,1200);
insert into member ( mid,mpw,mname,mphone,tier,Mileage) values ('zxczxc','8901','이수근','010-3333-3333','GOLD'	,8000);
insert into member ( mid,mpw,mname,mphone,tier,Mileage) values ('cxzcxz','7410','서장훈','010-4444-4444','BRONZE'	,200);
insert into member ( mid,mpw,mname,mphone,tier,Mileage) values ('dsadsa','8520','민경훈','010-5555-5555','DIAMOND',12000);
-- reservation 입력
insert into reservation (sno,mno,men,tprice) values (1,1,2,200000);
insert into reservation (sno,mno,men,tprice) values (1,2,1,100000);
insert into reservation (sno,mno,men,tprice) values (1,3,3,300000);
insert into reservation (sno,mno,men,tprice) values (2,4,1,100000);
insert into reservation (sno,mno,men,tprice) values (2,5,3,300000);
insert into reservation (sno,mno,men,tprice) values (8,1,1,250000);

select * from airline;
select * from airplane;
select * from airport;
select * from tier_table;
select * from member;
select * from LP;
select * from schedule;
select * from reservation;

select s.sno '스케쥴 번호' ,  al.lname '항공사명' , ap.aname '비행기명' ,  ap1.pname'출발지' , ap2.pname '도착지' , s.dtime '비행일' , s.atime '도착일' , s.price '가격' , s.rseats '남은좌석'  , m.mno '멤버번호' ,t_t.discount '할인율' ,t_t.arate '마일리지 적립비율' ,  m.mileage '멤버마일리지' 
				from schedule s , LP lp ,   airline al , airplane ap , airport ap1 , airport ap2 , member m , tier_table t_t
				where s.sno = 1  and m.mno=2  and t_t.tier = m.tier
				and s.lpno = lp.lpno and lp.lno = al.lno  and lp.ano = ap.ano and ap1.pno = s.dpno and ap2.pno = s.apno;
                
select Mileage from member where mno=2;
update  member  set  Mileage =1700 where mno=2;