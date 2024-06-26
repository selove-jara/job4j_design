create table teens(
id serial primary key,
    name  varchar(255),
	gender varchar(255)
);

insert into teens(name, gender) values ('Василиса', 'жен');
insert into teens(name, gender) values ('Андрей', 'муж');
insert into teens(name, gender) values ('Вася', 'муж');
insert into teens(name, gender) values ('Петя', 'муж');
insert into teens(name, gender) values ('Мария', 'жен');
insert into teens(name, gender) values ('Ксения', 'жен');
insert into teens(name, gender) values ('Иван', 'муж');
insert into teens(name, gender) values ('Виктория', 'жен');
insert into teens(name, gender) values ('Анна', 'жен');
insert into teens(name, gender) values ('Женя', 'жен');
insert into teens(name, gender) values ('Женя', 'муж');

select m.name, w.name
	from teens m
	cross join teens w
where m.gender = 'муж' and w.gender = 'жен';