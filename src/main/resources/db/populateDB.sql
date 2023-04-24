DELETE FROM user_role;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (user_id, id, dateTime, description, calories)
VALUES (100000, nextval('global_seq'), '2023-04-18 22:17:00', 'Овсянка на молоке', 100),
       (100000, nextval('global_seq'), '2023-04-18 22:19:00', 'Картофельное пюре', 400),
       (100000, nextval('global_seq'), '2023-04-20 22:20:00', 'Куриная котлета', 500);
