package mentor1.service;

import mentor1.Cursoring;
import mentor1.model.User;
import mentor1.repository.UserRepository;

public class UserService implements Cursoring {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //норм название?
    //Зачем нам возвращаемый тип Юзер? Подумать
    public void create(String name, String phoneNumber) {
        //зачем нам именно здесь создавать пользователя а не в юзеррепозитори?
        //Это бизнес-логика: здесь могут быть проверки на заполнение полей юзера. То есть здесь уже готовый объект
        //А в репозитории "тупая" хранилка уже готового объекта, она не должна знать ничего про его логику
        if (userRepository.existsByPhone(phoneNumber)) {
            System.out.println("Пользователь с таким телефоном уже существует!");
        }
        userRepository.save(new User(name, phoneNumber));
    }

    @Override
    public String getInfo() {
        return "";
    }

    @Override
    public String getCommands() {
        return "";
    }

    @Override
    public String execute(String commandNumber) {
        return null;
    }
}
