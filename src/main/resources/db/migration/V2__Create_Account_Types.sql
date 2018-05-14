create table account_types
( 
id int primary key,
name varchar(100) not null,
unique (name)
);

insert into account_types (id,name) values 
(1,'SAVINGS'),
(2,'CURRENT');