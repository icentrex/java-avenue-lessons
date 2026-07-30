package mentor1.service;

import mentor1.menu.ConsoleMainMenu;
import mentor1.menu.EquipmentMenu;
import mentor1.model.Equipment;
import mentor1.model.User;
import mentor1.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(String name, String phone) {
        return userRepository.create(name, phone);
    }

    public void deleteByUserId(String userId) {
        List<Equipment> userEquipments = EquipmentMenu.getInstance().getEquipmentService().getUserEquipments(userId);

        if (userEquipments.isEmpty()) {
            System.out.println("Закрепленная техника отсутствует!");
            userRepository.deleteById(userId);
            System.out.println("Пользователь удален");
            ConsoleMainMenu.getInstance().setCursorObject(null);
            System.out.println("Перехожу в главное меню");
            return;
        }

        System.out.println("Удалить нельзя. За пользователем закреплена техника!");
    }

    public void updateName(String userId, String name) {
        userRepository.updateName(userId, name);
    }

    public void updatePhone(String userId, String phone) {
        userRepository.updatePhone(userId, phone);
    }

    public User findByUserId(String userId) {
        return userRepository.findById(userId);
    }

    public void assignEquipment(String userId, int equipmentId) {
        EquipmentMenu.getInstance().getEquipmentService().assignEquipment(userId, equipmentId);
    }

    public void detachEquipment(int equipmentId) {
        EquipmentMenu.getInstance().getEquipmentService().detachEquipment(equipmentId);
    }

    public void showInfo(String userId) {
        User user = userRepository.getInfo(userId);

        if (user == null) {
            System.out.println("Пользователя с таким id не существует");
            return;
        }

        System.out.printf("userId = %s, name = %s, phone = %s%n", user.getId(), user.getName(), user.getPhoneNumber());
    }

    public void showUsersList() {
        List<User> userList = userRepository.getUsersList();

        if (userList.isEmpty()) {
            System.out.println("Список пользователей пуст");
            return;
        }

        for (User user : userList) {
            System.out.printf("userId = %s, name = %s, phone = %s%n",
                    user.getId(),
                    user.getName(),
                    user.getPhoneNumber());
        }
    }

    public void showFreeEquipments() {
        List<Equipment> freeEquipments = EquipmentMenu.getInstance().getEquipmentService().getFreeEquipments();

        if (freeEquipments.isEmpty()) {
            System.out.println("Нет свободной техники");
            return;
        }

        for (Equipment equipment : freeEquipments) {
            System.out.printf("id = %d, name = %s, serialNumber = %d, userId = %s%n",
                    equipment.getId(),
                    equipment.getBrandName(),
                    equipment.getSerialNumber(),
                    equipment.getUserId());
        }
    }

    public void showUserEquipments(String userId) {
        List<Equipment> userEquipments = EquipmentMenu.getInstance().getEquipmentService().getUserEquipments(userId);

        if (userEquipments.isEmpty()) {
            System.out.println("У пользователя нет техники");
            return;
        }

        for (Equipment equipment : userEquipments) {
            System.out.printf("id = %d, name = %s, serialNumber = %d, userId = %s%n",
                    equipment.getId(),
                    equipment.getBrandName(),
                    equipment.getSerialNumber(),
                    equipment.getUserId());
        }
    }
}
