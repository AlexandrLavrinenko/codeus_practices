# A. Thursday SQL Warmup (***Time - 30 min.***)

### How well are you know SQL?

Go
to: [Git_ThursdayWarmup](https://github.com/AlexandrLavrinenko/codeus_practices/blob/master/src/main/java/december/thursday/README.md)
pull the project.<br>

## 1. Connect to a database:

Use the recorded URL to connect to your Postgres database from any external source.

- ##### URL: `jdbc:postgresql://6.tcp.eu.ngrok.io:19751/morning_codeus`
- ##### USER: `codeus`
- ##### PASSWORD: `theOneWhoGetsUpEarly`
- ##### scheme: `public`
- ##### tables: `users, accounts, nbu_rates, credits, nbu_rates, currencies`

## 2. Open file [warmup.sql](../../../resources/sql/warmup.sql) and follow to the comments.

## 3. Check your implementation with branch `completed`

<br>
<br>

# B. Thursday SQL Advanced (***Time - 30 min.***)

## 4. Open file [advanced.sql](../../../resources/sql/advanced.sql) and follow to the comments.

## 5. Check your implementation with branch `completed`

# C. Additional materials

Database diagram: <br>
![DataBase Diagram Image](../../../resources/images/DB_Diagram.png)
<br>Presentation: <br>
[Основні команди SQL-запитів .pdf](../../../resources/images/%D0%9E%D1%81%D0%BD%D0%BE%D0%B2%D0%BD%D1%96%20%D0%BA%D0%BE%D0%BC%D0%B0%D0%BD%D0%B4%D0%B8%20SQL-%D0%B7%D0%B0%D0%BF%D0%B8%D1%82%D1%96%D0%B2%20.pdf)

# D. Homework practice
If you want to go through the training again, you can raise the base yourself using the command (Docker must be
installed): <br>
```docker run --rm  --name codeus-postgres  -e POSTGRES_PASSWORD=theOneWhoGetsUpEarly -e POSTGRES_USER=codeus -e POSTGRES_DB=morning_codeus -p 5433:5432 -d postgres:16.0```
<br> 1. Use the [init.sql](../../../resources/sql/util/init.sql)
<br> 2. And then use [fill_date.sql](../../../resources/sql/util/fill_date.sql)

If you want to expose your DB remotely, use the
link [How to Expose PostgreSQL Remotely Using ngrok](https://meroxa.com/blog/how-to-expose-postgresql-remotely-using-ngrok/).

