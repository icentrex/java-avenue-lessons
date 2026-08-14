package mentor1.service;

import mentor1.model.Equipment;
import mentor1.model.User;
import mentor1.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;
    private EquipmentService equipmentService;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setEquipmentService(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    public Optional<User> createUser(String name, String phone) {
        if (userRepository.isPhoneExist(phone)) {
            return Optional.empty();
        }

        User user = new User(name, phone);
        return Optional.of(userRepository.addUser(user));
    }

    public boolean deleteUserById(int userId) {
        List<Equipment> userEquipments = equipmentService.getUserEquipments(userId);

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

    public boolean assignEquipment(int userId, int equipmentId) {
        return equipmentService.assignEquipment(userId, equipmentId);
    }

    public boolean detachEquipment(int equipmentId) {
        return equipmentService.detachEquipment(equipmentId);
    }

    public List<User> getUsersList() {
        return userRepository.getUsersList();
    }

    public List<Equipment> getFreeEquipments() {
        return equipmentService.getFreeEquipments();
    }

    public List<Equipment> getUserEquipments(int userId) {
        return equipmentService.getUserEquipments(userId);
    }
}
