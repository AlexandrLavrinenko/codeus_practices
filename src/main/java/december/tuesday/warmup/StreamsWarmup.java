package december.tuesday.warmup;

import december.tuesday.helper_classes.Account;
import december.tuesday.helper_classes.Currency;
import december.tuesday.helper_classes.CurrentNbuRate;
import december.tuesday.helper_classes.Money;
import december.tuesday.helper_classes.User;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.summingDouble;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * Exercises for improving Stream API knowledge.
 * <br> Warning!
 * <br> List of accounts {@link Account} and list of user {@link User} are not empty.
 */
public class StreamsWarmup {

    /**
     * Get int max raw amount {@link Money#amount()} of account's.
     * <br> Difficulty: EASY.
     *
     * @param accounts non-empty list of accounts.
     * @return max raw amount.
     */
    public int getMaxRawAmount(List<Account> accounts) {
        return accounts.stream()
                .map(acc -> acc.money().amount())
                .mapToInt(Integer::intValue)
                .max()
                .getAsInt();
    }

    /**
     * Get count of users with several types ({@link Currency}) of an account.
     * <br> Difficulty: EASY.
     *
     * @param accounts non-empty list of accounts.
     * @return Map with User's ID as key and List of Account's ID as value.
     */
    public Map<Integer, List<Integer>> getListOfIdAccountsByUserId(List<Account> accounts) {
        return accounts.stream()
                .collect(groupingBy(
                        Account::userId,
                        mapping(Account::id, toList())
                ));
    }

    /**
     * Get the Total Sum UAH Equivalent For All Accounts.
     * <br> Difficulty: EASY.
     *
     * @param accounts non-empty list of {@link Account}.
     * @param rate     non-empty current rate by every {@link Currency}.
     * @return double total UAH equivalent.
     */
    public double getTotalUahEquivalentForAllAccounts(List<Account> accounts, CurrentNbuRate rate) {
        return accounts.stream()
                .map(account -> account.money().getUahEquivalent(rate))
                .reduce(0.0, Double::sum);
    }


    /**
     * Get the oldest user from a list of Users.
     * <br> Difficulty: EASY.
     *
     * @param users non-empty list of {@link User}.
     * @return oldest user.
     */
    public User getOldestUser(List<User> users) {
        return users.stream()
                .reduce((user1, user2) ->
                        user1.age() > user2.age() ? user1 : user2)
                .get();
    }

    /**
     * Get already closed account IDs.
     * <br> Difficulty: EASY.
     * <br> Note: use LocalDate.now().
     *
     * @param accounts non-empty list of {@link Account}.
     * @return list of account IDs.
     */
    public List<Integer> getClosedAccountIds(List<Account> accounts) {
        return accounts.stream()
                .filter(account -> account.closingDate().isBefore(LocalDate.now()))
                .map(Account::id)
                .toList();
    }

    /**
     * Get user IDs whose account will be closed this month.
     * <br> Difficulty: EASY.
     * <br> Note: use LocalDate.now().
     *
     * @param accounts non-empty list of {@link Account}.
     * @return Map with user ID as key and list of account IDs as value.
     */
    public Map<Integer, List<Integer>> getUsersIdWithAccountIdWillBeClosedAccount(List<Account> accounts) {
        return accounts.stream()
                .filter(account ->
                        (account.closingDate().getMonth() == LocalDate.now().getMonth()) &&
                        account.closingDate().getYear() == LocalDate.now().getYear())
                .collect(groupingBy(
                        Account::userId,
                        mapping(Account::id, toList())
                ));
    }

    /**
     * Get Map<Currency, Double> with max Big Amount {@link Money#getBigAmount()} by currency.
     * <br> Difficulty: MEDIUM.
     *
     * @param accounts non-empty list of accounts.
     * @return Map with Currency as the key and max Double Big Amount as value for the Currency.
     */
    public Map<Currency, Double> getMaxBigAmountByCurrency(List<Account> accounts) {
        return accounts.stream()
                .collect(groupingBy(
                        acc -> acc.money().currency(),
                        collectingAndThen(
                                maxBy(Comparator.comparing(acc -> acc.money().getBigAmount())),
                                optAcc ->
                                        optAcc.map(acc -> acc.money().getBigAmount()).get()
                        )
                ));
    }

    /**
     * Get a User who has max Big Amount values.
     * <br> Difficulty: MEDIUM.
     *
     * @param accounts list of Account.
     * @param users    list of User.
     * @return User if exists, else null.
     */
    public User getMaxBigAmountUser(List<Account> accounts, List<User> users) {
        var mayBeUserId = accounts.stream()
                .max(Comparator.comparing(acc -> acc.money().getBigAmount(), Comparator.naturalOrder()))
                .map(Account::userId);

        return mayBeUserId.flatMap(userId ->
                        users.stream()
                                .filter(usr -> usr.id() == userId)
                                .findFirst()
                )
                .orElse(null);
    }

    /**
     * Get users whose account will be closed this month.
     * <br> Difficulty: MEDIUM.
     * <br> Note: use LocalDate.now().
     *
     * @param accounts non-empty list of {@link Account}.
     * @param users    non-empty list of {@link User}.
     * @return Map with user as a key and list of accountID as a value.
     */
    public Map<User, List<Integer>> getUsersWithAccIDWillBeClosedAccount(List<Account> accounts,
                                                                         List<User> users) {
        // Create a map that matches users by their ID
        Map<Integer, User> userMap = users.stream()
                .collect(Collectors.toMap(User::id, user -> user));

        return accounts.stream()
                .filter(account ->
                        (account.closingDate().getMonth() == LocalDate.now().getMonth()) &&
                        account.closingDate().getYear() == LocalDate.now().getYear())
                .collect(groupingBy(
                        account -> userMap.get(account.userId()),
                        mapping(Account::id, toList())
                ));
    }

    /**
     * Get count of users with several type ({@link Currency}) of account.
     * <br> Difficulty: HARD.
     *
     * @param accounts non-empty list of accounts.
     * @return count of users.
     */
    public int getCountOfUserWithSeveralTypeOfAccount(List<Account> accounts) {
        // grouping accounts by userId
        Map<Integer, Set<Currency>> userCurrencyMap = accounts.stream()
                .collect(groupingBy(
                        Account::userId,
                        mapping(account -> account.money().currency(),
                                toSet()
                        )
                ));

        // counting the number of users who have more than one type of account
        return (int) userCurrencyMap.values().stream()
                .filter(currencySet -> currencySet.size() > 1)
                .count();
    }

    /**
     * Get User with max sum total Big Amount in UAH Equivalent.
     * <br> Difficulty: HARD.
     *
     * @param accounts non-empty list of {@link Account}.
     * @param users    non-empty list of {@link User}.
     * @param rate     non-empty current rate by every {@link Currency}.
     * @return Map with User as key and total sum Big Amount in UAH Equivalent as value.
     */
    public Map<User, Double> getMaxBigAmountInUahEquivalent(List<Account> accounts,
                                                            List<User> users,
                                                            CurrentNbuRate rate) {
        var userToTotalUah = accounts.stream()
                .collect(groupingBy(
                        Account::userId,
                        summingDouble(account -> account.money().getUahEquivalent(rate))
                ));

        var maxEntry = userToTotalUah.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        return maxEntry.map(entry -> users.stream()
                        .filter(user -> user.id() == entry.getKey())
                        .findFirst()
                        .map(user -> Map.of(user, entry.getValue()))
                        .orElse(Map.of()))
                .orElse(Map.of());
    }

}
