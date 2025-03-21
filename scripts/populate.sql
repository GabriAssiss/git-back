use cit;

DELETE FROM employees;
DELETE FROM residents;
DELETE FROM peoples;
DELETE FROM profile_permissions;
DELETE FROM profiles;

INSERT INTO `profiles` (`email`, `password`)
VALUES ('teste1@teste.com', '$2a$12$NqfuXSnf0DQc4zGU.dPeteDACG1gbnCPItjqu/FrnvOgbsrkh1DRa'),
       ('teste2@teste.com', '$2a$12$NqfuXSnf0DQc4zGU.dPeteDACG1gbnCPItjqu/FrnvOgbsrkh1DRa'),
       ('teste3@teste.com', '$2a$12$NqfuXSnf0DQc4zGU.dPeteDACG1gbnCPItjqu/FrnvOgbsrkh1DRa'),
       ('teste4@teste.com', '$2a$12$NqfuXSnf0DQc4zGU.dPeteDACG1gbnCPItjqu/FrnvOgbsrkh1DRa');

INSERT INTO `profile_permissions` (`profile_id`, `permissions`)
VALUE ((SELECT id FROM profiles WHERE email = 'teste1@teste.com'), 'ROLE_ADMIN'),
      ((SELECT id FROM profiles WHERE email = 'teste2@teste.com'), 'ROLE_RESIDENT'),
      ((SELECT id FROM profiles WHERE email = 'teste3@teste.com'), 'ROLE_ADMIN'),
      ((SELECT id FROM profiles WHERE email = 'teste4@teste.com'), 'ROLE_RESIDENT');

INSERT INTO `peoples` (`name`, `profile_id`)
VALUES ('teste 1', (SELECT id FROM profiles WHERE email = 'teste1@teste.com')),
       ('teste 2', (SELECT id FROM profiles WHERE email = 'teste2@teste.com')),
       ('teste 3', (SELECT id FROM profiles WHERE email = 'teste3@teste.com')),
       ('teste 4', (SELECT id FROM profiles WHERE email = 'teste4@teste.com'));

INSERT INTO `employees` (`people_id`, `role`)
VALUES ((SELECT id FROM peoples WHERE name = 'teste 1'), 'Sindico'),
       ((SELECT id FROM peoples WHERE name = 'teste 3'), 'Sindico');

INSERT INTO `residents` (`people_id`)
VALUES ((SELECT id FROM peoples WHERE name = 'teste 2')),
       ((SELECT id FROM peoples WHERE name = 'teste 4'));