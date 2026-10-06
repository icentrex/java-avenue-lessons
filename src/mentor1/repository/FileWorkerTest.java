package mentor1.repository;

import mentor1.model.Equipment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileWorkerTest {
    public static void main(String[] args) {
        Path equipment = Path
                .of("/Users/aleafoninser/IdeaProjects/group55/src/mentor1/repository/EquipmentRepository.txt");
        Path equipmentType = Path
                .of("/Users/aleafoninser/IdeaProjects/group55/src/mentor1/repository/EquipmentTypeRepository.txt");

        try {
            List<String> equipments = Files.readAllLines(equipment);
            equipments.forEach(System.out::println);

            List<String> equipmentTypes = Files.readAllLines(equipmentType);
            equipmentTypes.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
