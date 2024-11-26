package december.tuesday.helper_classes;

public record Money(Currency currency, int amount) {
    private static final double PENNIES = 100.0;

    // centos to dollars, pennies to pounds, capitals to hryvnia
    public double getBigAmount() {
        return amount / PENNIES;
    }
}