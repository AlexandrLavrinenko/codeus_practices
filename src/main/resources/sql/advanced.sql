-- ADVANCED
-- NOTE: Money.amount are specified in pennies.
--       Big Amount = Amount / 100.0

-- ------------------------------------------------------------------------------------------------
-- #11. EASY.
-- Update field 'finished_at' in the table 'students' for your ID or your name (nick).
-- WARNING! Do not use UPDATE without conditions (WHERE)!!!

-- Check your name:
SELECT *
FROM students
ORDER BY id;

UPDATE students
SET finished_at = NOW()
WHERE name = 'YourName'
-- id = 1
;

-- ------------------------------------------------------------------------------------------------
-- #12. MEDIUM
-- Calculate how long the warm-up was for each student, and display the TOP-3 most agile students.
-- Warning:
-- If "finished_at" is equal to "created_at", it means that the student is still in the process of completing the task,
-- it does not need to be taken into account.
-- Result: "id", "name", "minutes"

-- Notes:
-- 1) To get the difference between two timestamps, you need to use the formula:
--          [EXTRACT(EPOCH FROM (end_time - start_time)) / 60 AS "minutes"]
-- 2) Round the obtained time result in "minutes" to tenths. [15.788888 -> 15.8]
--              [ROUND(duration, 1) AS rounded_duration]

SELECT id,
       name,
       ROUND(EXTRACT(EPOCH FROM (finished_at - created_at)) / 60, 1) as "minutes"
FROM students
WHERE ROUND(EXTRACT(EPOCH FROM (finished_at - created_at)) / 60, 1) > 0
ORDER BY ROUND(EXTRACT(EPOCH FROM (finished_at - created_at)) / 60, 1)
LIMIT 3;


-- ------------------------------------------------------------------------------------------------
-- #13. MEDIUM
-- Count the number of students in the table 'students' ("cnt"),
-- the amount of time spent from the start of the warm-up (created_at) to its end (finished_at)
-- for the entire group of students in the table ("minutes").
-- Also, additionally calculate the average warm-up time if all students worked in multithreaded mode:
--                 ["avg_minutes" = "minutes" / "cnt"]
-- Note: Round the obtained time result in "minutes" to tenths. [15.788888 -> 15.8]
-- Use the formula: ROUND(duration, 1) AS rounded_duration

SELECT count(id)                                                                           as "cnt",
       ROUND(EXTRACT(EPOCH FROM (max(finished_at) - min(created_at))) / 60, 1)             as "minutes",
       ROUND(EXTRACT(EPOCH FROM (max(finished_at) - min(created_at))) / 60, 1) / count(id) as "avg_minutes"
FROM students;

-- ------------------------------------------------------------------------------------------------
-- #14. MEDIUM.
-- What is the largest Big Amount in UAH equivalent on a non-overdue account ("max_curr_uah_eq")?
-- Result: "user_id", "acc_id", "max_curr_uah_eq"

-- Note: See the "end_date" field and compare with the current date.

SELECT a.user_id,
       a.id                        as "acc_id",
       (a.amount * n.rate) / 100.0 as "max_curr_uah_eq"
FROM accounts as a
         JOIN nbu_rates as n on n.ccy = a.currency AND n.ccy_date = CURRENT_DATE
WHERE a.end_date >= CURRENT_DATE
ORDER BY a.amount DESC
LIMIT 1;

-- ------------------------------------------------------------------------------------------------
-- #15. HARD.
-- Calculate the number of accounts ("cnt_acc") and credits ("cnt_cred") for EACH user
-- and the total Big Amount on accounts ("sum_uah_ba_acc") and credits ("sum_uah_ba_cred") in UAH equivalent.
-- Also, you need to calculate the difference ("diff") between "sum_uah_ba_acc" and "sum_uah_ba_cred".
-- Warning: The amount values of credits are negative. Thus, the difference ("diff") should be calculated as:
--                            ["diff" = "sum_uah_ba_acc" - "sum_uah_ba_cred"]
-- Result: "user_id", "name", "cnt_acc", "sum_uah_ba_acc", "cnt_cred", "sum_uah_ba_cred", "diff"

-- Note: use table 'nbu_rates' for rates for different types of currencies (rate for current day).


-- a) Creating a temporary table with index(es)
DROP TABLE IF EXISTS current_nbu_rates;
CREATE TEMP TABLE current_nbu_rates
(
    ccy          CHAR(3) PRIMARY KEY NOT NULL,
    current_rate NUMERIC(6, 2)
);

-- b) Filling the temporary table the datas:
INSERT INTO current_nbu_rates
SELECT n.ccy, n.rate
FROM nbu_rates as n
WHERE ccy_date = CURRENT_DATE;

