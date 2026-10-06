package mentor1.repository;

import mentor1.exceptions.DuplicateRequestException;
import mentor1.model.EquipmentType;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EquipmentTypeFileRepository {
    private static final String FILE_PATH = "src/mentor1/repository/EquipmentTypeRepository.txt";
    private final Path path = Path.of(FILE_PATH);
    private int nextId = 1;

    public EquipmentTypeFileRepository() {
        nextId = maxIdFromFile() + 1;
        if (nextId == 1) {
            add(new EquipmentType("Монитор"));
            add(new EquipmentType("Мышка"));
            add(new EquipmentType("Наушники"));
        }
    }

    private int maxIdFromFile() {
        return readFileRepositoryToList().stream()
                .mapToInt(EquipmentType::getId)
                .max()
                .orElse(0);
    }

    private String toLine(EquipmentType et) {
        return et.getId() + ";" + et.getName();
    }

    private void writeToFile(List<EquipmentType> equipmentTypes) {
        List<String> lines = equipmentTypes.stream()
                .map(this::toLine)
                .collect(Collectors.toList());
        try {
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException("Не удалось записать в файл: " + path, e);
        }
    }

    public EquipmentType add(EquipmentType equipmentType) throws DuplicateRequestException {
        List<EquipmentType> all = readFileRepositoryToList();

        boolean alreadyExistsByName = all.stream()
                .anyMatch(et -> equipmentType.getName().equalsIgnoreCase(et.getName()));

        if (alreadyExistsByName) {
            throw new DuplicateRequestException("Такой тип техники уже существует");
        }

        equipmentType.setId(nextId);
        nextId++;
        all.add(equipmentType);

        writeToFile(all);
        return equipmentType;
    }

    public boolean deleteEquipmentTypeById(int equipmentTypeId) {
        //todo проверка на наличие техники с таким типом
        List<EquipmentType> all = readFileRepositoryToList();

        List<EquipmentType> remaining = all.stream()
                .filter(equipmentType -> equipmentType.getId() != equipmentTypeId)
                .collect(Collectors.toList());

        if (remaining.size() == all.size()) {
            return false; //такого типа нет в файле
        }

        writeToFile(remaining);
        return true;
    }

    public Optional<EquipmentType> findEquipmentTypeById(int equipmentTypeId) {
        return readFileRepositoryToList().stream()
                .filter(equipmentType -> equipmentType.getId() == equipmentTypeId)
                .findFirst();
    }

    public Optional<EquipmentType> findEquipmentTypeByName(String name) {
        return readFileRepositoryToList().stream()
                .filter(equipmentType -> equipmentType.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public List<EquipmentType> getEquipmentTypesListFromFile() {
        return readFileRepositoryToList();
    }

    public List<EquipmentType> readFileRepositoryToList() {
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }

        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            return lines
                    .filter(line -> !line.isBlank())
                    .map(line -> {
                        String[] tokens = line.split(";", 2);
                        if (tokens.length < 2) {
                            throw new IllegalArgumentException("Строка не соответствует формату: " + line);
                        }
                        return new EquipmentType(Integer.parseInt(tokens[0]), tokens[1].trim());
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException("Не удалось прочитать файл: " + path, e);
        }
    }
}