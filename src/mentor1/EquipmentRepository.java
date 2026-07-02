package mentor1;

import java.util.ArrayList;
import java.util.List;

public class EquipmentRepository {
    private List<Equipment> equipments = new ArrayList<>();

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
