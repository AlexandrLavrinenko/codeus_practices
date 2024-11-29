package december.tuesday.warmup;

import december.tuesday.helper_classes.Account;
import december.tuesday.helper_classes.Currency;
import december.tuesday.helper_classes.CurrentNbuRate;
import december.tuesday.helper_classes.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StreamsWarmupTest {
    private static List<User> users;
    private static List<Account> accounts;
    private static StreamsWarmup streamsWarmup;
    private static CurrentNbuRate currentNbuRate;

    @BeforeAll
    static void setUpBeforeClass() {
        streamsWarmup = new StreamsWarmup();

        users = new MakeUserList().getUsers();
        accounts = new MakeAccountList().getAccounts();

        var currencyMap = new HashMap<Currency, Double>();
        currencyMap.put(Currency.UAH, 1.0);
        currencyMap.put(Currency.USD, 41.85);
        currencyMap.put(Currency.EUR, 44.35);
        currentNbuRate = new CurrentNbuRate(currencyMap);
    }

    @Test
    @DisplayName("Max raw amount of account's")
    void checkMaxRawAmount() {
        var actual = streamsWarmup.getMaxRawAmount(accounts);
        var expected = 8690000;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Max Big Amount of account's by currency")
    void checkMaxBigAmountByCurrency() {
        var actual = streamsWarmup.getMaxBigAmountByCurrency(accounts);
        var expected = new HashMap<Currency, Double>();
        expected.put(Currency.UAH, 86900.0);
        expected.put(Currency.USD, 700.0);
        expected.put(Currency.EUR, 300.0);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Counting of users with several type of account")
    void checkCountOfUsersWithSeveralTypeOfAccount() {
        var actual = streamsWarmup.getCountOfUserWithSeveralTypeOfAccount(accounts);
        var expected = 3;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("List Of IdAccounts By UserId")
    void checkListOfIdAccountsByUserId() {
        var actual = streamsWarmup.getListOfIdAccountsByUserId(accounts);
        var expected = new HashMap<Integer, List<Integer>>();
        expected.put(1, List.of(101, 110));
        expected.put(2, List.of(102));
        expected.put(3, List.of(103));
        expected.put(4, List.of(104));
        expected.put(5, List.of(105, 111));
        expected.put(6, List.of(106));
        expected.put(7, List.of(107));
        expected.put(8, List.of(108));
        expected.put(9, List.of(109, 112, 113));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check the User with the largest Big Amount on the account")
    void maxAmountUser() {
        var expectedUserId = 9;

        var actual = streamsWarmup.getMaxBigAmountUser(accounts, users);
        var expected = users.stream()
                .filter(user -> user.id() == expectedUserId)
                .findFirst()
                .get();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check max Big Amount in UAH equivalent")
    void checkMaxBigAmountInUahEquivalent() {
        var expectedUserId = 9;
        var expectedUser = users.stream().filter(u -> u.id() == expectedUserId).findFirst().get();
        var expectedUahEquivalent = 86900 + (700 * 41.85) + (300 * 44.35);

        var actual = streamsWarmup.getMaxBigAmountInUahEquivalent(accounts, users, currentNbuRate);
        var expected = Map.of(expectedUser, expectedUahEquivalent);

        assertEquals(expected, actual);

    }

}