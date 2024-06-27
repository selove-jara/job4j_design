create table owners(
    id   serial primary key,
    name varchar(50)
);

insert into owners (name)
values ('Василий Васильков'), ('Андрей Андреев'), ('Иван Иванов');



create table manufacturer(
    id   serial primary key,
    name varchar(50)
);

insert into manufacturer (name)
values ('LADA'), ('VW'), ('BMW');

create table model(
    id   serial primary key,
    name varchar(50),
    manufacturer_id int references manufacturer(id)
);

insert into model (manufacturer_id, name)
values (1, 'priora'), (1, 'granta'), (2, 'polo'), (2, 'passat'), (3, 'X5');

create table rent(
    id   serial primary key,
    owners_id int references owners(id),
    model_id int references model(id)
);

insert into rent (owners_id, model_id)
values (1,1), (1,1), (1,2), (1,3), (2,2), (2,3), (3,1), (3,2), (3,4), (3,5);

select o.name, count(m.name), m.name
from owners o
         join rent r on o.id = r.owners_id
         join model ml on r.model_id = ml.id
         join manufacturer m on ml.manufacturer_id = m.id
group by (o.name, m.name)
having count(m.name) >= 2;

create view owners_with_2_or_more_cars as
select o.name as o, count(m.name), m.name as manufacturer
from owners as o
         join rent r on o.id = r.owners_id
         join model ml on r.model_id = ml.id
         join manufacturer m on ml.manufacturer_id = m.id
group by (o.name, m.name)
having count(m.name) >= 2;

select * from owners_with_2_or_more_cars;



select m.name, ml.name, count(ml.name), o.name
from owners o
         join rent r on o.id = r.owners_id
         join model ml on r.model_id = ml.id
	     join manufacturer m on ml.manufacturer_id = m.id
group by (m.name, ml.name, o.name)
having count(ml.name) <= 2;

create view car_models_less_than_2 as
select m.name as m, ml.name, count(ml.name) as ml, o.name as o
from owners o
         join rent r on o.id = r.owners_id
         join model ml on r.model_id = ml.id
	     join manufacturer m on ml.manufacturer_id = m.id
group by (m.name, ml.name, o.name)
having count(ml.name) <= 2;

select * from car_models_less_than_2;