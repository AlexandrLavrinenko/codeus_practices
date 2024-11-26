package december.tuesday.warmup;

import december.tuesday.helper_classes.Account;
import december.tuesday.helper_classes.User;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

class StreamsWarmupTest {
    private static List<User> users;
    private static List<Account> accounts;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        users = new MakeUserList().getUsers();
        accounts = new MakeAccountList().getAccounts();
    }

    // Вибрати юзера з найбільшою сумою на рахунку
}