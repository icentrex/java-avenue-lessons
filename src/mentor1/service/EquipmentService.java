package mentor1.service;

import mentor1.model.*;
import mentor1.repository.EquipmentRepository;
import mentor1.repository.EquipmentTypeRepository;

import java.util.List;
import java.util.Optional;

public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final EquipmentTypeRepository equipmentTypeRepository;

    public EquipmentService(EquipmentRepository equipmentRepository,
                            EquipmentTypeRepository equipmentTypeRepository) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentTypeRepository = equipmentTypeRepository;
    }

    public Optional<Equipment> createEquipment(EquipmentType equipmentType, String brandName, int serialNumber) {
        if (equipmentType == null || brandName == null || brandName.isBlank()) {
            return Optional.empty();
        }

        if (equipmentRepository.isSerialNumberExist(serialNumber)) {
            return Optional.empty();
        }

        Equipment equipment = new Equipment(equipmentType, brandName, serialNumber);
        return Optional.of(equipmentRepository.add(equipment));
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

    public UpdateResult updateEquipment(Equipment equipment) {
        if (equipment.getEquipmentType() == null || equipment.getBrandName() == null ||
                equipment.getBrandName().isBlank()) {
            return UpdateResult.INVALID_DATA;
        }

        if (equipmentRepository.isSerialNumberExist(equipment.getId(), equipment.getSerialNumber())) {
            return UpdateResult.DUPLICATE;
        }
        return equipmentRepository.update(equipment) ? UpdateResult.SUCCESS : UpdateResult.NOT_FOUND;
    }

    //метод для UserMenu и User
    public List<Equipment> getUserEquipments(int userId) {
        return equipmentRepository.getUserEquipments(userId);
    }

    //метод для UserMenu и User
    public List<Equipment> getFreeEquipments() {
        return equipmentRepository.getFreeEquipments();
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