-- WARMUP
-- NOTE: Money.amount are specified in pennies.
--       Big Amount = Amount / 100.0
-- Time: 30 min.

-- ------------------------------------------------------------------------------------------------
-- #0. EASY.
-- Insert into table 'students' your name (or nick) and your age.
-- Name length must be 3 or more letters and Age > 15
INSERT INTO students (name, age)
VALUES ('Potuzhniy', 46);

-- ------------------------------------------------------------------------------------------------
-- #1. EASY.
-- Calculate average age of users [double/numeric] (in any option)
-- Result: one field  -> "average_age"
SELECT avg(t1.age)                       as "average_age",
       sum(t1.age)                       as "sum_age",
       count(t1.age)                     as "cnt",
       1.0 * sum(t1.age) / count(t1.age) as "avg_age"
FROM users as t1;

-- ------------------------------------------------------------------------------------------------
-- #2. EASY-MEDIUM-HARD (depends on the solution path you choose)
-- Get total Big Amount UAH equivalent for ACCOUNTS of users.
-- Result: User ID and the total sum of Big Amount UAH equivalent sorted by user ID ("id", "uah_eq_acc").

-- Note: Use CURRENT_DATE for getting current date in Postgres DB.

-- Option 1 (EASY) - the simple query
SELECT u.id,
       sum((a.amount * n.rate) / 100.0) as "uah_eq_acc"
FROM users as u
         JOIN public.accounts a on u.id = a.user_id
         JOIN nbu_rates as n on n.ccy = a.currency
    and n.ccy_date = CURRENT_DATE
GROUP BY u.id
ORDER BY u.id
;

-- Option 2 (MEDIUM) - with subquery as constant
WITH pennies AS (SELECT 100.0 AS pennies)
SELECT u.id,
       sum((a.amount * n.rate) / pennies.pennies) as "uah_eq_acc"
FROM users as u
         JOIN public.accounts a on u.id = a.user_id
         JOIN nbu_rates as n on n.ccy = a.currency
    and n.ccy_date = CURRENT_DATE,
     pennies
GROUP BY u.id
ORDER BY u.id
;

-- Option 3 (HARD) - with temporary table (much better perform with big volumes of data)
-- a) Creating a temporary table with index(es)
DROP TABLE IF EXISTS temp_current_nbu_rates;
CREATE TEMP TABLE temp_current_nbu_rates
(
    ccy          CHAR(3) PRIMARY KEY NOT NULL,
    current_rate NUMERIC(6, 2)
);
-- b) Filling the temporary table the datas:
INSERT INTO temp_current_nbu_rates
SELECT n.ccy, n.rate
FROM nbu_rates as n
WHERE ccy_date = CURRENT_DATE;

-- c) Making the general query with the temporary table
SELECT u.id,
       sum((a.amount * t.current_rate) / 100.0) as "uah_eq_acc"
FROM users as u
         JOIN public.accounts a on u.id = a.user_id
         JOIN temp_current_nbu_rates as t on t.ccy = a.currency
GROUP BY u.id
ORDER BY u.id
;

-- ------------------------------------------------------------------------------------------------
-- #2a. EASY-MEDIUM-HARD (depends on the solution path you choose)
-- Get total Big Amount UAH equivalent for CREDITS of users and count of CREDITS by user.
-- Result: "user_id", "cnt_cred", "uah_eq_cred"

-- Note: Use CURRENT_DATE for getting current date in Postgres DB.
SELECT u.id                             as "user_id",
       count(c.id)                      as "cnt_cred",
       sum((c.amount * n.rate) / 100.0) as "uah_eq_cred"
FROM users as u
         JOIN credits as c on u.id = c.user_id
         JOIN nbu_rates as n on n.ccy = c.currency
    and n.ccy_date = CURRENT_DATE
GROUP BY u.id
ORDER BY u.id
;

-- ------------------------------------------------------------------------------------------------
-- #4. EASY.
-- Get all the users which have more than one of accounts.
-- Result: User ID and count of his accounts ("count_acc"), sorted by "count_acc" DESCending and User ID ASCending.
SELECT u.id, count(a.id) as "count_acc"
FROM accounts as a
         JOIN users u on u.id = a.user_id
GROUP BY u.id
ORDER BY count(a.id) DESC, u.id;

-- ------------------------------------------------------------------------------------------------
-- #5. EASY.
-- Select all UNIQUE users who have accounts other than UAH.
-- Result: user_id
SELECT a.user_id
FROM accounts as a
WHERE a.currency != 'UAH'
GROUP BY a.user_id
ORDER BY a.user_id;

-- ------------------------------------------------------------------------------------------------
-- #6. MEDIUM.
-- Select users by name (as "user_name"), account (as "acc_id")
-- and maximum Big Amount of money in the account (as "UAH_sum") (only UAH-accounts).
-- Sort by amount in descending order. Bring out the top-3 in the rating.
-- Result: "user_name", "acc_id", "UAH_sum"
SELECT u.name           as "user_name",
       a.id             as "acc_id",
       a.amount / 100.0 as "UAH_sum"
FROM users as u
         JOIN accounts a on u.id = a.user_id
WHERE a.currency = 'UAH'
ORDER BY a.amount / 100.0 DESC
LIMIT 3;

-- ------------------------------------------------------------------------------------------------
-- #7. MEDIUM.
-- How did the rate change compared to the previous day (except UAH rate)?
-- Result: currency, "curr_date", "curr_rate", "prev_date", "prev_rate", "diff"
SELECT current.ccy,
       current.ccy_date             as "curr_date",
       current.rate                 as "curr_rate",
       previous.ccy_date            as "prev_date",
       previous.rate                as "prev_rate",
       current.rate - previous.rate as "diff"
FROM nbu_rates as current
         JOIN nbu_rates as previous on previous.ccy = current.ccy
    and previous.ccy_date = (CURRENT_DATE - 1)
WHERE current.ccy_date = CURRENT_DATE
  AND current.ccy != 'UAH'
ORDER BY current.ccy
;

-- ------------------------------------------------------------------------------------------------
-- #8. MEDIUM.
-- Get count accounts and credits for ALL users sorted by user ID.
-- Result: "user_id", "cnt_acc", "cnt_cred"

-- Note: Not at all the users have accounts and/or credits, in this case output NULL as "uah_eq"
SELECT u.id        as "user_id",
       count(a.id) as "cnt_acc",
       count(c.id) as "cnt_cred"
FROM users as u
         LEFT JOIN accounts as a on u.id = a.user_id
         LEFT JOIN credits as c on c.user_id = u.id
GROUP BY u.id
ORDER BY u.id
;

-- ------------------------------------------------------------------------------------------------
-- #9. MEDIUM.
-- Select all users who do not have any accounts.
-- Result: "user_id", "acc_id"
-- Note: In this case account ID must be NULL.
SELECT u.id as "user_id",
       a.id as "acc_id"
FROM users as u
         LEFT JOIN accounts as a on u.id = a.user_id
WHERE a.user_id is NULL
;

-- ------------------------------------------------------------------------------------------------
-- #10. EASY (BONUS-LEVEL)
-- Calculate the count of students and their average age.
-- Result: "cnt", "avg_age"
-- ------------------------------------------------------------------------------------------------
SELECT count(id) as "cnt",
       avg(age)  as "avg_age"
FROM students;