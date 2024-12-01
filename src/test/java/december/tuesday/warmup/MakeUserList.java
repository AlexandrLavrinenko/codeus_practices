package december.tuesday.warmup;

import december.tuesday.helper_classes.User;

import java.util.ArrayList;
import java.util.List;

public class MakeUserList {
    List<User> users;

    public MakeUserList() {
        users = new ArrayList<User>();
        setUsers();
    }

    public List<User> getUsers() {
        return users;
    }

    private void setUsers() {
        var user1 = new User(1, "Maxim", 42);
        var user2 = new User(2, "Pavlo", 38);
        var user3 = new User(3, "Liza", 27);
        var user4 = new User(4, "Dmytro", 36);
        var user5 = new User(5, "Yevhenii", 41);
        var user6 = new User(6, "Oleksii", 43);
        var user7 = new User(7, "Andrii", 38);
        var user8 = new User(8, "Serhii", 42);
        var user9 = new User(9, "Taras", 36);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
    }
}
