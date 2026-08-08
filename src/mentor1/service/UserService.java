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
        if (userRepository.isPhoneExist(phone)) {
            return null;
        }
        User user = new User(name, phone);
        userRepository.add(user);
        return user;
    }

    public String deleteByUserId(int userId) {
        List<Equipment> userEquipments = equipmentService.getUserEquipments(userId);

        if (userEquipments.isEmpty()) {
            userRepository.deleteById(userId);
            MainMenu.getInstance().setCursorObject(null);
            return "200 OK";
        }
        //уточнить код HTTP
        return "300";
    }

    public String updateName(int userId, String name) {
        userRepository.updateName(userId, name);
        return "200 OK";
    }

    public String updatePhone(int userId, String phone) {
        if (userRepository.isPhoneExist(phone)) {
            System.out.println("Пользователь с таким номером телефона уже существует!");
            //уточнить код HTTP
            return "300";
        }
        userRepository.updatePhone(userId, phone);
        return "200 OK";
    }

    public User findByUserId(int userId) {
        return userRepository.findById(userId);
    }

    public String assignEquipment(int userId, int equipmentId) {
        equipmentService.assignEquipment(userId, equipmentId);
        return "200 OK";
    }

    public String detachEquipment(int equipmentId) {
        equipmentService.detachEquipment(equipmentId);
        return "200 OK";
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

    public List<String> getUserEquipments(int userId) {
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
