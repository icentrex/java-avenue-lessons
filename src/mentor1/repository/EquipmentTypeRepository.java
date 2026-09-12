package mentor1.repository;

import mentor1.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class EquipmentTypeRepository {
    private final HashMap<Integer, EquipmentType> equipmentTypes = new HashMap<>();
    private int nextId = 1;

    public EquipmentTypeRepository() {
        add(new EquipmentType("Монитор"));
        add(new EquipmentType("Мышка"));
        add(new EquipmentType("Наушники"));
    }

    public EquipmentType add(EquipmentType equipmentType) {
        equipmentType.setId(nextId);
        equipmentTypes.put(nextId, equipmentType);
        nextId++;
        return equipmentType;
    }

    public boolean deleteEquipmentTypeById(int equipmentTypeId) {
        return equipmentTypes.remove(equipmentTypeId) != null;
    }

    public Optional<EquipmentType> findEquipmentTypeById(int equipmentTypeId) {
        return Optional.ofNullable(equipmentTypes.get(equipmentTypeId));
    }

    public List<EquipmentType> getEquipmentTypesList() {
        return new ArrayList<>(equipmentTypes.values());
    }
}