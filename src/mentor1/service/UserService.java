package mentor1.service;

import mentor1.menu.MainMenu;
import mentor1.model.Equipment;
import mentor1.model.User;
import mentor1.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> createUser(String name, String phone) {
        if (name == null || name.isBlank() || phone == null || phone.isBlank()) {
            return Optional.empty();
        }

        if (userRepository.isPhoneExist(phone)) {
            return Optional.empty();
        }

        User user = new User(name, phone);
        return Optional.of(userRepository.add(user));
    }

    public boolean deleteUserById(int userId) {
        List<Equipment> userEquipments = MainMenu.getInstance().getEquipmentService().getUserEquipments(userId);

        if (userEquipments.isEmpty()) {
            return userRepository.delete(userId);
        }

        return false;
    }

    public UpdateResult updateUser(User user) {
        if (user.getName() == null || user.getName().isBlank() ||
                user.getPhone() == null || user.getPhone().isBlank()) {
            return UpdateResult.INVALID_DATA;
        }
        if (userRepository.isPhoneExist(user.getId(), user.getPhone())) {
            return UpdateResult.DUPLICATE;
        }
        return userRepository.update(user) ? UpdateResult.SUCCESS : UpdateResult.NOT_FOUND;
    }

    public Optional<User> findUserById(int userId) {
        return userRepository.find(userId);
    }

    public List<User> getUsersList() {
        return userRepository.getUsersList();
    }
}
