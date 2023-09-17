CREATE DATABASE travel_agency_repository;
DROP DATABASE travel_agency_repository;

-- Drop tables
DROP TABLE company;

DROP TABLE profile;

DROP TABLE users;

DROP TABLE voucher;

DROP TABLE voucher_info;

DROP TABLE booking;

-- Create and insert queries
CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(256) NOT NULL,
    password VARCHAR(256) NOT NULL,
    email    VARCHAR(256) NOT NULL UNIQUE
);

ALTER TABLE users
ADD CONSTRAINT email_unique UNIQUE (email);

INSERT INTO users (username, password, email)
VALUES ('UName', '123', 'uname@gmail.com'),
       ('UName1', '1231', 'uname1@gmail.com'),
       ('UName2', '1232', 'uname2@gmail.com'),
       ('UName3', '1233', 'uname3@gmail.com'),
       ('UName4', '1234', 'uname4@gmail.com'),
       ('UName5', '1235', 'uname5@gmail.com'),
       ('UName6', '1236', 'uname6@gmail.com'),
       ('UName7', '1237', 'uname7@gmail.com'),
       ('UName8', '1238', 'uname8@gmail.com'),
       ('UName9', '1239', 'uname9@gmail.com'),
       ('UName10', '12310', 'uname10@gmail.com'),
       ('UName11', '12311', 'uname11@gmail.com'),
       ('UName12', '12312', 'uname12@gmail.com');

CREATE TABLE profile
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT REFERENCES users (id) ON DELETE CASCADE,
    first_name VARCHAR(128),
    last_name  VARCHAR(128),
    birthday   DATE
);

INSERT INTO profile(user_id, first_name, last_name, birthday)
VALUES (1, 'FName', 'LName', '2000-09-15'),
       (2, 'FName1', 'LName1', '2001-08-24'),
       (3, 'FName2', 'LName2', '2002-04-13'),
       (4, 'FName3', 'LName3', '2003-06-04'),
       (5, 'FName4', 'LName4', '2003-10-08'),
       (6, 'FName5', 'LName5', '2002-09-16'),
       (7, 'FName6', 'LName6', '2001-04-24'),
       (8, 'FName7', 'LName7', '2004-03-14'),
       (9, 'FName8', 'LName8', '2001-01-05'),
       (10, 'FName9', 'LName9', '2002-01-21'),
       (11, 'FName10', 'LName10', '2004-01-21'),
       (12, 'FName11', 'LName11', '2001-05-29'),
       (13, 'FName12', 'LName12', '2000-02-28');

CREATE TABLE company
(
    id            BIGSERIAL PRIMARY KEY,
    name          VARCHAR(128) NOT NULL,
    company_image VARCHAR(128)
);

ALTER TABLE company
    ADD COLUMN company_image VARCHAR(128);

INSERT INTO company(name, company_image)
VALUES ('Spain Agency', 'spainCompany.webp'),
       ('GreeceAgency', 'greeceCompany.webp'),
       ('Belarus Agency', 'belarusCompany.webp'),
       ('Brazil Agency', 'brazilCompany.webp');

CREATE TABLE voucher
(
    id          BIGSERIAL PRIMARY KEY,
    company_id  BIGINT REFERENCES company (id) ON DELETE CASCADE,
    name        VARCHAR(64) NOT NULL,
    price       FLOAT       NOT NULL CHECK (price > 0),
    type        VARCHAR(32),
    description VARCHAR(256),
    image       VARCHAR(256)
);

INSERT INTO voucher(company_id, name, price, type, description, image)
VALUES (2, 'Voucher', 123.25, 'THERAPY', null, 'greece.webp'),
       (1, 'Voucher1', 136.75, 'SHOPPING', null, 'spain.webp'),
       (2, 'Voucher2', 143.59, 'THERAPY', null, 'greece.webp'),
       (1, 'Voucher3', 153.29, 'SHOPPING', null, 'spain.webp'),
       (4, 'Voucher4', 167.49, 'EXCURSION', null, 'brazil.webp'),
       (3, 'Voucher5', 173.46, 'CRUISE', null, 'belarus.webp'),
       (1, 'Voucher6', 183.15, 'REST', null, 'spain.webp'),
       (3, 'Voucher7', 199.25, 'THERAPY', null, 'belarus.webp'),
       (2, 'Voucher8', 131.35, 'EXCURSION', null, 'greece.webp'),
       (1, 'Voucher9', 143.55, 'THERAPY', null, 'spain.webp'),
       (4, 'Voucher10', 123.45, 'REST', null, 'brazil.webp');

CREATE TABLE voucher_info
(
    id         BIGSERIAL PRIMARY KEY,
    voucher_id BIGINT REFERENCES voucher (id) ON DELETE CASCADE,
    country    VARCHAR(32) NOT NULL,
    start_at   DATE        NOT NULL,
    end_at     date        NOT NULL,
    transport  VARCHAR(32) NOT NULL,
    food       VARCHAR(64) NOT NULL
);

