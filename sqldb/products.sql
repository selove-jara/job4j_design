create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name  varchar(255),
	expired_date date,
    price float,
	type_id int references type(id)
);

insert into type(name) values('Сыр'), ('Мороженое'), ('Молоко'), ('Колбасы'), ('Йогурты');
select * from type;

insert into product(name, type_id, expired_date, price) values('Сыр плавленный', 1, '24.07.2024', 149);
insert into product(name, type_id, expired_date, price) values('Сыр моцарелла', 1, '21.07.2024', 499);
insert into product(name, type_id, expired_date, price) values('Сыр пармезан', 1, '21.07.2024', 999);
insert into product(name, type_id, expired_date, price) values('Сыр маасдам', 1, '21.07.2024', 999);
insert into product(name, type_id, expired_date, price) values('Мороженое клубничное', 2, '01.07.2024', 99);
insert into product(name, type_id, expired_date, price) values('Мороженое шоколадное', 2, '01.07.2024', 109);
insert into product(name, type_id, expired_date, price) values('Мороженое карамельное', 2, '01.07.2024', 209);
insert into product(name, type_id, expired_date, price) values('Мороженое пломбир', 2, '01.07.2024', 139);
insert into product(name, type_id, expired_date, price) values('Молоко Эконом', 3, '01.07.2024', 60);
insert into product(name, type_id, expired_date, price) values('Молоко Кубанка', 3, '01.07.2024', 99);
insert into product(name, type_id, expired_date, price) values('Молоко Молочка', 3, '01.06.2024', 199);
insert into product(name, type_id, expired_date, price) values('Вкусное Молоко', 3, '01.07.2024', 119);
insert into product(name, type_id, expired_date, price) values('Колбаса Папа может', 4, '01.07.2024', 119);
insert into product(name, type_id, expired_date, price) values('Колбаса Мама может', 4, '01.07.2024', 119);
insert into product(name, type_id, expired_date, price) values('Колбаса кто то может', 4, '01.05.2024', 119);
insert into product(name, type_id, expired_date, price) values('Йогурт за 300', 5, '01.07.2024', 999);
insert into product(name, type_id, expired_date, price) values('Йогурт за 1', 5, '29.05.2024', 60);

select * from product
where type_id = 1;

select * from product
where name like '%Мороженое%';

select * from product
where expired_date < current_date; --;

select t.name, type_id, max(p.price)
from product as p
join type as t on p.type_id = t.id
group by t.name, type_id;

select t.name, count(*)
from type t
join product p on t.id = p.type_id
group by t.name;

select * from product where type_id in(1,3);

select t.name, count(*)
from type t
join product p on t.id = p.type_id
group by t.name
having count(*) < 10;

select t.name, count(*)
from type t
join product p on t.id = p.type_id
group by t.name
having count(*) < 3;

select t.name, p.name, p.expired_date, p.price
from product p
join type t on p.type_id = t.id
group by p.name,  p.expired_date, p.price ,t.name;