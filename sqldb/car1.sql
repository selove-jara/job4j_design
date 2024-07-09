create table car1(
  id serial primary key,
  brand varchar(255),
   model varchar(255),
  price integer

insert into car1(brand, model, price) values('Volkswagen', 'Touareg', 50000);
insert into car1(brand, model, price) values('Volkswagen', 'Tiguan', 70000);
insert into car1(brand, model, price) values('Volkswagen', 'Polo', 40000);
insert into car1(brand, model, price) values('LADA', 'Priora', 10000);

savepoint first_savepoint;

begin transaction;
savepoint first_savepoint;

insert into car1(brand, model, price) values('Volkswagen', 'Polo2', 45000);

select * from car1;

update car1 set price = 8000 where brand = 'LADA';

 select * from car1;

savepoint two_savepoint;

rollback to first_savepoint;

select * from car1;

rollback to two_savepoint;

commit transaction;

select * from car1;