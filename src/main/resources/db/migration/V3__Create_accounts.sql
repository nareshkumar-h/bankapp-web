create table accounts
(
	id bigint unsigned primary key auto_increment,
	user_id bigint unsigned not null,
	account_type_id int not null,
	balance bigint not null default 0,
	active boolean not null default true,
	created_by bigint unsigned not null,
	created_date timestamp not null,
	modified_by bigint unsigned not null, 
	modified_date timestamp not null,
	foreign key (account_type_id) references account_types(id),
	foreign key (created_by) references users(id),
	foreign key (modified_by) references users(id),
	unique (user_id, account_type)
);

insert into accounts ( user_id, account_type_id, balance,created_by,created_date, modified_by,modified_date)
values
( 1, 1, 10000, 1, DATETIME() , 1, DATETIME() ),
( 2, 1, 10000, 2, DATETIME() , 2, DATETIME() );
