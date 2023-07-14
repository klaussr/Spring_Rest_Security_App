INSERT INTO users (id, user_name, email, password, created, updated, status)
VALUES (1, 'User', 'user@mail.ru', '$2a$09$By9.K12F69koPHCjF3OONepG4YfnZpUyTrOVNEe4lnsZIebJ5/G7S', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ACTIVE');

INSERT INTO users (id, user_name, email, password, created, updated,  status)
VALUES (2, 'Moderator', 'moderator@mail.ru', '$2a$09$crq1sbUwcgeDgHtycidXrewfYlrOBz3zZlKAWavzTd68hPdISM4/6', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ACTIVE');

INSERT INTO users (id, user_name, email, password, created, updated, status)
VALUES (3, 'Admin', 'admin@mail.ru', '$2a$09$RlByfKFjy.8ypkML7LPVc.r7dPa8uiCcYVdwOL1fs2FsHw2f1idtW', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),  'ACTIVE');

INSERT INTO roles (id, name, created, updated, status)
VALUES (1, 'ADMIN',  CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),  'ACTIVE');

INSERT INTO roles (id, name, created, updated, status)
VALUES (2, 'MODERATOR',  CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),  'ACTIVE');

INSERT INTO roles (id, name, created, updated, status)
VALUES (3, 'USER',  CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),  'ACTIVE');

INSERT INTO user_roles (user_id, role_id)
VALUES (1, 3);

INSERT INTO user_roles (user_id, role_id)
VALUES (2, 2);

INSERT INTO user_roles (user_id, role_id)
VALUES (3, 1);