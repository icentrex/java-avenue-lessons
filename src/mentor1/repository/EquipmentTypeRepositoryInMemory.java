package mentor1.repository;

import mentor1.exceptions.DuplicateRequestException;
import mentor1.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class EquipmentTypeRepositoryInMemory implements EquipmentTypeRepository {
    private final HashMap<Integer, EquipmentType> equipmentTypes = new HashMap<>();
    private int nextId = 1;

    public EquipmentTypeRepositoryInMemory() {
        add(new EquipmentType("Монитор"));
        add(new EquipmentType("Мышка"));
        add(new EquipmentType("Наушники"));
    }

    @Override
    public EquipmentType add(EquipmentType equipmentType) throws DuplicateRequestException {
        Optional<EquipmentType> equipmentTypeFindResult = findByName(equipmentType.getName());

        if (equipmentTypeFindResult.isEmpty()) {
            equipmentType.setId(nextId);
            equipmentTypes.put(nextId, new EquipmentType(equipmentType));
            nextId++;
            return equipmentType;
        } else {
            throw new DuplicateRequestException("Такой тип техники уже существует");
        }
    }

    @Override
    public boolean delete(int equipmentTypeId) {
        return equipmentTypes.remove(equipmentTypeId) != null;
    }

    @Override
    public Optional<EquipmentType> findById(int equipmentTypeId) {
        return Optional.ofNullable(equipmentTypes.get(equipmentTypeId)).map(et -> new EquipmentType(et));
    }

    @Override
    public Optional<EquipmentType> findByName(String name) {
        for (EquipmentType equipmentType : equipmentTypes.values()) {
            if (equipmentType.getName().equalsIgnoreCase(name)) {
                return Optional.of(new EquipmentType(equipmentType));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<EquipmentType> getEquipmentTypesList() {
        return equipmentTypes.values().stream()
                .map(equipmentType -> new EquipmentType(equipmentType))
                .collect(Collectors.toList());
    }
}