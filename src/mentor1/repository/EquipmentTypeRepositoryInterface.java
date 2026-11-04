package mentor1.repository;

import mentor1.model.EquipmentType;

import java.util.List;
import java.util.Optional;

public interface EquipmentTypeRepositoryInterface {

    EquipmentType add(EquipmentType equipmentType);

    boolean delete(int equipmentTypeId);

    Optional<EquipmentType> findById(int equipmentTypeId);

    Optional<EquipmentType> findByName(String name);

    List<EquipmentType> getEquipmentTypesList();
}
