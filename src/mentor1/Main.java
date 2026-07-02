package mentor1;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        userRepository.create("Петя", 1);
//        userRepository.create("Вася", 2);
//        userRepository.create("Гоша", 3);
//        userRepository.create("Петя", 1);
//        userRepository.showAllUsers();
//        userRepository.infoById(2);
//        userRepository.deleteById(3);

        EquipmentRepository equipmentRepository = new EquipmentRepository();
        equipmentRepository.add(new Monitor("Samsung", 1));
        equipmentRepository.add(new Mouse("Logitech", 2));
//        equipmentRepository.add(new Computer("Dell", 3));
//        equipmentRepository.showAll();

        equipmentRepository.assignTo(1,1);
        equipmentRepository.assignTo(2,1);

        equipmentRepository.showEquipmentListByUser(1);

    }
}
