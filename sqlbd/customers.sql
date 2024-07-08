CREATE TABLE customers(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

insert into customers(first_name, last_name, age, country) values('Андрей', 'Морозов', 26, 'Россия');
insert into customers(first_name, last_name, age, country) values('Василий', 'Васильков', 30, 'Россия');
insert into customers(first_name, last_name, age, country) values('Анна', 'Анюткина', 18, 'Россия');
insert into customers(first_name, last_name, age, country) values('Петр', 'Петров', 22, 'Россия');


select * from customers
where age = (select min (age) from customers);

CREATE TABLE orders(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

insert into orders(customer_id, amount) values(1, 2200);
insert into orders(customer_id, amount) values(1, 1000);
insert into orders(customer_id, amount) values(2, 999);
insert into orders(customer_id, amount) values(3, 1235);

select * from customers
where id NOT IN (select customer_id from Orders);