package mentor1.repository;

import mentor1.model.Computer;
import mentor1.model.Equipment;
import mentor1.model.Monitor;
import mentor1.model.Mouse;

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
            System.out.println("\nТехника уже существует!");
        } else {
            equipments.add(equipment);
            System.out.println("Техника " + equipment + " добавлена в каталог");
        }
    }

    public void showAll() {
        System.out.println("\nСписок всей техники:");
        for (Equipment equipment : equipments) {
            System.out.println(equipment);
        }
    }

    public void assignTo(int equipmentId, int userId) {
        for (Equipment equipment : equipments) {
            if (equipment.getId() == equipmentId) {
                equipment.setUserId(userId);
                System.out.printf("\nТехника с id=%d выдана пользователю с id=%d", equipmentId, userId);
                return;
            }
        }
        System.out.println("\nТехника не найдена");
    }

    public void returnFrom(int equipmentId, int userId) {
        for (Equipment equipment : equipments) {
            if (equipment.getId() == equipmentId) {
                equipment.setUserId(0);
                System.out.printf("\nТехника c id=%d принята от пользователя с id=%d", equipmentId, userId);
                return;
            }
        }
        System.out.println("\nТехника не найдена");
    }

    public void showEquipmentListByUser(int userId) {
        System.out.println("\nСписок техники, выданной пользователю:");
        for (Equipment equipment : equipments) {
            if (equipment.getUserId() == userId) {
                System.out.println(equipment);
            }
        }
    }
}
