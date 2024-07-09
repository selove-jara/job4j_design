insert into roles (role_name) values ('admin');
insert into users (user_name, role_id) values ('Вася', 1);
insert into rules (rule_name) values ('как то');
insert into roles_rules (role_id, rule_id) values (1, 1);
insert into states (state_name) values ('В работе');
insert into categories (category_name) values ('Консультации');
insert into items (item_name, user_id, category_id, state_id) values ('по курсу', 1, 1, 1);
insert into comments (comment_name, item_id) values ('comments***', 1);
insert into attachs (attach_name, item_id) values ('attach', 1);