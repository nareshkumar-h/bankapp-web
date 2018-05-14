create table transactions
(
id bigint unsigned primary key auto_increment,
account_id bigint unsigned not null,
transaction_type varchar(100) not null,
amount int not null,
active boolean not null default true,
status varchar(100) not null,
transaction_date timestamp not null
);


insert into transactions ( account_id, transaction_type, amount, status, transaction_date)
values 
(1,'CREDIT',1000,'SUCCESS',DATETIME());
