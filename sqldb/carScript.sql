create table bodies (
    id serial primary key,
    name  varchar(255)
);

create table engines (
    id serial primary key,
    name  varchar(255)
);

create table transmissions (
   id serial primary key,
    name  varchar(255)
);

create table cars (
   id serial primary key,
    name  varchar(255),
    bodies_id int references bodies(id),
    engines_id int references engines(id),
    transmissions_id int references transmissions(id)
);

insert into bodies(name) values('хэтчбек');
insert into bodies(name) values('седан');
insert into bodies(name) values('пикап');||
insert into bodies(name) values('кабриолет');

insert into engines(name) values('V4');
insert into engines(name) values('V6');
insert into engines(name) values('V8');
insert into engines(name) values('V12');

insert into transmissions(name) values('Механическая');
insert into transmissions(name) values('Автоматические');
insert into transmissions(name) values('Роботизированные');
insert into transmissions(name) values('Комбинированные');


insert into cars(name, bodies_id, engines_id, transmissions_id) values('BMW', 4, 3, 2);
insert into cars(name, bodies_id, engines_id, transmissions_id) values('Volkswagen', 2, 1, 1);
insert into cars(name, bodies_id, engines_id, transmissions_id) values('Lada', 1, 1, 1);
insert into cars(name, bodies_id, engines_id, transmissions_id) values('Mercedes-Benz', 2, 3, 3);
insert into cars(name, bodies_id, engines_id, transmissions_id) values('Mercedes-Benz', 4, null , 3);

select cars.name, bodies.name, engines.name, transmissions.name
from cars
left join bodies on cars.bodies_id = bodies.id
left join engines on cars.engines_id = engines.id
left join transmissions on cars.transmissions_id = transmissions.id;

select *
from bodies
	left join cars on  bodies.id = cars.bodies_id
where cars.bodies_id is null;

select * from engines
	left join cars on  engines.id = cars.engines_id
where cars.engines_id is null;

select * from transmissions
	left join cars on  transmissions.id = cars.transmissions_id
where cars.transmissions_id is null;
