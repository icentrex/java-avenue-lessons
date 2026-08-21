package mentor1.service;

import mentor1.TechnicalException;
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
        if (userRepository.isPhoneExist(phone)) {
            return Optional.empty();
        }

        User user = new User(name, phone);
        try {
            User createdUser = userRepository.addUser(user);
            if (createdUser == null) {
                throw new TechnicalException("Репозиторий вернул ошибку при сохранении пользователя", null);
            }
            return Optional.of(createdUser);
        } catch (TechnicalException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new TechnicalException("Техническая ошибка при создании пользователя", e);
        }
    }

    public boolean deleteUserById(int userId) {
        List<Equipment> userEquipments = MainMenu.getInstance().getEquipmentService().getUserEquipments(userId);

        if (userEquipments.isEmpty()) {
            return userRepository.deleteUserById(userId);
        }
        return false;
    }

    public boolean updateUserName(int userId, String name) {
        return userRepository.updateUserName(userId, name);
    }

    public boolean updateUserPhone(int userId, String phone) {
        if (userRepository.isPhoneExist(userId, phone)) {
            return false;
        }
        return userRepository.updateUserPhone(userId, phone);
    }

    public Optional<User> findUserById(int userId) {
        return userRepository.findUserById(userId);
    }

    public List<User> getUsersList() {
        return userRepository.getUsersList();
    }
}
