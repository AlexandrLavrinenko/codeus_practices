package december.tuesday.warmup;

import december.tuesday.helper_classes.Account;
import december.tuesday.helper_classes.Currency;
import december.tuesday.helper_classes.Money;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MakeAccountList {
    List<Account> accounts;

    public MakeAccountList() {
        accounts = new ArrayList<>();
        setAccounts();
    }

    private void setAccounts() {
        var account1 = new Account(101, 1,
                new Money(Currency.UAH, 64000),
                LocalDate.of(2023, 11, 26),
                LocalDate.of(2025, 12, 26));
        var account2 = new Account(102, 2,
                new Money(Currency.UAH, 458600),
                LocalDate.of(2021, 6, 12),
                LocalDate.of(2024, 6, 12));
        var account3 = new Account(103, 3,
                new Money(Currency.UAH, 856400),
                LocalDate.of(2022, 8, 25),
                LocalDate.of(2024, 8, 25)
        );
        var account4 = new Account(104, 4,
                new Money(Currency.UAH, 4569500),
                LocalDate.of(2024, 10, 22),
                LocalDate.of(2025, 10, 22)
        );
        var account5 = new Account(105, 5,
                new Money(Currency.UAH, 2350000),
                LocalDate.of(2024, 10, 20),
                LocalDate.of(2025, 10, 20)
        );
        var account6 = new Account(106, 6,
                new Money(Currency.UAH, 2536000),
                LocalDate.of(2022, 6, 19),
                LocalDate.of(2024, 12, 19)
        );
        var account7 = new Account(107, 7,
                new Money(Currency.UAH, 256000),
                LocalDate.of(2024, 7, 12),
                LocalDate.of(2026, 7, 12)
        );
        var account8 = new Account(108, 8,
                new Money(Currency.UAH, 2310000),
                LocalDate.of(2022, 4, 15),
                LocalDate.of(2024, 4, 15)
        );
        var account9 = new Account(109, 9,
                new Money(Currency.UAH, 8690000),
                LocalDate.of(2024, 6, 11),
                LocalDate.of(2025, 6, 11)
        );
        var account10 = new Account(110, 1,
                new Money(Currency.USD, 50500),
                LocalDate.of(2024, 12, 12),
                LocalDate.of(2025, 12, 12)
        );
        var account11 = new Account(111, 5,
                new Money(Currency.USD, 23500),
                LocalDate.of(2020, 7, 23),
                LocalDate.of(2024, 12, 23)
        );
        var account12 = new Account(112, 9,
                new Money(Currency.USD, 70000),
                LocalDate.of(2024, 1, 13),
                LocalDate.of(2026, 1, 13)
        );
        var account13 = new Account(113, 9,
                new Money(Currency.EUR, 30000),
                LocalDate.of(2024, 2, 15),
                LocalDate.of(2026, 2, 15)
        );

        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);
        accounts.add(account4);
        accounts.add(account5);
        accounts.add(account6);
        accounts.add(account7);
        accounts.add(account8);
        accounts.add(account9);
        accounts.add(account10);
        accounts.add(account11);
        accounts.add(account12);
        accounts.add(account13);
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
