
insert into users (id,username,password,enabled) values ( 1, 'saraadmin', '123456',1);
insert into users (id,username,password,enabled) values ( 2, 'sarauser', '123456',1);
insert into Role (id,role) values (  1, 'ADMIN');
insert into Role (id,role) values (  2, 'USER');

insert into USERS_ROLES (users_username,roles_role) values (  'saraadmin','admin');
