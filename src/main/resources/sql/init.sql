-- NBU rates by the dates
CREATE TABLE IF NOT EXISTS nbu_rates
(
    ccy_date DATE          NOT NULL,
    ccy      CHAR(3)       NOT NULL,
    rate     NUMERIC(6, 2) NOT NULL,
    PRIMARY KEY (ccy_date, ccy)
);
-- DROP TABLE nbu_rates;

-- Users
CREATE TABLE IF NOT EXISTS users
(
    id   INT PRIMARY KEY,
    name VARCHAR(50),
    age  INT
);
-- DROP TABLE users;

-- Account
CREATE TABLE IF NOT EXISTS accounts
(
    id         INT PRIMARY KEY,
    user_id    INT REFERENCES users (id),
    currency   CHAR(3),
    amount     BIGINT CHECK (amount > 0),
    start_date DATE NOT NULL,
    end_date   DATE NOT NULL
);
-- DROP TABLE accounts;

-- Credits
CREATE TABLE IF NOT EXISTS credits
(
    id         INT PRIMARY KEY,
    user_id    INT REFERENCES users (id),
    currency   CHAR(3),
    amount     BIGINT NOT NULL,
    start_date DATE   NOT NULL,
    end_date   DATE   NOT NULL
);
-- DROP TABLE credits;

-- students
CREATE TABLE IF NOT EXISTS students
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL CHECK (length(name) >= 3),
    age         INTEGER      NOT NULL CHECK ( age > 15 ),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    finished_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
--DROP TABLE students;

-- DROP ALL
/*
DROP TABLE nbu_rates;
DROP TABLE accounts;
DROP TABLE credits;
DROP TABLE users;
DROP TABLE students;
*/


