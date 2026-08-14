package mentor1.service;

import mentor1.menu.DisplayReadWriter;
import mentor1.model.*;
import mentor1.repository.EquipmentRepository;

import java.util.List;
import java.util.Optional;

public class EquipmentService implements DisplayReadWriter {
    private final EquipmentRepository equipmentRepository;
    private UserService userService;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Equipment create(int type, String brandName, int serialNumber) {
        if (equipmentRepository.isSerialNumberExist(serialNumber)) {
            DisplayReadWriter.write(List.of("Техника с таким серийным номером уже существует!"));
            return null;
        }

        switch (type) {
            case 1 -> {
                Equipment monitor = new Monitor(brandName, serialNumber);
                equipmentRepository.add(monitor);
                return monitor;
            }
            case 2 -> {
                Equipment mouse = new Mouse(brandName, serialNumber);
                equipmentRepository.add(mouse);
                return mouse;
            }
            case 3 -> {
                Equipment computer = new Computer(brandName, serialNumber);
                equipmentRepository.add(computer);
                return computer;
            }
            default -> {
                DisplayReadWriter.write(List.of("Такого оборудования не существует!"));
                return null;
            }
        }
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
    public List<Equipment> getUserEquipments(int userId) {
        return equipmentRepository.getUserEquipments(userId);
    }

    public void showAssignedUserByEquipmentId(int equipmentId) {
        int assignedUserId = equipmentRepository.getAssignedUserByEquipmentId(equipmentId);

        if (assignedUserId == 0) {
            System.out.println("Техника не закреплена за пользователем");
            return;
        }

        Optional<User> findUserResult = userService.findUserById(assignedUserId);
        if (findUserResult.isEmpty()) {
            DisplayReadWriter.write(List.of("Такого пользователя не существует"));
            return;
        }

        User user = findUserResult.get();
        System.out.printf("userId = %s, name = %s, phone = %s%n", user.getId(), user.getName(), user.getPhone());
    }

    //метод для UserMenu
    public List<Equipment> getFreeEquipments() {
        List<Equipment> freeEquipments = equipmentRepository.getFreeEquipments();

        if (freeEquipments.isEmpty()) {
            System.out.println("Вся техника занята");
        }

        return freeEquipments;
    }

    public boolean assignEquipment(int userId, int equipmentId) {
        return equipmentRepository.assignEquipment(userId, equipmentId);
    }

    public boolean detachEquipment(int equipmentId) {
        return equipmentRepository.detachEquipment(equipmentId);
    }

    public void showUsersList() {
        userService.getUsersList();
    }
}
