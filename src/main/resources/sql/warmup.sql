-- WARMUP
-- NOTE: Money.amount are specified in pennies.
--       Big Amount = Amount / 100.0
-- Time: 30 min.

-- ------------------------------------------------------------------------------------------------
-- #0. EASY.
-- Insert into table 'students' your name (or nick) and your age.
-- Name length must be 3 or more letters and Age > 15
INSERT INTO students (name, age)
VALUES ('YourName', 0);

-- ------------------------------------------------------------------------------------------------
-- #1. EASY.
-- Calculate average age of users [double/numeric] (in any option)
-- Result: one field  -> "average_age"
SELECT *
-- TODO: Write your solution here
FROM users as t1;

-- ------------------------------------------------------------------------------------------------
-- #2. EASY-MEDIUM-HARD (depends on the solution path you choose)
-- Get total Big Amount UAH equivalent for ACCOUNTS of users.
-- Result: User ID and the total sum of Big Amount UAH equivalent sorted by user ID ("id", "uah_eq_acc").

-- Note: Use CURRENT_DATE for getting current date in Postgres DB.

SELECT *
-- TODO: Write your solution here
FROM users as t1;

-- ------------------------------------------------------------------------------------------------
-- #2a. EASY-MEDIUM-HARD (depends on the solution path you choose)
-- Get total Big Amount UAH equivalent for CREDITS of users and count of CREDITS by user.
-- Result: "user_id", "cnt_cred", "uah_eq_cred"

-- Note: Use CURRENT_DATE for getting current date in Postgres DB.
SELECT *
-- TODO: Write your solution here
FROM users as t1;

-- ------------------------------------------------------------------------------------------------
-- #4. EASY.
-- Get all the users which have more than one of accounts.
-- Result: User ID and count of his accounts ("count_acc"), sorted by "count_acc" DESCending and User ID ASCending.
SELECT *
-- TODO: Write your solution here
FROM users as t1;

-- ------------------------------------------------------------------------------------------------
-- #5. EASY.
-- Select all UNIQUE users who have accounts other than UAH.
-- Result: user_id
SELECT *
-- TODO: Write your solution here
FROM users as t1;

-- ------------------------------------------------------------------------------------------------
-- #6. MEDIUM.
-- Select users by name (as "user_name"), account (as "acc_id")
-- and maximum Big Amount of money in the account (as "UAH_sum") (only UAH-accounts).
-- Sort by amount in descending order. Bring out the top-3 in the rating.
-- Result: "user_name", "acc_id", "UAH_sum"
SELECT *
-- TODO: Write your solution here
FROM users as t1;

-- ------------------------------------------------------------------------------------------------
-- #7. MEDIUM.
-- How did the rate change compared to the previous day (except UAH rate)?
-- Result: currency, "curr_date", "curr_rate", "prev_date", "prev_rate", "diff"
SELECT *
-- TODO: Write your solution here
FROM users as t1;

-- ------------------------------------------------------------------------------------------------
-- #8. MEDIUM.
-- Get count accounts and credits for ALL users sorted by user ID.
-- Result: "user_id", "cnt_acc", "cnt_cred"

-- Note: Not at all the users have accounts and/or credits, in this case output NULL as "uah_eq"
SELECT *
-- TODO: Write your solution here
FROM users as t1;

-- ------------------------------------------------------------------------------------------------
-- #9. MEDIUM.
-- Select all users who do not have any accounts.
-- Result: "user_id", "acc_id"
-- Note: In this case account ID must be NULL.
SELECT *
-- TODO: Write your solution here
FROM users as t1;

-- ------------------------------------------------------------------------------------------------
-- #10. EASY (BONUS-LEVEL)
-- Calculate the count of students and their average age.
-- Result: "cnt", "avg_age"
-- ------------------------------------------------------------------------------------------------
SELECT *
-- TODO: Write your solution here
FROM students;