-- ADVANCED
-- NOTE: Money.amount are specified in pennies.
--       Big Amount = Amount / 100.0

-- Загальна сума на рахунках та кредитах в UAH-еквіваленті по курсу на поточну дату.

-- ------------------------------------------------------------------------------------------------
-- #11. EASY.
-- Update field 'finished_at' in the table 'students' for your ID or your name (nick).
-- WARNING! Do not use UPDATE without conditions (WHERE)!!!

SELECT *
FROM students
ORDER BY id;

UPDATE students
SET finished_at = NOW()
WHERE name = 'YourName2'
-- id = 1
;

-- ------------------------------------------------------------------------------------------------
-- #12. HARD.
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
-- BONUS-LEVEL
-- #10. EASY
-- Calculate the count of students and their average age.

-- MEDIUM: Додатково підрахуйте, скільки тривала уся розминка для усіх студентів, що внесли свох дані у таблицю.
-- Notes:
-- 1) To get the difference between two timestamps, you need to use the formula:
--          [EXTRACT(EPOCH FROM (end_time - start_time)) / 60 AS "minutes"]
--          [EXTRACT(EPOCH FROM (end_time - start_time)) % 60 AS "seconds"]

-- 2) Round the obtained time result in minutes to tenths. [15.788888 -> 15.8]
-- ROUND(duration, 1) AS rounded_duration

-- Result: "cnt", "avg_age"
--      MEDIUM - "cnt", "avg_age", ""
-- ------------------------------------------------------------------------------------------------
SELECT count(id)                                                              as "cnt",
       avg(age)                                                               as "avg_age",
       ROUND(EXTRACT(EPOCH FROM (max(created_at) - min(created_at))) / 60, 1) as "minutes"
FROM students;