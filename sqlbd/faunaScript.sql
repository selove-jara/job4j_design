create table fauna(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);


insert into fauna(name, avg_age, discovery_date)
values('Cat', 15475, '1648-09-24');
insert into fauna(name, avg_age, discovery_date)
values('Red_fish', 8888, '1648-09-24');
insert into fauna(name, avg_age, discovery_date)
values('crocodile', 5475, '2000-09-24');
insert into fauna(name, avg_age)
values('Cquirrel', 25475);

select * from fauna;

select * from fauna where name like '%fish%';

select * from fauna where avg_age > 10000 and avg_age < 21000;

select * from fauna where discovery_date is null;

select * from fauna where discovery_date < '01.01.1950';