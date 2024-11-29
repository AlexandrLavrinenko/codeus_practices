package december.tuesday.helper_classes;

import java.util.Objects;

public record User(int id, String name, int age) implements Comparable<User> {
    @Override
    public int compareTo(User other) {
        // Compare by id
        int result = Integer.compare(this.id, other.id);
        // If ids are equal, compare by name
        if (result == 0) {
            result = this.name.compareTo(other.name);
        }
        // If names are also equal, compare by age
        if (result == 0) {
            result = Integer.compare(this.age, other.age);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }
}
