CREATE DATABASE travel_agency_repository;
DROP DATABASE travel_agency_repository;

-- Drop tables
DROP TABLE company;

DROP TABLE profile;

DROP TABLE users;

DROP TABLE voucher;

DROP TABLE voucher_info;

-- Create and insert queries
CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    username   VARCHAR(256) NOT NULL,
    password   VARCHAR(256) NOT NULL,
    email      VARCHAR(256) NOT NULL UNIQUE,
    company_id BIGINT REFERENCES company (id)
);

INSERT INTO users (username, password, email, company_id)
VALUES ('UName1', '123', 'name1@mail.com', 3),
       ('UName2', '123', 'name2@mail.com', 2),
       ('UName3', '123', 'name3@mail.com', 3),
       ('UName4', '123', 'name4@mail.com', 4),
       ('UName5', '123', 'name5@mail.com', 1);

CREATE TABLE profile
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT REFERENCES users (id) ON DELETE CASCADE,
    first_name VARCHAR(128),
    last_name  VARCHAR(128),
    birthday   TIMESTAMP
);

INSERT INTO profile(user_id, first_name, last_name, birthday)
VALUES (6, 'FName1', 'LName1', '1999-11-05'),
       (7, 'FName2', 'LName2', '2000-10-12'),
       (8, 'FName3', 'LName3', '2001-12-12'),
       (9, 'FName4', 'LName4', '2002-06-17'),
       (10, 'FName5', 'LName5', '2000-08-25');

CREATE TABLE company
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL
);

ALTER TABLE company
ADD COLUMN company_image VARCHAR(128);

INSERT INTO company(name)
VALUES ('Company1'),
       ('Company2'),
       ('Company3'),
       ('Company4');

CREATE TABLE voucher
(
    id          BIGSERIAL PRIMARY KEY,
    company_id  BIGINT REFERENCES company (id) ON DELETE CASCADE,
    user_id     BIGINT REFERENCES users (id),
    name        VARCHAR(64) NOT NULL,
    price       NUMERIC     NOT NULL CHECK (price > 0),
    type        VARCHAR(32),
    description VARCHAR(256)
);

INSERT INTO voucher(company_id, user_id, name, price, type, description)
VALUES (1, 6, 'Voucher1', 123.25, 'THERAPY', null),
       (2, 7, 'Voucher2', 193.25, 'SHOPPING', null),
       (3, 8, 'Voucher3', 143.25, 'CRUISE', null),
       (2, 9, 'Voucher4', 113.25, 'SHOPPING', null),
       (3, 6, 'Voucher5', 173.25, 'CRUISE', null),
       (4, 10, 'Voucher6', 153.25, 'EXCURSION', null),
       (1, 8, 'Voucher7', 123.25, 'THERAPY', null),
       (2, 7, 'Voucher8', 293.25, 'SHOPPING', null),
       (1, 9, 'Voucher9', 143.25, 'CRUISE', null),
       (3, 9, 'Voucher10', 113.25, 'SHOPPING', null),
       (3, 6, 'Voucher11', 173.25, 'REST', null),
       (4, 10, 'Voucher12', 153.25, 'EXCURSION', null),
       (3, 9, 'Voucher13', 223.25, 'THERAPY', null),
       (4, 8, 'Voucher14', 193.25, 'REST', null),
       (3, 10, 'Voucher15', 143.25, 'CRUISE', null),
       (4, 10, 'Voucher16', 313.25, 'REST', null),
       (1, 6, 'Voucher17', 173.25, 'REST', null),
       (3, 7, 'Voucher18', 153.25, 'EXCURSION', null);

CREATE TABLE voucher_info
(
    id         BIGSERIAL PRIMARY KEY,
    voucher_id BIGINT REFERENCES voucher (id) ON DELETE CASCADE,
    country    VARCHAR(32) NOT NULL,
    start_on   TIMESTAMP   NOT NULL,
    end_on     TIMESTAMP   NOT NULL,
    transport  VARCHAR(32) NOT NULL,
    food       BOOLEAN     NOT NULL
);

INSERT INTO voucher_info(voucher_id, country, start_on, end_on, transport, food)
VALUES (1, 'BY', '2023-08-22', '2023-08-30', 'BUS', true),
       (2, 'UK', '2023-09-22', '2023-09-30', 'BUS', false),
       (3, 'USA', '2023-09-10', '2023-09-16', 'PLANE', true),
       (4, 'PL', '2023-09-22', '2023-10-05', 'PLANE', false),
       (5, 'USA', '2023-10-10', '2023-10-20', 'CRUISE', true),
       (6, 'USA', '2023-11-01', '2023-11-08', 'CRUISE', false),
       (7, 'BY', '2024-01-22', '2024-01-30', 'BUS', true),
       (8, 'UK', '2024-02-22', '2024-02-28', 'BUS', true),
       (9, 'BY', '2024-03-10', '2024-03-16', 'BUS', true),
       (10, 'PL', '2024-04-22', '2024-05-05', 'PLANE', false),
       (11, 'BY', '2024-06-10', '2024-06-20', 'BUS', true),
       (12, 'PL', '2023-11-10', '2023-11-18', 'PLANE', false),
       (13, 'BY', '2023-08-22', '2023-08-30', 'BUS', true),
       (14, 'UK', '2023-09-22', '2023-09-30', 'CRUISE', false),
       (15, 'BY', '2023-09-10', '2023-09-16', 'BUS', true),
       (16, 'PL', '2023-09-22', '2023-10-05', 'PLANE', true),
       (17, 'BY', '2023-10-10', '2023-10-20', 'BUS', true),
       (18, 'USA', '2023-11-01', '2023-11-08', 'PLANE', false);

