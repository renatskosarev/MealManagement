DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2023-02-21 9:00', 'Завтрак', 560, 100000),
       ('2023-02-21 14:40', 'Обед', 490, 100000),
       ('2023-02-21 18:10', 'Быстрый перекус', 120, 100000),
       ('2023-02-21 21:00', 'Ужин', 900, 100000),
       ('2023-02-22 10:00', 'Завтрак', 400, 100000),
       ('2023-02-22 15:50', 'Обед', 600, 100000),
       ('2023-02-22 22:00', 'Ужин', 1100, 100000),
       ('2023-02-21 10:00', '(админ) Завтрак', 400, 100001),
       ('2023-02-21 15:50', '(админ) Обед', 600, 100001),
       ('2023-02-21 22:00', '(админ) Ужин', 1100, 100001);