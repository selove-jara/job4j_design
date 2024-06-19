create table city(
id serial primary key,
name varchar(100),
date_creation date,
fact_city text);
insert into city(name, date_creation, fact_city) 
values('Москва', '1147-01-24', 'Москва входит в десять самых крупных городов мира');
update city set fact_city = 'Москва входит в десять самых крупных городов мира.';
select * from city;
delete from city;