ALTER TABLE voucher_info
ALTER COLUMN food TYPE VARCHAR(32);

-- Select queries
-- 1. Select voucher by transport
SELECT v.id,
       v.name,
       v.price,
       v.type,
       v.description,
       i.country,
       i.start_on,
       i.end_on,
       i.transport,
       i.food
FROM voucher_info i
         LEFT JOIN voucher v on i.voucher_id = v.id
WHERE i.transport = 'BUS';

-- 2. Select voucher by food
SELECT v.id,
       v.name,
       v.price,
       v.type,
       v.description,
       i.country,
       i.start_on,
       i.end_on,
       i.transport,
       i.food
FROM voucher v
         LEFT JOIN voucher_info i ON v.id = i.id
WHERE i.food = 'AI';

-- 3. Select voucher by amount of the days
SELECT v.id,
       v.name,
       v.price,
       v.type,
       v.description,
       i.country,
       i.start_on,
       i.end_on,
       i.transport,
       i.food
FROM voucher v
         LEFT JOIN voucher_info i ON v.id = i.id
WHERE dateDiff(i.end_on, i.start_on) >= 10;

-- 4. Select by voucher type
SELECT v.id,
       v.name,
       v.price,
       v.type,
       v.description,
       i.country,
       i.start_on,
       i.end_on,
       i.transport,
       i.food
FROM voucher v
         LEFT JOIN voucher_info i ON v.id = i.id
WHERE v.type = 'SHOPPING'
   AND v.price >= 125.0 AND v.price <= 139.0
AND i.transport = 'PLANE';

-- 5. Select by company
SELECT v.id,
       v.name,
       v.price,
       v.type,
       v.description,
       i.country,
       i.start_on,
       i.end_on,
       i.transport,
       i.food
FROM voucher v
         LEFT JOIN voucher_info i ON v.id = i.id
         LEFT JOIN company c on v.company_id = c.id
WHERE c.name = 'Company2';

-- 5.1. Select by company id
SELECT v.id,
       v.name,
       v.price,
       v.type,
       v.description,
       i.country,
       i.start_on,
       i.end_on,
       i.transport,
       i.food
FROM voucher v
         LEFT JOIN voucher_info i ON v.id = i.id
         LEFT JOIN company c on v.company_id = c.id
WHERE c.id = 1;

-- 6. Select by country
SELECT v.id,
       v.name,
       v.price,
       v.type,
       v.description,
       i.country,
       i.start_on,
       i.end_on,
       i.transport,
       i.food
FROM voucher v
         LEFT JOIN voucher_info i ON v.id = i.id
WHERE i.country = 'UK';


-- Sorting
-- 1. Sort vouchers by price
SELECT v.id,
       v.name,
       v.price,
       v.type,
       v.description,
       i.country,
       i.start_on,
       i.end_on,
       i.transport,
       i.food
FROM voucher v
         LEFT JOIN voucher_info i ON v.id = i.id
ORDER BY v.price
LIMIT 6;

-- 2. Sort vouchers by amount of the days
SELECT v.id,
       v.name,
       v.price,
       v.type,
       v.description,
       i.country,
       i.start_on,
       i.end_on,
       i.transport,
       i.food
FROM voucher v
         LEFT JOIN voucher_info i ON v.id = i.id
ORDER BY dateDiff(i.end_on, i.start_on);

-- 3. Sort vouchers by food
SELECT v.id,
       v.name,
       v.price,
       v.type,
       v.description,
       i.country,
       i.start_on,
       i.end_on,
       i.transport,
       i.food
FROM voucher v
         LEFT JOIN voucher_info i ON v.id = i.id
ORDER BY i.food DESC;


-- Delete operations
-- If a user delete, profile should delete too.
INSERT INTO users(username, password, email, company_id)
VALUES ('UName6', 123, 'name6@gmail.com', 2);

INSERT INTO profile(user_id, first_name, last_name, birthday)
VALUES (11, 'FName6', 'LName6', '2003-05-16');

DELETE
FROM users
WHERE id = 11;

-- If a company delete, the vouchers should delete too.
INSERT INTO company(name)
VALUES ('Company5');

INSERT INTO voucher(company_id, user_id, name, price, type, description)
VALUES (5, 6, 'Voucher19', 45.23, 'REST', null),
       (5, 6, 'Voucher19', 45.23, 'REST', null);

DELETE
FROM company
WHERE id = 5;

-- If a voucher delete, the voucher_info should delete too.
INSERT INTO voucher(company_id, user_id, name, price, type, description)
VALUES (4, 6, 'VoucherTest', 45.23, 'REST', null);

INSERT INTO voucher_info(voucher_id, country, start_on, end_on, transport, food)
VALUES (21, 'PL', '2000-02-02', '2000-03-02', 'BUS', true);

DELETE
FROM voucher
WHERE id = 21;


-- Functions

CREATE OR REPLACE FUNCTION dateDiff(endsAt TIMESTAMP, startsAt TIMESTAMP) RETURNS integer AS $$
BEGIN
    RETURN DATE_PART('day', endsAt - startsAt);
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION  dateDiff(endsAt TIMESTAMP, startsAt TIMESTAMP);