package mentor1.repository;

import mentor1.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class EquipmentRepository {
    private final Map<Integer, Equipment> equipments = new HashMap<>();
    private int nextId = 1;

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

    public boolean updateBrandName(int equipmentId, String brandName) {
        Optional<Equipment> findEquipmentResult = findEquipmentById(equipmentId);
        if (findEquipmentResult.isEmpty()) {
            return false;
        }

        findEquipmentResult.get().setBrandName(brandName);
        return true;
    }

    public boolean updateSerialNumber(int equipmentId, int serialNumber) {
        Optional<Equipment> findEquipmentResult = findEquipmentById(equipmentId);
        if (findEquipmentResult.isEmpty()) {
            return false;
        }

        findEquipmentResult.get().setSerialNumber(serialNumber);
        return true;
    }

    public boolean updateEquipmentType(int equipmentId, EquipmentType equipmentType) {
        Optional<Equipment> findEquipmentResult = findEquipmentById(equipmentId);
        if (findEquipmentResult.isEmpty()) {
            return false;
        }

        findEquipmentResult.get().setEquipmentType(equipmentType);
        return true;
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

    //метод для UserMenu и User
    public List<Equipment> getUserEquipments(int userId) {
        return equipments.values().stream()
                .filter(equipment -> equipment.getUser() != null && equipment.getUser().getId() == userId)
                .collect(Collectors.toList());
    }

    //метод для UserMenu и User
    public List<Equipment> getFreeEquipments() {
        return equipments.values().stream()
                .filter((equipment -> equipment.getUser() == null))
                .collect(Collectors.toList());
    }

    public boolean assignEquipment(User user, int equipmentId) {
        Equipment equipment = equipments.get(equipmentId);

        if (equipment != null && user != null) {
            equipment.setUser(user);
            return true;
        }
        return false;
    }

    public boolean detachEquipment(int equipmentId) {
        Equipment equipment = equipments.get(equipmentId);

        if (equipment != null) {
            equipment.setUser(null);
            return true;
        }
        return false;
    }
}