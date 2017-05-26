
insert into users (username,password) values (  'saraadmin','123456');
insert into users (username,password) values (  'sarauser','123456');
insert into Role (role,description) values (  'admin','administrateur');
insert into Role (role,description) values (  'user','simple user');
insert into USERS_ROLES (users_username,roles_role) values (  'saraadmin','admin');
