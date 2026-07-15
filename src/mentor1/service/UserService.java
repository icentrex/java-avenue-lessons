package mentor1.service;

import mentor1.model.User;
import mentor1.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(String name, String phone) {
        if (userRepository.existsByPhone(phone)) {
            System.out.println("Пользователь с таким телефоном уже существует!");
        }
        userRepository.save(new User(name, phone));
    }

    public void deleteByPhone(String phone) {
        if (!userRepository.existsByPhone(phone)) {
            System.out.println("Пользователь с таким телефоном не найден!");
        }
    }

    public User infoByPhone(String phone) {
        if (!userRepository.existsByPhone(phone)) {
            System.out.println("Пользователь с таким телефоном не найден!");
            return null;
        }
        return userRepository.findByPhone(phone);
    }

    public void assignEquipment() {

    }

    public void detachEquipment() {

    }

    public void showAll() {
        userRepository.showAllUsers();
    }
}
