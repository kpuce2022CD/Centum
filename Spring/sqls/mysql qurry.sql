create database boxfolio default character set utf8;

SET SQL_SAFE_UPDATES = 0;

show databases;

use boxfolio;

show tables;

select * from userInfo;

delete from userInfo where id="testUser";

create table userInfo(
	id varchar(20) not null,
    nickName varchar(10) not null,
    userLevel tinyint unsigned default 0,
    pw varchar(64) not null
)

