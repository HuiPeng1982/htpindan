drop table if exists pd_subscription;
drop table if exists pd_order_item;
drop table if exists pd_order;
drop table if exists pd_user_email;
drop table if exists pd_user;
drop table if exists pd_item_price;
drop table if exists pd_item;

create table pd_subscription (
	id bigint auto_increment,
	item_id bigint not null,
	buyer_id bigint not null,
	seller_id bigint,
	descript varchar(255),
	created_date timestamp not null,
	arrived_date timestamp,
	state varchar(64) not null,
	primary key (id)
) engine=InnoDB;

create table pd_order_item (
	id bigint auto_increment,
	order_id bigint not null,
	item_id  bigint not null,
	quantity SMALLINT not null,
	currency TINYINT(1) not null,
	price DOUBLE(16,2) not null default '0.00',
	descript varchar(255),
	primary key (id)
) engine=InnoDB;

create table pd_order (
	id bigint auto_increment,
	user_id bigint not null,
	email varchar(255) not null,
	supplier varchar(255) not null,
	order_num varchar(255),
	order_info varchar(500),
	order_date varchar(500),
	delivery_address varchar(500),
	delivery_tracking varchar(500),
	delivery_date varchar(500),
	state varchar(64) not null,
	primary key (id)
) engine=InnoDB;

create table pd_user_email (
	id bigint auto_increment,
	email varchar(255) not null unique,
	user_id bigint not null,
	validated tinyint(1) not null default '0',
	salt varchar(64) not null,
	primary key (id)
) engine=InnoDB;

create table pd_user (
	id bigint auto_increment,
	email varchar(255) not null unique,
	name varchar(64) not null unique,
	password varchar(255) not null,
	salt varchar(64) not null,
	roles varchar(255) not null,
	register_date timestamp not null,
	primary key (id)
) engine=InnoDB;

create table pd_item (
	id bigint auto_increment,
	name varchar(255) not null,
	brand varchar(255),
	url varchar(500),
	img varchar(500),
	supplier varchar(64) not null,
	original_num varchar(64),
	tag varchar(64),
	primary key (id)
) engine=InnoDB;

create table pd_item_price (
	id bigint auto_increment,
	item_id bigint not null,
	currency TINYINT(1) not null,
	price DOUBLE(16,2) not null default '0.00',
	sold_by varchar(64),
	collected_date timestamp not null,
	primary key (id)
) engine=InnoDB;