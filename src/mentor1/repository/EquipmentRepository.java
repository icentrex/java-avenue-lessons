package mentor1.repository;

import mentor1.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class EquipmentRepository implements EquipmentRepositoryInterface {
    private final Map<Integer, Equipment> equipments = new HashMap<>();
    private int nextId = 1;

    @Override
    public Equipment add(Equipment equipment) {
        equipment.setId(nextId);
        //на сохранение отравляем копию
        equipments.put(nextId, new Equipment(equipment));
        nextId++;
        return equipment;
    }

    @Override
    public boolean delete(int equipmentId) {
        return equipments.remove(equipmentId) != null;
    }

    @Override
    public Optional<Equipment> find(int equipmentId) {
        //в то место, откуда вызван метод, отдаем копию, если найдена
        return Optional.ofNullable(equipments.get(equipmentId)).map(e -> new Equipment(e));
    }

    @Override
    public boolean update(Equipment equipment) {
        if (!equipments.containsKey(equipment.getId())) {
            return false;
        }

        //сохраняем копию объекта
        equipments.put(equipment.getId(), new Equipment(equipment));
        return true;
    }

    @Override
    public boolean isSerialNumberExist(int serialNumber) {
        return equipments.values().stream()
                .anyMatch(equipment -> (equipment.getSerialNumber() == serialNumber));
    }

    @Override
    public List<Equipment> getEquipmentsList() {
        return equipments.values().stream()
                .sorted(Comparator.comparing(Equipment::getId))
                //отдаем копии объектов
                .map(equipment -> new Equipment(equipment))
                .collect(Collectors.toList());
    }

    //метод для UserMenu и User
    @Override
    public List<Equipment> getUserEquipments(int userId) {
        return equipments.values().stream()
                .filter(equipment -> equipment.getUser() != null && equipment.getUser().getId() == userId)
                //отдаем копии объектов
                .map(equipment -> new Equipment(equipment))
                .collect(Collectors.toList());
    }

    //метод для UserMenu и User
    @Override
    public List<Equipment> getFreeEquipments() {
        return equipments.values().stream()
                .filter((equipment -> equipment.getUser() == null))
                //отдаем копии объектов
                .map(equipment -> new Equipment(equipment))
                .collect(Collectors.toList());
    }

    @Override
    public boolean assign(User user, int equipmentId) {
        Equipment equipment = equipments.get(equipmentId);

        if (equipment != null && user != null) {
            equipment.setUser(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean detach(int equipmentId) {
        Equipment equipment = equipments.get(equipmentId);

        if (equipment != null) {
            equipment.setUser(null);
            return true;
        }
        return false;
    }
}