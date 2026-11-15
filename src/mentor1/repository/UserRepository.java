package mentor1.repository;

import mentor1.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User add(User user);
    boolean delete(int userId);
    boolean update(User user);
    Optional<User> find(int userId);

    boolean isPhoneExist(String phone);
    boolean isPhoneExist(int excludedUserId, String phone);

    List<User> getUsersList();
}
