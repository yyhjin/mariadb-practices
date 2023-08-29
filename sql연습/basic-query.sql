select version(), current_date, now() from dual;

-- 수학 함수도 사용 가능 (사칙연산 가능)
select sin(pi() / 4), 1+2*3-4/5 from dual;

-- 대소문자 구분 안함
seLECT VERSION(), current_DATE, NOW() froM duAL;

-- table 생성: DDL
create table pet(
	name VARCHAR(100),
    owner VARCHAR(20),
    species VARCHAR(20),
    gender CHAR(1),
    birth DATE,
    death DATE
);

-- schema 확인
show tables;
describe pet;
desc pet;

-- table 삭제: DDL
drop table pet;
show tables;

-- insert: DML(C)
insert into pet values('성탄이', '안대혁', 'dog', 'm', '2019-12-25', null);

-- select: DML(R)
select * from pet;

-- update: DML(U)
update pet set name='성타니' where name='성탄이';

-- delete: DML(D)