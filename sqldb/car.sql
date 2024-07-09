create table car(
    id serial primary key,
    brand varchar(255),
	model varchar(255),
    VIN varchar(255)
);

create table owner(
    id serial primary key,
    name varchar(255),
    car_id int references car(id)
);

insert into car(brand, model, VIN) values('Volkswagen', 'Touareg', 17171717171717177);
insert into car(brand, model, VIN) values('Volkswagen', 'Tiguan', 17171717171717178);
insert into car(brand, model, VIN) values('Volkswagen', 'Polo', 17171717171717179);
insert into car(brand, model, VIN) values('LADA', 'Priora', 17171717171717170);
insert into car(brand, model, VIN) values('LADA', 'Priora', 17171717171717171);

insert into owner(name, car_id) values('Boris', 1);
insert into owner(name, car_id) values('Ivan', 2);
insert into owner(name, car_id) values('Kiril', 3);
insert into owner(name, car_id) values ('Marina', 4);
insert into owner(name, car_id) values ('Boris', 5);

select * from owner
join car on owner.car_id = car.id;

select o.name, c.brand
from owner as o join car as c on o.car_id = c.id;

select o.name as  "Имя" , c.brand as "Марка автомобиля", c.VIN
from owner as o join car as c on o.car_id = c.id;

select o.name "Имя" , c.brand "Марка автомобиля", c.VIN "Вин-код"
from owner o join car c on o.car_id = c.id;