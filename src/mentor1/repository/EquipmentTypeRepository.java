package mentor1.repository;

import com.sun.jdi.request.DuplicateRequestException;
import mentor1.model.*;

import java.util.*;

public class EquipmentTypeRepository {
    private final HashMap<Integer, EquipmentType> equipmentTypes = new HashMap<>();
    private int nextId = 1;

    public EquipmentTypeRepository() {
        add(new EquipmentType("Монитор"));
        add(new EquipmentType("Мышка"));
        add(new EquipmentType("Наушники"));
    }

    public EquipmentType add(EquipmentType equipmentType) throws DuplicateRequestException {
        Optional<EquipmentType> equipmentTypeFindResult = findEquipmentTypeByName(equipmentType.getName());

        if (equipmentTypeFindResult.isEmpty()) {
            equipmentType.setId(nextId);
            equipmentTypes.put(nextId, equipmentType);
            nextId++;
            return equipmentType;
        } else {
            throw new DuplicateRequestException("Такой тип техники уже существует");
        }
    }

    public boolean deleteEquipmentTypeById(int equipmentTypeId) {
        return equipmentTypes.remove(equipmentTypeId) != null;
    }

    public Optional<EquipmentType> findEquipmentTypeById(int equipmentTypeId) {
        return Optional.ofNullable(equipmentTypes.get(equipmentTypeId));
    }

    public Optional<EquipmentType> findEquipmentTypeByName(String name) {
        for (EquipmentType equipmentType : equipmentTypes.values()) {
            if (equipmentType.getName().equalsIgnoreCase(name)) {
                return Optional.of(equipmentType);
            }
        }
        return Optional.empty();
    }

    public List<EquipmentType> getEquipmentTypesList() {
        return new ArrayList<>(equipmentTypes.values());
    }
}