create table devices(
    id serial primary key,
    name  varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices(name, price) values('Тостер', 3600);
insert into devices(name, price) values('Принтер', 13600);
insert into devices(name, price) values('Гриль', 20000);
insert into devices(name, price) values('Пылесос', 10000);
insert into devices(name, price) values('Ноутбук', 20000);

insert into people(name) values('Вася');
insert into people(name) values('Андрей');
insert into people(name) values('Алеша');

insert into devices_people(people_id, device_id) values (1, 2);
insert into devices_people(people_id, device_id) values (2, 2);
insert into devices_people(people_id, device_id) values (3, 3);
insert into devices_people(people_id, device_id) values (1, 4);
insert into devices_people(people_id, device_id) values (2, 5);

select avg(price) from devices;

select people.name, avg(devices.price)
from devices_people dp
         join people
              on dp.people_id = people.id
              join devices
              on dp.device_id = devices.id
group by people.name;

insert into people(name) values('Петька');
insert into devices_people(people_id, device_id) values (4, 1);

select people.name, avg(devices.price)
from devices_people dp
         join people
              on dp.people_id = people.id
              join devices
              on dp.device_id = devices.id
              where devices.price > 5000
group by people.name;