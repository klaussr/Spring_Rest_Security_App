INSERT INTO users (id, user_name, email, password, created, updated, role, status)
VALUES (1, 'User', 'user@mail.ru', '$2a$09$By9.K12F69koPHCjF3OONepG4YfnZpUyTrOVNEe4lnsZIebJ5/G7S', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'USER', 'ACTIVE');

INSERT INTO users (id, user_name, email, password, created, updated, role, status)
VALUES (2, 'Moderator', 'moderator@mail.ru', '$2a$09$crq1sbUwcgeDgHtycidXrewfYlrOBz3zZlKAWavzTd68hPdISM4/6', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'MODERATOR', 'ACTIVE');

INSERT INTO users (id, user_name, email, password, created, updated, role, status)
VALUES (3, 'Admin', 'admin@mail.ru', '$2a$09$RlByfKFjy.8ypkML7LPVc.r7dPa8uiCcYVdwOL1fs2FsHw2f1idtW', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ADMIN', 'ACTIVE');