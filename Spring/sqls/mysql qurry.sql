create database boxfolio default character set utf8;

show databases;

use boxfolio;

show tables;

select * from userInfo;

create table userInfo(
	id varchar(20) not null,
    nickName varchar(10) not null,
    userLevel tinyint unsigned default 0,
    pw varchar(64) not null
)

