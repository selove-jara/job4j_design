create table departments(
id serial primary key,
    name  varchar(255)
);

create table employees(
id serial primary key,
    name  varchar(255),
     departments_id int references departments (id)
);

insert into departments(name)
         values('Управление проектами'), ('Разработка'), ('Техническая поддержка'), ('Админ');

insert into employees(name, departments_id) values('Василий', 2);
insert into employees(name, departments_id) values('Андрей', 3);
insert into employees(name, departments_id) values('Алексей', 1);
insert into employees(name, departments_id) values('Иван', 2);
insert into employees(name, departments_id) values('Пётр', 1);
insert into employees(name) values('Маша');

select * from departments d
         left join employees e on e.departments_id = d.id;

select * from employees e
         right join departments d on e.departments_id = d.id;

select * from employees e
         full join departments d on e.departments_id = d.id;

select * from employees e cross join departments d;

select * from employees e
         left join departments d on e.departments_id = d.id
         where d.id is null;

select * from employees e
         left join departments d on e.departments_id = d.id;

select * from employees e
         right join departments d on e.departments_id = d.id;

select * from departments d
         right join employees e on e.departments_id = d.id;

select * from departments d
         left join employees e on e.departments_id = d.id;