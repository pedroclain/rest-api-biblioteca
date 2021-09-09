insert into authority (name) values ("READ");
insert into authority (name) values ("READ_ADMIN");
insert into authority (name) values ("WRITE");
insert into authority (name) values ("DELETE");
insert into role (name) values ("ROLE_ADMIN");
insert into role (name) values ("ROLE_MANAGER");
insert into role_authority (role_id, authority_id) values (1,1);
insert into role_authority (role_id, authority_id) values (1,2);
insert into role_authority (role_id, authority_id) values (1,3);
insert into role_authority (role_id, authority_id) values (2,1);
insert into role_authority (role_id, authority_id) values (2,3);
-- password 123
insert into application_user (username, password, role) values ("admin", "$2a$10$9tqMAchtgdQHOb6vriKlm.f.rV2.dSBpMZAPVKAw0mb4lAuh1GpuO", 1);
insert into application_user (username, password, role) values ("manager", "$2a$10$9tqMAchtgdQHOb6vriKlm.f.rV2.dSBpMZAPVKAw0mb4lAuh1GpuO", 2);