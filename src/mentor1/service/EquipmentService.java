package mentor1.service;

import mentor1.model.*;
import mentor1.repository.EquipmentRepository;

import java.util.List;

public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private UserService userService;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Equipment add(int type, String brandName, int serialNumber) {
        return equipmentRepository.add(type, brandName, serialNumber);
    }

    public void deleteById(int id) {
        equipmentRepository.deleteById(id);
    }

    public Equipment findById(int id) {
//        Equipment equipment = equipmentRepository.findById(id);
//        equipment.setUser(userService.findByEquipmentId(this.id));
//        return equipment;
        return null;
    }

    public void updateBrandName(int currentEquipmentId, String brandName) {
        equipmentRepository.updateBrandName(currentEquipmentId, brandName);
    }

    public void updateSerialNumber(int currentEquipmentId, int serialNumber) {
        equipmentRepository.updateSerialNumber(currentEquipmentId, serialNumber);
    }

    public void showAllEquipments() {
        List<Equipment> catalog = equipmentRepository.getAllEquipments();

        if (catalog.isEmpty()) {
            System.out.println("Техника отсутствует");
            return;
        }

        for (Equipment equipment : catalog) {
            System.out.printf("id = %d, name = %s, serialNumber = %d, userId = %s%n",
                    equipment.getId(),
                    equipment.getBrandName(),
                    equipment.getSerialNumber(),
                    equipment.getUserId());
        }
    }

    //метод для UserMenu
    public List<Equipment> getUserEquipments(String userId) {
        return equipmentRepository.getUserEquipments(userId);
    }

    public void showAssignedUserByEquipmentId(int equipmentId) {
        String assignedUserId = equipmentRepository.getAssignedUserByEquipmentId(equipmentId);

        if (assignedUserId.isEmpty()) {
            System.out.println("Техника не закреплена за пользователем");
            return;
        }

        User user = userService.findByUserId(assignedUserId);
        if (user == null) {
            System.out.println("Такого пользователя не существует");
            return;
        }

        System.out.printf("userId = %s, name = %s, phone = %s%n", user.getId(), user.getName(), user.getPhoneNumber());
    }

    //метод для UserMenu
    public List<Equipment> getFreeEquipments() {
        List<Equipment> freeEquipments = equipmentRepository.getFreeEquipments();

        if (freeEquipments.isEmpty()) {
            System.out.println("Вся техника занята");
        }

        return freeEquipments;
    }

    public void assignEquipment(String userId, int equipmentId) {
        equipmentRepository.assignEquipment(userId, equipmentId);
    }

    public void detachEquipment(int equipmentId) {
        equipmentRepository.detachEquipment(equipmentId);
    }

    public void showEquipmentInfo(int equipmentId) {
        Equipment equipment = equipmentRepository.getEquipmentInfo(equipmentId);

        if (equipment == null) {
            System.out.println("Техники с таким id не существует");
            return;
        }

        System.out.printf("id = %d, name = %s, serialNumber = %d, userId = %s%n",
                equipment.getId(),
                equipment.getBrandName(),
                equipment.getSerialNumber(),
                equipment.getUserId());
    }

    public void showUsersList() {
        userService.getUsersList();
    }
}
