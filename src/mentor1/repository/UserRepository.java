package mentor1.repository;

import mentor1.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final List<User> users = new ArrayList<>(100);

    public UserRepository() {
        users.add(new User("Сергей", "123-45-66"));
        users.add(new User("Иван", "999-45-66"));
        users.add(new User("Ирина", "888-45-66"));
    }

    public void save(User user) {
        users.add(user);
    }

    public boolean existsById(int id) {
        if (users.get(id) != null) {
            return true;
        }
        return false;
    }

    public void showAllUsers() {
        for (int i = 0; i < users.size(); i++) {
            System.out.printf("id = %d, name = %s, phone = %s", i, users.get(i).getName(), users.get(i).getPhoneNumber());
            System.out.println();
        }
    }

    public User findById(int id) {
        return users.get(id);
    }

    public void deleteById(int id) {
        users.remove(id);
    }
}
