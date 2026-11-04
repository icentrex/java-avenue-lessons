package mentor1.repository;

import mentor1.model.Equipment;
import mentor1.model.User;

import java.util.List;
import java.util.Optional;

public interface EquipmentRepositoryInterface {

    Equipment add(Equipment equipment);
    boolean delete(int equipmentId);
    Optional<Equipment> find(int equipmentId);
    boolean update(Equipment equipment);

    boolean isSerialNumberExist(int serialNumber);

    List<Equipment> getEquipmentsList();
    List<Equipment> getUserEquipments(int userId);
    List<Equipment> getFreeEquipments();

    boolean assign(User user, int equipmentId);
    boolean detach(int equipmentId);
}
