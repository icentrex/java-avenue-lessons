package mentor1.service;

import mentor1.menu.EquipmentMenu;
import mentor1.model.Equipment;
import mentor1.model.User;
import mentor1.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(String name, String phone) {
        userRepository.create(new User(name, phone));
        System.out.println("Пользователь создан!");
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
        System.out.println("Пользователь удален!");
    }

    public void update(String name, String phone) {
        if (!name.isEmpty()) {
            userRepository.updateName(name);
            System.out.println("Имя обновлено!");
        } else {
            System.out.println("Введено пустое имя пользователя. Данные не обновлены.");
        }

        if (!phone.isEmpty()) {
            userRepository.updatePhone(phone);
            System.out.println("Телефон обновлен");
        } else {
            System.out.println("Введен пустой номер телефона. Данные не обновлены.");
        }
    }

    public User findById(int id) {
        return userRepository.findById(id);
    }

    public int findUserIdByPhone(String phone) {
        return userRepository.findUserIdByPhone(phone);
    }

    public void assignEquipment(int userId, int equipmentId) {
        EquipmentMenu.getInstance().getEquipmentService().findById(equipmentId).setUserId(userId);
    }

    public void detachEquipment(int equipmentId) {
        EquipmentMenu.getInstance().getEquipmentService().findById(equipmentId).setUserId(0);
    }

    public void showAll() {
        userRepository.showAllUsers();
    }
}
