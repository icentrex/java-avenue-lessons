package mentor1.repository;

import mentor1.model.*;
import java.util.ArrayList;
import java.util.List;

public class EquipmentRepository {
    private final List<Equipment> equipments = new ArrayList<>();

    public EquipmentRepository() {
        // Заполняем БД
        this.equipments.add(new Monitor("Samsung", 1));
        this.equipments.add(new Mouse("Logitech", 2));
        this.equipments.add(new Computer("Dell", 3));
    }

    public void add(Equipment equipment) {
        if (equipments.contains(equipment)) {
            System.out.println("Техника уже существует!");
        }
        equipments.add(equipment);
    }

    public void deleteById(int id) {
        equipments.remove(id);
    }

    public Equipment findById(int id) {
        return equipments.get(id);
    }

    public void updateName(String name) {
        Equipment equipment = findByName(name);
        if (equipment != null) {
            equipment.setName(name);
        }
    }

    public void updateSerialNumber(int number) {
        Equipment equipment = findBySerialNumber(number);
        if (equipment != null) {
            equipment.setSerialNumber(number);
        }
    }

    public Equipment findByName(String name) {
        for (Equipment equipment : equipments) {
            if (equipment.getName().equalsIgnoreCase(name)) {
                return equipment;
            }
        }
        return null;
    }

    public Equipment findBySerialNumber(int number) {
        for (Equipment equipment : equipments) {
            if (equipment.getSerialNumber() == number) {
                return equipment;
            }
        }
        return null;
    }

    public void showAll() {
        for (int index = 0; index < equipments.size(); index++) {
            System.out.printf("id = %d, name = %s, serialNumber = %d, userId = %d%n", index, equipments.get(index).getName(),
                    equipments.get(index).getSerialNumber(), equipments.get(index).getUserId());
        }
    }

    public void assignTo(int equipmentId, int userId) {
        for (Equipment equipment : equipments) {
            if (equipment.getSerialNumber() == equipmentId) {
                equipment.setUserId(userId);
                System.out.printf("\nТехника с id=%d выдана пользователю с id=%d", equipmentId, userId);
                return;
            }
        }
        System.out.println("\nТехника не найдена");
    }
}
