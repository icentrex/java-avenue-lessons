package mentor1.repository;

import mentor1.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final Map<Integer, User> users = new HashMap<>(10);
    //норм название мапы?
    private final Map<String, Integer> phoneToUserId = new HashMap<>(10);
    private int nextId = 1;

    public void save(User user) throws RuntimeException {
        // Метод на проверку наличия дубликата
        // Если нет дубля сохранить в Map
        // если дубль есть выбросить исключение
        //проверка уже есть в сервисе, не дублирую
        users.put(nextId, user);
        phoneToUserId.put(user.getPhoneNumber(), nextId);
        nextId++;
    }

    public boolean existsByPhone(String phoneNumber) throws RuntimeException {
        return phoneToUserId.containsKey(phoneNumber);
    }

    //как верно реализовать?
//    public void deleteById(int userId) {
//        int index = -1;
//        for (int i = 0; i < users.size(); i++) {
//            if (users.get(i).getId() == userId) {
//                index = i;
//                break;
//            }
//        }

    // вынести в отдельный класс
//        if (index >= 0) {
//            users.remove(index);
//            System.out.printf("\nПользователь с id = %d удален", userId);
//        } else {
//            System.out.printf("\nПользователь c id = %d не найден", userId);
//        }
    //   }
//
//    public void infoById(int userId) {
//        for (User user : users) {
//            if (user.getId() == userId) {
//                System.out.println("\nИнформация о пользователе:");
//                System.out.println(user);
//                return;
//            }
//        }
//        System.out.println("\nПользователь не найден");
//    }
//
//    public void showAllUsers() {
//        System.out.println("\nСписок всех пользователей:");
//        for (User user : users) {
//            System.out.println(user);
//        }
//    }
}
