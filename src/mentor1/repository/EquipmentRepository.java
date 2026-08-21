package mentor1.repository;

import mentor1.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class EquipmentRepository {
    private final Map<Integer, Equipment> equipments = new HashMap<>();
    private int nextId = 1;

    public EquipmentRepository() {
        add(new Monitor("Samsung", 1));
        add(new Mouse("Logitech", 2));
        add(new Computer("Dell", 3));
    }

    public Equipment add(Equipment equipment) {
        equipment.setId(nextId);
        equipments.put(nextId, equipment);
        nextId++;
        return equipment;
    }

    public boolean deleteEquipmentById(int equipmentId) {
        return equipments.remove(equipmentId) != null;
    }

    public Optional<Equipment> findEquipmentById(int equipmentId) {
        return Optional.ofNullable(equipments.get(equipmentId));
    }

    public void updateBrandName(int equipmentId, String brandName) {
        Equipment equipment = findEquipmentById(equipmentId);
        if (equipment != null) {
            equipment.setBrandName(brandName);
        }
    }

    public void updateSerialNumber(int equipmentId, int serialNumber) {
        Equipment equipment = findEquipmentById(equipmentId);
        if (equipment != null) {
            equipment.setSerialNumber(serialNumber);
        }
    }

    public boolean isSerialNumberExist(int serialNumber) {
        return equipments.values().stream()
                .anyMatch(equipment -> (equipment.getSerialNumber() == serialNumber));
    }

    public List<Equipment> getEquipmentsList() {
        return equipments.values().stream()
                .sorted(Comparator.comparing(Equipment::getId))
                .collect(Collectors.toList());
    }

    //метод для UserMenu
    public List<Equipment> getUserEquipments(int userId) {
        return equipments.values().stream()
                .filter(equipment -> equipment.getUserId() == userId)
                .collect(Collectors.toList());
    }

    //метод для UserMenu
    public List<Equipment> getFreeEquipments() {
        return equipments.values().stream()
                .filter((equipment -> equipment.getUserId() == 0))
                .collect(Collectors.toList());
    }

    public boolean assignEquipment(int userId, int equipmentId) {
        Equipment equipment = equipments.get(equipmentId);

        if (equipment != null) {
            equipment.setUserId(userId);
            return true;
        }
        return false;
    }

    public boolean detachEquipment(int equipmentId) {
        Equipment equipment = equipments.get(equipmentId);

        if (equipment != null) {
            equipment.setUserId(0);
            return true;
        }
        return false;
    }

    public int getAssignedUserByEquipmentId(int equipmentId) {
        return equipments.get(equipmentId).getUserId();
    }
}
