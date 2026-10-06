package mentor1.repository;

import mentor1.exceptions.DuplicateRequestException;
import mentor1.model.Equipment;
import mentor1.model.EquipmentType;
import mentor1.model.User;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EquipmentFileRepository {
    private static final String FILE_PATH = "src/mentor1/repository/EquipmentRepository.txt";
    private final Path path = Path.of(FILE_PATH);
    private final EquipmentTypeFileRepository equipmentTypeFileRepository = new EquipmentTypeFileRepository();
    private final UserFileRepository userFileRepository = new UserFileRepository();
    private int nextId = 1;

    public EquipmentFileRepository() {
        nextId = maxIdFromFile() + 1;
    }

    private int maxIdFromFile() {
        return readFileRepositoryToList().stream()
                .mapToInt(Equipment::getId)
                .max()
                .orElse(0);
    }

    private String toLine(Equipment e) {
        int userId = e.getUser() != null ? e.getUser().getId() : 0;
        return e.getId() + ";" + e.getEquipmentType().getId() + ";" + e.getBrandName() + ";"
                + e.getSerialNumber() + ";" + e.getUser().getId();
    }

    private void writeToFile(List<Equipment> equipments) {
        List<String> lines = equipments.stream()
                .map(this::toLine)
                .collect(Collectors.toList());
        try {
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException("Не удалось записать в файл: " + path, e);
        }
    }

    public Equipment add(Equipment equipment) {
        List<Equipment> all = readFileRepositoryToList();

        boolean alreadyExist = all.stream().anyMatch(e ->
                equipment.getEquipmentType().equals(e.getEquipmentType()) && equipment.getBrandName().equalsIgnoreCase(e.getBrandName()) &&
                        equipment.getSerialNumber() == e.getSerialNumber());

        if (alreadyExist) {
            throw new DuplicateRequestException("Техника с таким типом, производителем и серийным номером уже существует");
        }

        equipment.setId(nextId);
        nextId++;
        all.add(equipment);

        writeToFile(all);
        return equipment;
    }

    public boolean deleteEquipmentById(int equipmentId) {
        //todo проверка на наличие закрепления за пользователем
        List<Equipment> all = readFileRepositoryToList();

        List<Equipment> remaining = all.stream()
                .filter(equipment -> equipment.getId() != equipmentId)
                .collect(Collectors.toList());

        if (remaining.size() == all.size()) {
            return false; //такой техники нет в файле
        }

        writeToFile(remaining);
        return true;
    }

    public Optional<Equipment> findEquipmentById(int equipmentId) {
        return readFileRepositoryToList().stream()
                .filter(equipment -> equipment.getId() == equipmentId)
                .findFirst();
    }

    public List<Equipment> readFileRepositoryToList() {
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }

        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            return lines
                    .filter(line -> !line.isBlank())
                    .map(line -> {
                        String[] tokens = line.split(";", 5);
                        if (tokens.length < 5) {
                            throw new IllegalArgumentException("Строка не соответствует формату: " + line);
                        }

                        Optional<EquipmentType> findEquipmentTypeByIdResult = equipmentTypeFileRepository
                                .findEquipmentTypeById(Integer.parseInt(tokens[1]));
                        if (findEquipmentTypeByIdResult.isEmpty()) {
                            throw new IllegalArgumentException("Строка не соответствует формату: " + line);
                        }

                        int userId = Integer.parseInt(tokens[4]);
                        User user = userId == 0 ? null
                                : userFileRepository.findUserById(userId).orElse(null);

                        return new Equipment(
                                Integer.parseInt(tokens[0]),
                                findEquipmentTypeByIdResult.get(),
                                tokens[2].trim(),
                                Integer.parseInt(tokens[3]),
                                user);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException("Не удалось прочитать файл: " + path, e);
        }
    }

    //todo updateEquipment (Equipment equipment)
    //а в сервисе через findUserById получаешь Юзера,
    // обновляшь поля какие нужны, проверяешь на ошибки,
    // и в конце кидаешь этого юзера в updateUser(User user)

    public boolean updateEquipment(Equipment equipment) {
        List<Equipment> all = readFileRepositoryToList();

        boolean isEquipmentExist = all.stream().anyMatch(eq -> eq.getId() == equipment.getId());
        if (!isEquipmentExist) {
            return false;
        }

        List<Equipment> updated = all.stream()
                .map(eq -> eq.getId() == equipment.getId() ? equipment : eq)
                .collect(Collectors.toList());

        writeToFile(updated);
        return true;
    }

//    public boolean updateBrandName(int equipmentId, String brandName) {
//        Optional<Equipment> findEquipmentResult = findEquipmentById(equipmentId);
//        if (findEquipmentResult.isEmpty()) {
//            return false;
//        }
//
//        findEquipmentResult.get().setBrandName(brandName);
//        return true;
//    }
//
//    public boolean updateSerialNumber(int equipmentId, int serialNumber) {
//        Optional<Equipment> findEquipmentResult = findEquipmentById(equipmentId);
//        if (findEquipmentResult.isEmpty()) {
//            return false;
//        }
//
//        findEquipmentResult.get().setSerialNumber(serialNumber);
//        return true;
//    }
//
//    public boolean updateEquipmentType(int equipmentId, EquipmentType equipmentType) {
//        Optional<Equipment> findEquipmentResult = findEquipmentById(equipmentId);
//        if (findEquipmentResult.isEmpty()) {
//            return false;
//        }
//
//        findEquipmentResult.get().setEquipmentType(equipmentType);
//        return true;
//    }

    public boolean isSerialNumberExist(int serialNumber) {
        return readFileRepositoryToList().stream()
                .anyMatch(equipment -> (equipment.getSerialNumber() == serialNumber));
    }

    public List<Equipment> getEquipmentsList() {
        return readFileRepositoryToList();
    }

    //метод для UserMenu и User
    public List<Equipment> getUserEquipments(int userId) {
        return readFileRepositoryToList().stream()
                .filter(equipment -> equipment.getUser() != null && equipment.getUser().getId() == userId)
                .collect(Collectors.toList());
    }

    //метод для UserMenu и User
    public List<Equipment> getFreeEquipments() {
        return readFileRepositoryToList().stream()
                .filter((equipment -> equipment.getUser() == null))
                .collect(Collectors.toList());
    }

    public boolean assignEquipment(User user, int equipmentId) {
        List<Equipment> all = readFileRepositoryToList();
        if (user != null) {
            for (Equipment equipment : all) {
                if (equipment.getId() == equipmentId) {
                    equipment.setUser(user);
                    writeToFile(all);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean detachEquipment(int equipmentId) {
        List<Equipment> all = readFileRepositoryToList();

        for (Equipment equipment : all) {
            if (equipment.getId() == equipmentId) {
                equipment.setUser(null);
                writeToFile(all);
                return true;
            }
        }
        return false;
    }
}