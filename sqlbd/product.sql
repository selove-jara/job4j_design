create table products(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create trigger discount_trigger
    after insert
    on products
    for each row
    execute procedure discount();

create  or replace function discount()
    returns trigger as
$$
    begin
        update products
        set price = price - price * 0.2
        where count <= 5
        and id = new.id;
        return new;
    end;
$$
LANGUAGE 'plpgsql';

insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115);
insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price)
VALUES ('product_23', 'producer_23', 2, 100);

select * from products;

create
or replace function tax()
    returns trigger as
$$
    begin
        update products
        set price = price - price * 0.2
        where id = (select id from inserted)
        and count <= 5;
        return new;
    end;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure tax();

create or replace function tax1()
returns trigger as
$$
    begin
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    end;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger1
    after insert
    on products
    referencing new table as
                    inserted
    for each STATEMENT
    execute procedure tax1();


insert into products (name, producer, count, price)
VALUES ('product_123', 'producer_123', 2, 100);

select * from products;

drop trigger tax_trigger1 on products;


create or replace function tax2()
returns trigger as
$$
    begin
        update products
        set price = price + price * 0.2;
        return new;
    end;
$$
LANGUAGE 'plpgsql';

create trigger tax2_trigger
    before insert
    on products
    for each row
    execute procedure tax2();

insert into products (name, producer, count, price)
VALUES ('product_123', 'producer_123', 2, 100);

select * from products;

create table history_of_price(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

create or replace function add_in_history_of_price()
returns trigger as
$$
    begin
        insert into history_of_price (name, price, date)
        values (NEW.name, NEW.price, current_timestamp);
        return new;
    end;
$$
LANGUAGE 'plpgsql';

create trigger add_in_history_of_price_trigger
    after  insert
    on products
    for each row
    execute procedure add_in_history_of_price();

    insert into products (name, producer, count, price)
VALUES ('product_123', 'producer_123', 2, 100);

select * from history_of_price;
