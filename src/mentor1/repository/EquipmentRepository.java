package mentor1.repository;

import mentor1.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class EquipmentRepository {
    private final Map<Integer, Equipment> equipments = new HashMap<>();

    public EquipmentRepository() {
        Equipment equipment = new Monitor("Samsung", 1);
        this.equipments.put(equipment.getId(), equipment);
        Equipment equipment1 = new Mouse("Logitech", 2);
        this.equipments.put(equipment1.getId(), equipment1);
        Equipment equipment2 = new Computer("Dell", 3);
        this.equipments.put(equipment2.getId(), equipment2);
    }

    public Equipment add(int type, String brandName, int serialNumber) {

        if (isSerialNumberExist(serialNumber)) {
            System.out.println("Техника с таким серийным номером уже существует!");
            return null;
        }

        switch (type) {
            case 1 -> {
                Equipment monitor = new Monitor(brandName, serialNumber);
                equipments.put(monitor.getId(), monitor);
                return monitor;
            }
            case 2 -> {
                Equipment mouse = new Mouse(brandName, serialNumber);
                equipments.put(mouse.getId(), mouse);
                return mouse;
            }
            case 3 -> {
                Equipment computer = new Computer(brandName, serialNumber);
                equipments.put(computer.getId(), computer);
                return computer;
            }
            default -> {
                System.out.println("Такого оборудования не существует!");
                return null;
            }
        }
    }

    public void deleteById(int equipmentId) {
        equipments.remove(equipmentId);
    }

    public Equipment findById(int equipmentId) {
        return equipments.get(equipmentId);
    }

    public void updateBrandName(int equipmentId, String brandName) {
        Equipment equipment = findById(equipmentId);
        if (equipment != null) {
            equipment.setBrandName(brandName);
        }
    }

    public void updateSerialNumber(int equipmentId, int serialNumber) {
        Equipment equipment = findById(equipmentId);
        if (equipment != null) {
            equipment.setSerialNumber(serialNumber);
        }
    }

    public boolean isSerialNumberExist(int serialNumber) {
        return equipments.values().stream()
                .anyMatch(equipment -> (equipment.getSerialNumber() == serialNumber));
    }

    public Equipment getEquipmentInfo(int equipmentId) {
        return equipments.get(equipmentId);
    }

    public List<Equipment> getAllEquipments() {
        return equipments.values().stream()
                .sorted(Comparator.comparing(Equipment::getId))
                .collect(Collectors.toList());
    }

    //метод для UserMenu
    public List<Equipment> getUserEquipments(String userId) {
        return equipments.values().stream()
                .filter(equipment -> equipment.getUserId().equalsIgnoreCase(userId))
                .collect(Collectors.toList());
    }

    //метод для UserMenu
    public List<Equipment> getFreeEquipments() {
        return equipments.values().stream()
                .filter((equipment -> equipment.getUserId().equalsIgnoreCase("Не закреплена")))
                .collect(Collectors.toList());
    }

    public void assignEquipment(String userId, int equipmentId) {
        Equipment equipment = equipments.get(equipmentId);

        if (equipment != null) {
            equipment.setUserId(userId);
        }
    }

    public void detachEquipment(int equipmentId) {
        Equipment equipment = equipments.get(equipmentId);

        if (equipment != null) {
            equipment.setUserId("Не закреплено");
        }
    }

    public String getAssignedUserByEquipmentId(int equipmentId) {
        return equipments.get(equipmentId).getUserId();
    }
}