ALTER TABLE voucher_info
    ALTER COLUMN end_at TYPE DATE;

ALTER TABLE voucher_info
    ALTER COLUMN start_at TYPE DATE;

ALTER TABLE profile
    ALTER COLUMN birthday TYPE DATE;

INSERT INTO voucher_info(voucher_id, country, start_at, end_at, transport, food)
VALUES (1, 'PL', '2023-08-22', '2023-08-30', 'BUS', 'AI'),
       (2, 'BY', '2023-09-22', '2023-09-30', 'PLANE', 'EP'),
       (3, 'UK', '2023-09-10', '2023-09-16', 'CRUISE', 'AI'),
       (4, 'USA', '2023-09-22', '2023-10-05', 'BUS', 'BB'),
       (5, 'BY', '2023-10-10', '2023-10-20', 'BUS', 'EP'),
       (6, 'UK', '2023-11-01', '2023-11-08', 'BUS', 'BB'),
       (7, 'USA', '2024-01-22', '2024-01-30', 'PLANE', 'FB'),
       (8, 'PL', '2024-02-22', '2024-02-28', 'BUS', 'RO'),
       (9, 'PL', '2024-03-10', '2024-03-16', 'CRUISE', 'HB'),
       (10, 'BY', '2024-04-15', '2024-04-30', 'BUS', 'AI'),
       (11, 'USA', '2024-06-20', '2024-06-28', 'PLANE', 'RO');

ALTER TABLE voucher_info
    ALTER COLUMN food TYPE VARCHAR(32);


CREATE TABLE booking
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT REFERENCES users (id) ON DELETE CASCADE,
    voucher_id BIGINT REFERENCES voucher ON DELETE CASCADE
);

ALTER TABLE booking
    ADD COLUMN persons INT NOT NULL default 1;

-- Select queries
-- 1. Select voucher by transport
SELECT v.id,
       v.name,
       v.price,
       v.type,
       v.description,
       i.country,
       i.start_at,
       i.end_at,
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
       i.start_at,
       i.end_at,
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
       i.start_at,
       i.end_at,
       i.transport,
       i.food
FROM voucher v
         LEFT JOIN voucher_info i ON v.id = i.id
WHERE dateDiff(i.end_at, i.start_at) >= 10;

-- 4. Select by voucher type
SELECT v.id,
       v.name,
       v.price,
       v.type,
       v.description,
       i.country,
       i.start_at,
       i.end_at,
       i.transport,
       i.food
FROM voucher v
         LEFT JOIN voucher_info i ON v.id = i.id
WHERE v.type = 'SHOPPING'
  AND v.price >= 125.0
  AND v.price <= 139.0
  AND i.transport = 'PLANE';

-- 5. Select by company
SELECT v.id,
       v.name,
       v.price,
       v.type,
       v.description,
       i.country,
       i.start_at,
       i.end_at,
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
       i.start_at,
       i.end_at,
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
       i.start_at,
       i.end_at,
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
       i.start_at,
       i.end_at,
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
       i.start_at,
       i.end_at,
       i.transport,
       i.food
FROM voucher v
         LEFT JOIN voucher_info i ON v.id = i.id
ORDER BY dateDiff(i.end_at, i.start_at);

-- 3. Sort vouchers by food
SELECT v.id,
       v.name,
       v.price,
       v.type,
       v.description,
       i.country,
       i.start_at,
       i.end_at,
       i.transport,
       i.food
FROM voucher v
         LEFT JOIN voucher_info i ON v.id = i.id
ORDER BY i.food DESC;


-- Delete operations
-- If a user delete, profile should delete too.
INSERT INTO users(username, password, email)
VALUES ('UName6', 123, 'name6@gmail.com');

INSERT INTO profile(user_id, first_name, last_name, birthday)
VALUES (11, 'FName6', 'LName6', '2003-05-16');

DELETE
FROM users
WHERE id = 11;

-- If a company delete, the vouchers should delete too.
INSERT INTO company(name)
VALUES ('Company5');

INSERT INTO voucher(company_id, name, price, type, description)
VALUES (5, 'Voucher19', 45.23, 'REST', null),
       (5, 'Voucher19', 45.23, 'REST', null);

DELETE
FROM company
WHERE id = 5;

-- If a voucher delete, the voucher_info should delete too.
INSERT INTO voucher(company_id, name, price, type, description)
VALUES (4, 'VoucherTest', 45.23, 'REST', null);

INSERT INTO voucher_info(voucher_id, country, start_at, end_at, transport, food)
VALUES (21, 'PL', '2000-02-02', '2000-03-02', 'BUS', true);

DELETE
FROM voucher
WHERE id = 21;


-- Functions

CREATE OR REPLACE FUNCTION dateDiff(endsAt TIMESTAMP, startsAt TIMESTAMP) RETURNS integer AS
$$
BEGIN
    RETURN DATE_PART('day', endsAt - startsAt);
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION dateDiff(endsAt TIMESTAMP, startsAt TIMESTAMP);