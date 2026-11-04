package mentor1.service;

import mentor1.exceptions.TechnicalException;
import mentor1.model.*;
import mentor1.repository.EquipmentRepositoryInterface;
import mentor1.repository.EquipmentTypeRepositoryInterface;

import java.util.List;
import java.util.Optional;

public class EquipmentService {
    private final EquipmentRepositoryInterface equipmentRepository;
    private final EquipmentTypeRepositoryInterface equipmentTypeRepository;

    public EquipmentService(EquipmentRepositoryInterface equipmentRepository,
                            EquipmentTypeRepositoryInterface equipmentTypeRepository) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentTypeRepository = equipmentTypeRepository;
    }

    public Optional<Equipment> createEquipment(EquipmentType equipmentType, String brandName, int serialNumber) {
        if (equipmentRepository.isSerialNumberExist(serialNumber)) {
            return Optional.empty();
        }

        Equipment equipment = new Equipment(equipmentType, brandName, serialNumber);
        try {
            return Optional.of(equipmentRepository.add(equipment));
        } catch (RuntimeException e) {
            throw new TechnicalException("Техническая ошибка при создании техники", e);
        }
    }

    public boolean deleteEquipmentById(int equipmentId) {
        Optional<Equipment> equipmentFindResult = equipmentRepository.find(equipmentId);
        if (equipmentFindResult.isPresent()) {
            if (equipmentFindResult.get().getUser() == null) {
                return equipmentRepository.delete(equipmentId);
            }
        }

        return false;
    }

    public Optional<Equipment> findEquipmentById(int equipmentId) {
        return equipmentRepository.find(equipmentId);
    }

    public boolean updateEquipment(Equipment equipment) {
        return equipmentRepository.update(equipment);
    }

    //метод для UserMenu и User
    public List<Equipment> getUserEquipments(int userId) {
        return equipmentRepository.getUserEquipments(userId);
    }

    //метод для UserMenu и User
    public List<Equipment> getFreeEquipments() {
        List<Equipment> freeEquipments = equipmentRepository.getFreeEquipments();

        if (freeEquipments.isEmpty()) {
            System.out.println("Вся техника занята");
        }

        return freeEquipments;
    }

    public boolean assignEquipment(User user, int equipmentId) {
        return equipmentRepository.assign(user, equipmentId);
    }

    public boolean detachEquipment(int equipmentId) {
        return equipmentRepository.detach(equipmentId);
    }

    public List<Equipment> getEquipmentsList() {
        return equipmentRepository.getEquipmentsList();
    }

    public List<EquipmentType> getEquipmentTypesList() {
        return equipmentTypeRepository.getEquipmentTypesList();
    }
}