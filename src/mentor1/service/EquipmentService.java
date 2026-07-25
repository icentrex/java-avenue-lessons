package mentor1.service;

import mentor1.model.Computer;
import mentor1.model.Equipment;
import mentor1.model.Monitor;
import mentor1.model.Mouse;
import mentor1.repository.EquipmentRepository;

public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public void add(int type, String name, int serialNumber) {
        switch (type) {
            case 1 -> equipmentRepository.add(new Monitor(name, serialNumber));
            case 2 -> equipmentRepository.add(new Mouse(name, serialNumber));
            case 3 -> equipmentRepository.add(new Computer(name, serialNumber));
            default -> System.out.println("Такого оборудования не существует!");
        }
    }

    public void deleteById(int id) {
        equipmentRepository.deleteById(id);
    }

    public Equipment findById(int id) {
        return equipmentRepository.findById(id);
    }

    public void update(String name, int number) {
        if (!name.isEmpty()) {
            equipmentRepository.updateName(name);
            System.out.println("Имя обновлено!");
        } else {
            System.out.println("Введено пустое имя пользователя. Данные не обновлены.");
        }

        if (number != 0) {
            equipmentRepository.updateSerialNumber(number);
            System.out.println("Серийный номер обновлен");
        } else {
            System.out.println("Введен пустой серийный номер. Данные не обновлены.");
        }
    }

    public void showAll() {
        equipmentRepository.showAll();
    }
}
