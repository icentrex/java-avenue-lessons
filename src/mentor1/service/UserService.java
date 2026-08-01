package mentor1.service;

import mentor1.menu.MainMenu;
import mentor1.model.Equipment;
import mentor1.model.User;
import mentor1.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;
    private EquipmentService equipmentService;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setEquipmentService(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    public User create(String name, String phone) {
        if (userRepository.isNameExist(name) && userRepository.isPhoneExist(phone)) {
            return null;
        }
        return userRepository.add(new User(name, phone));
    }

    public List<String> deleteByUserId(String userId) {
        List<Equipment> userEquipments = equipmentService.getUserEquipments(userId);

        if (userEquipments.isEmpty()) {
            userRepository.deleteById(userId);
            MainMenu.getInstance().setCursorObject(null);
            return List.of("Закрепленная техника отсутствует!", "Пользователь удален", "Перехожу в главное меню");
        }
        return List.of("Удалить нельзя. За пользователем закреплена техника!");
    }

    public void updateName(String userId, String name) {
        userRepository.updateName(userId, name);
    }

    public void updatePhone(String userId, String phone) {
        if (userRepository.isPhoneExist(phone)) {
            System.out.println("Пользователь с таким номером телефона уже существует!");
            return;
        }
        userRepository.updatePhone(userId, phone);
    }

    public User findByUserId(String userId) {
        return userRepository.findById(userId);
    }

    public void assignEquipment(String userId, int equipmentId) {
        equipmentService.assignEquipment(userId, equipmentId);
    }

    public void detachEquipment(int equipmentId) {
        equipmentService.detachEquipment(equipmentId);
    }

    public List<String> getInfo(String userId) {
        User user = userRepository.findById(userId);

        if (user == null) {
            return List.of("Пользователя с таким id не существует");
        }

        return List.of(String.format("userId = %s, name = %s, phone = %s%n",
                user.getId(), user.getName(), user.getPhoneNumber()));
    }

    public List<String> getUsersList() {
        List<User> userList = userRepository.getUsersList();

        if (userList.isEmpty()) {
            return List.of("Список пользователей пуст");
        }

        return userList.stream()
                .map(e -> String.format("userId = %s, name = %s, phone = %s%n",
                        e.getId(),
                        e.getName(),
                        e.getPhoneNumber()))
                .toList();
    }

    public List<String> getFreeEquipments() {
        List<Equipment> freeEquipments = equipmentService.getFreeEquipments();

        if (freeEquipments.isEmpty()) {
            return List.of("Нет свободной техники");
        }

        return freeEquipments.stream()
                .map(e -> String.format("id = %d, name = %s, serialNumber = %d, userId = %s%n",
                        e.getId(),
                        e.getBrandName(),
                        e.getSerialNumber(),
                        e.getUserId()))
                .toList();
    }

    public List<String> getUserEquipments(String userId) {
        List<Equipment> userEquipments = equipmentService.getUserEquipments(userId);

        if (userEquipments.isEmpty()) {
            return List.of("У пользователя нет техники");
        }

        return userEquipments.stream()
                .map(e -> String.format("id = %d, name = %s, serialNumber = %d, userId = %s%n",
                        e.getId(),
                        e.getBrandName(),
                        e.getSerialNumber(),
                        e.getUserId()))
                .toList();
    }
}
