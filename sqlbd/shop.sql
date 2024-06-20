create table owner(
    id serial primary key,
    name varchar(255),
);

create table buyer(
    id serial primary key,
    name varchar(255),

);

create table shop(
    id serial primary key,
    title varchar(200),
    turnover int
    owner_id int references owners(id),
    buyer_id int references buyer(id)
);

create table multicooker(
    id serial primary key,
    title varchar(200),
    price int,
    title varchar(200),
    shop_id int references shop(id) unique
);

create table categor(
    id serial primary key,
    title varchar(200),
    shop_id int references shop(id) unique
    multicooker_id references multicooker(id)
);