-- c) Making the temporary table for accounts:
CREATE TEMP TABLE temp_accounts AS
SELECT u.id                                   as "user_id",
       u.name,
       count(a.id)                            as "cnt_acc",
       sum(a.amount * n.current_rate / 100.0) as "sum_uah_ba_acc"
FROM users as u
         LEFT JOIN accounts as a on a.user_id = u.id
         LEFT JOIN current_nbu_rates as n on n.ccy = a.currency
GROUP BY u.id, u.name
ORDER BY u.id
;

-- d) Making the temporary table for credits:
CREATE TEMP TABLE temp_credits AS
SELECT u.id                                   as "user_id",
       u.name,
       count(c.id)                            as "cnt_cred",
       sum(c.amount * n.current_rate / 100.0) as "sum_uah_ba_cred"
FROM users as u
         LEFT JOIN credits c on c.user_id = u.id
         LEFT JOIN current_nbu_rates as n on n.ccy = c.currency
GROUP BY u.id, u.name
ORDER BY u.id
;

-- e) Making the general query with the temporary table
SELECT u.id                                   as "user_id",
       u.name,
       ta.cnt_acc,
       ta.sum_uah_ba_acc,
       tc.cnt_cred,
       tc.sum_uah_ba_cred,
       ta.sum_uah_ba_acc + tc.sum_uah_ba_cred as "diff"
FROM users as u
         LEFT JOIN temp_accounts as ta on ta.user_id = u.id
         LEFT JOIN temp_credits as tc on tc.user_id = u.id
ORDER BY u.id;


-- ------------------------------------------------------------------------------------------------
-- #16. HARD (BONUS-LEVEL)
-- Based on task #15, do the same, but for non-overdue accounts and credits.
-- Calculate "diff" and identify customers whose total amount on accounts does not exceed
-- (i.e. is less than) the total Big Amount of their credits.
-- Result: "user_id", "name", "cnt_acc", "sum_uah_ba_acc", "cnt_cred", "sum_uah_ba_cred", "diff"

-- a) Creating a temporary table with index(es)
DROP TABLE IF EXISTS current_nbu_rates;
CREATE TEMP TABLE current_nbu_rates
(
    ccy          CHAR(3) PRIMARY KEY NOT NULL,
    current_rate NUMERIC(6, 2)
);

-- b) Filling the temporary table the datas:
INSERT INTO current_nbu_rates
SELECT n.ccy, n.rate
FROM nbu_rates as n
WHERE ccy_date = CURRENT_DATE;

-- c) Making the temporary table for accounts:
DROP TABLE IF EXISTS t_accounts;
CREATE TEMP TABLE t_accounts AS
SELECT u.id                                   as "user_id",
       u.name,
       count(a.id)                            as "cnt_acc",
       sum(a.amount * n.current_rate / 100.0) as "sum_uah_ba_acc"
FROM users as u
         LEFT JOIN accounts as a on a.user_id = u.id AND a.end_date >= CURRENT_DATE
         LEFT JOIN current_nbu_rates as n on n.ccy = a.currency
GROUP BY u.id, u.name
ORDER BY u.id
;

-- d) Making the temporary table for credits:
DROP TABLE IF EXISTS t_credits;
CREATE TEMP TABLE t_credits AS
SELECT u.id                                   as "user_id",
       u.name,
       count(c.id)                            as "cnt_cred",
       sum(c.amount * n.current_rate / 100.0) as "sum_uah_ba_cred"
FROM users as u
         LEFT JOIN credits c on c.user_id = u.id AND c.end_date >= CURRENT_DATE
         LEFT JOIN current_nbu_rates as n on n.ccy = c.currency
GROUP BY u.id, u.name
ORDER BY u.id
;

-- e) Making the general query in the temp result table
DROP TABLE IF EXISTS t_result;
CREATE TEMP TABLE t_result AS
SELECT u.id    as "user_id",
       u.name,
       ta.cnt_acc,
       CASE
           WHEN ta.sum_uah_ba_acc IS NULL
               THEN 0.0
           ELSE ta.sum_uah_ba_acc
           END as "sum_uah_ba_acc",
       tc.cnt_cred,
       CASE
           WHEN tc.sum_uah_ba_cred IS NULL
               THEN 0.0
           ELSE tc.sum_uah_ba_cred
           END as "sum_uah_ba_cred",
       CASE
           WHEN ta.sum_uah_ba_acc IS NULL THEN tc.sum_uah_ba_cred
           WHEN tc.sum_uah_ba_cred IS NULL THEN ta.sum_uah_ba_acc
           ELSE ta.sum_uah_ba_acc + tc.sum_uah_ba_cred
           END as "diff"
FROM users as u
         LEFT JOIN t_accounts as ta on ta.user_id = u.id
         LEFT JOIN t_credits as tc on tc.user_id = u.id
ORDER BY u.id;

-- f) And finally, checking the condition that diff < 0 in result temp table.
SELECT *
FROM t_result
WHERE diff < 0;