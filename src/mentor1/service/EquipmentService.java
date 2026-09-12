package mentor1.service;

import mentor1.TechnicalException;
import mentor1.model.*;
import mentor1.repository.EquipmentRepository;
import mentor1.repository.EquipmentTypeRepository;

import java.util.List;
import java.util.Optional;

public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final EquipmentTypeRepository equipmentTypeRepository;

    public EquipmentService(EquipmentRepository equipmentRepository, EquipmentTypeRepository equipmentTypeRepository) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentTypeRepository = equipmentTypeRepository;
    }

    public Optional<Equipment> createEquipment(EquipmentType equipmentType, String brandName, int serialNumber) {
        if (equipmentRepository.isSerialNumberExist(serialNumber)) {
            return Optional.empty();
        }

        Equipment equipment = new Equipment(equipmentType, brandName, serialNumber);
        try {
            Equipment createdEquipment = equipmentRepository.add(equipment);
            if (createdEquipment == null) {
                throw new TechnicalException("Репозиторий вернул ошибку при сохранении техники", null);
            }
            return Optional.of(createdEquipment);
        } catch (TechnicalException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new TechnicalException("Техническая ошибка при создании техники", e);
        }
    }

    public boolean deleteEquipmentById(int equipmentId) {
        Optional<Equipment> equipmentFindResult = equipmentRepository.findEquipmentById(equipmentId);
        if (equipmentFindResult.isPresent()) {
            if (equipmentFindResult.get().getUser() == null) {
                return equipmentRepository.deleteEquipmentById(equipmentId);
            }
        }

        return false;
    }

    public Optional<Equipment> findEquipmentById(int equipmentId) {
        return equipmentRepository.findEquipmentById(equipmentId);
    }

    public boolean updateBrandName(int currentEquipmentId, String brandName) {
        return equipmentRepository.updateBrandName(currentEquipmentId, brandName);
    }

    public boolean updateSerialNumber(int currentEquipmentId, int serialNumber) {
        return equipmentRepository.updateSerialNumber(currentEquipmentId, serialNumber);
    }

    public boolean updateEquipmentType(int equipmentId, EquipmentType equipmentType) {
        return equipmentRepository.updateEquipmentType(equipmentId, equipmentType);
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
        return equipmentRepository.assignEquipment(user, equipmentId);
    }

    public boolean detachEquipment(int equipmentId) {
        return equipmentRepository.detachEquipment(equipmentId);
    }

    public List<Equipment> getEquipmentsList() {
        return equipmentRepository.getEquipmentsList();
    }

    public List<EquipmentType> getEquipmentTypesList() {
        return equipmentTypeRepository.getEquipmentTypesList();
    }
}