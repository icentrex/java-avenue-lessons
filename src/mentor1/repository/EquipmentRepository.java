package mentor1.repository;

import mentor1.model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EquipmentRepository {
//    private final Path path = Path
//            .of("/Users/aleafoninser/IdeaProjects/group55/src/mentor1/repository/EquipmentRepository.txt");
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

//    public List<Equipment> getEquipmentsListFromFile() {
//        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
//            return lines
//                    .filter(line -> !line.isEmpty())
//                    .map(line -> {
//                        String[] tokens = line.trim().split("\\s+");
//                        return new Equipment(Integer.parseInt());
//                    })
//                    .toList();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return Collections.emptyList();
//        }
//    }

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