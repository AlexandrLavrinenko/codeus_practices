# Tuesday Warmup

How well are you using the Stream API?

Go
to: [Git_Warmup](https://github.com/AlexandrLavrinenko/codeus_practices/tree/master/src/main/java/december/tuesday/stream_interview)
pull the project.<br>
Implement [StreamsWarmup.java](StreamsWarmup.java) according to comments.

Check your implementation
with [StreamsWarmupTest.java](../../../../../test/java/december/tuesday/warmup/StreamsWarmupTest.java).

***Words of encouragement:*** <br>
It's natural if you won't be able to handle all the tasks given the HARD level and maybe MEDIUM level.
But the purpose of this task is to identify weak points and make them more perfect.

**Time - 30 min.**

## Important to know:

Classes:
<br>[User.java](../helper_classes/User.java):
<br>`public record User(int id, String name, int age) implements Comparable<User>{}`
<br>
<br>[Currency.java](../helper_classes/Currency.java):
<br>`public enum Currency { USD, EUR, UAH }`
<br>
<br>[CurrentNbuRate.java](../helper_classes/CurrentNbuRate.java):
<br>```public record CurrentNbuRate(Map<Currency, Double> currencyRate) {}```
<br>
<br>[Account.java](../helper_classes/Account.java):
<br>`public record Account(int id, int userId, Money money, LocalDate openingDate, LocalDate closingDate) {}`
<br>
<br>[Money.java](../helper_classes/Money.java):

```
public record Money(Currency currency, int amount) {

private static final double PENNIES = 100.0;

    // centos to dollars, pennies to pounds, capitals to hryvnia
    public double getBigAmount() {
        return amount / PENNIES;
    }

    // convert to UAH equivalent
    public double getUahEquivalent(CurrentNbuRate rate) {
        return rate.currencyRate().get(currency) * getBigAmount();
    }
}
```