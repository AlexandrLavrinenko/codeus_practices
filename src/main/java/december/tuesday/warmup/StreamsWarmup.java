package december.tuesday.warmup;

import december.tuesday.helper_classes.Account;
import december.tuesday.helper_classes.Currency;
import december.tuesday.helper_classes.CurrentNbuRate;
import december.tuesday.helper_classes.Money;
import december.tuesday.helper_classes.User;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
     * @param accounts list of accounts.
     * @return max amount or else 0.
     */
    public int getMaxRawAmount(List<Account> accounts) {
        return accounts.stream()
                .map(acc -> acc.money().amount())
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);
    }

    /**
     * Get Map<Currency, Double> with max Big Amount {@link Money#getBigAmount()} by currency.
     * <br> Difficulty: HARD.
     *
     * @param accounts list of accounts.
     * @return Map with Currency as the key and max Double Big Amount as value for the Currency.
     */
    public Map<Currency, Double> getMaxBigAmountByCurrency(List<Account> accounts) {
        return accounts.stream()
                .collect(Collectors.groupingBy(
                        acc -> acc.money().currency(),
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(acc -> acc.money().getBigAmount())),
                                optAcc ->
                                        optAcc.map(acc -> acc.money().getBigAmount()).orElse(0.0)
                        )
                ));
    }

    /**
     * Get count of users with several type ({@link Currency}) of account.
     * <br> Difficulty: EASY.
     *
     * @param accounts list of accounts.
     * @return Map with User's ID as key and List of Account's ID as value.
     */
    public Map<Integer, List<Integer>> getListOfIdAccountsByUserId(List<Account> accounts) {
        return accounts.stream()
                .collect(Collectors.groupingBy(
                        Account::userId,
                        Collectors.mapping(Account::id, Collectors.toList())
                ));
    }

    /**
     * Get count of users with several type ({@link Currency}) of account.
     * <br> Difficulty: HARD.
     *
     * @param accounts list of accounts.
     * @return count of users.
     */
    public int getCountOfUserWithSeveralTypeOfAccount(List<Account> accounts) {
        // grouping accounts by userId
        Map<Integer, Set<Currency>> userCurrencyMap = accounts.stream()
                .collect(Collectors.groupingBy(
                        Account::userId,
                        Collectors.mapping(account -> account.money().currency(),
                                Collectors.toSet()
                        )
                ));

        // counting the number of users who have more than one type of account
        return (int) userCurrencyMap.values().stream()
                .filter(currencySet -> currencySet.size() > 1)
                .count();
    }

    /**
     * Get User who has max Big Amount values.
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
     * Get User with max sum total Big Amount in UAH Equivalent.
     * <br> Difficulty: HARD.
     *
     * @param accounts list of {@link Account}.
     * @param users    list of {@link User}.
     * @param rate     current rate by every {@link Currency}.
     * @return Map with User as key and total sum Big Amount in UAH Equivalent as value.
     */
    public static Map<User, Double> getMaxBigAmountInUahEquivalent(List<Account> accounts,
                                                                   List<User> users,
                                                                   CurrentNbuRate rate) {
        var userToTotalUah = accounts.stream()
                .collect(Collectors.groupingBy(
                        Account::userId,
                        Collectors.summingDouble(account -> account.money().getUahEquivalent(rate))
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
