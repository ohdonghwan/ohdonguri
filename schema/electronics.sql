drop table Electronics;

create table Electronics(
	model_num varchar2(15) primary key,
	model_name varchar2(20) not null,
	price int,
	description varchar2(100),
	password varchar2(20) not null,
	writeday date  not null,
	readnum int,
	fname varchar2(50), --파일이름
        fsize int --파일용량
);


insert into Electronics values('NT900X4D-A68','삼성샌스',1300000,'Windows 8','1111',sysdate,0,null,0);
insert into Electronics values('SHV-E250S','Galaxy Note II',1000000,'Wi-Fi bluetooth 4.0','1111',sysdate,0,null,0);
insert into Electronics values('NT900X4D-A99S','삼성샌스',1700000,'Windows 8','1111',sysdate,0,null,0);


SELECT SYS_DATETIME; --시스템 현재날짜와 시분초

SELECT writeday, to_char(writeday, 'YYYY-MM-DD HH:MI:SS AM') FROM Electronics;

 select model_num,model_name,price, description,password , to_char(writeday, 'YYYY-MM-DD HH:MI:SS AM') as writeday ,readnum
 from Electronics ;

