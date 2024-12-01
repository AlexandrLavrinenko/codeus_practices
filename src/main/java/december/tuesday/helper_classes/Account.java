package december.tuesday.helper_classes;

import java.time.LocalDate;

public record Account(int id, int userId, Money money, LocalDate openingDate, LocalDate closingDate) {
}
