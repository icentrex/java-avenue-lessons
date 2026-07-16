package mentor1.service;

import mentor1.model.User;
import mentor1.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(String name, String phone) {
        userRepository.save(new User(name, phone));
    }

    public void deleteById(int id) {
        if (!userRepository.existsById(id)) {
            System.out.println("Пользователь с таким телефоном не найден!");
            return;
        }
        userRepository.deleteById(id);
    }

    public User findById(int id) {
        return userRepository.findById(id);
    }

    public void assignEquipment() {

    }

    public void detachEquipment() {

    }

    public void showAll() {
        userRepository.showAllUsers();
    }
}
