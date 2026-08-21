package mentor1.service;

import mentor1.TechnicalException;
import mentor1.menu.MainMenu;
import mentor1.model.*;
import mentor1.repository.EquipmentRepository;

import java.util.List;
import java.util.Optional;

public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public Optional<Equipment> createEquipment(int type, String brandName, int serialNumber) {
        if (equipmentRepository.isSerialNumberExist(serialNumber)) {
            return Optional.empty();
        }

        switch (type) {
            case 1 -> {
                Equipment monitor = new Monitor(brandName, serialNumber);
                try {
                    Equipment createdEquipment = equipmentRepository.add(monitor);
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
            case 2 -> {
                Equipment mouse = new Mouse(brandName, serialNumber);
                try {
                    Equipment createdEquipment = equipmentRepository.add(mouse);
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
            case 3 -> {
                Equipment computer = new Computer(brandName, serialNumber);
                try {
                    Equipment createdEquipment = equipmentRepository.add(computer);
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
            default -> {
                //TODO переделать на еще один сценарий
                return Optional.empty();
            }
        }
    }

    public boolean deleteEquipmentById(int equipmentId) {
        return equipmentRepository.deleteEquipmentById(equipmentId);
    }

    public Optional<Equipment> findEquipmentById(int equipmentId) {
        return equipmentRepository.findEquipmentById(equipmentId);
    }

    public void updateBrandName(int currentEquipmentId, String brandName) {
        equipmentRepository.updateBrandName(currentEquipmentId, brandName);
    }

    public void updateSerialNumber(int currentEquipmentId, int serialNumber) {
        equipmentRepository.updateSerialNumber(currentEquipmentId, serialNumber);
    }

    //метод для UserMenu
    public List<Equipment> getUserEquipments(int userId) {
        return equipmentRepository.getUserEquipments(userId);
    }

    public void showAssignedUserByEquipmentId(int equipmentId) {
        int assignedUserId = equipmentRepository.getAssignedUserByEquipmentId(equipmentId);

        if (assignedUserId == 0) {
            System.out.println("Техника не закреплена за пользователем");
            return;
        }

        Optional<User> findUserResult = MainMenu.getInstance().getUserService().findUserById(assignedUserId);
        if (findUserResult.isEmpty()) {
            MainMenu.getInstance().getDisplayReadWriter()
                    .write(List.of("Такого пользователя не существует"));
            return;
        }

        User user = findUserResult.get();
        System.out.printf("userId = %s, name = %s, phone = %s%n", user.getId(), user.getName(), user.getPhone());
    }

    //метод для UserMenu
    public List<Equipment> getFreeEquipments() {
        List<Equipment> freeEquipments = equipmentRepository.getFreeEquipments();

        if (freeEquipments.isEmpty()) {
            System.out.println("Вся техника занята");
        }

        return freeEquipments;
    }

    public boolean assignEquipment(int userId, int equipmentId) {
        return equipmentRepository.assignEquipment(userId, equipmentId);
    }

    public boolean detachEquipment(int equipmentId) {
        return equipmentRepository.detachEquipment(equipmentId);
    }

    public List<Equipment> getEquipmentsList() {
        return equipmentRepository.getEquipmentsList();
    }
}
