package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.exeption.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Slf4j
//@Component
public class InMemoryUserStorage implements UserStorage {

    protected int generateId = 0;
    private final Map<Integer, User> users = new HashMap<>();
    private int generateId() {
        return ++generateId;
    }

    @Override
    public User creat(User user) throws ValidationException {
        userValidate(user);
        user.setUser_id(generateId());
        users.put(user.getUser_id(), user);
        return user;
    }

    @Override
    public User update(User user) throws ValidationException, NotFoundException {
        userValidate(user);
        if (user.getUser_id() > 0 && user.getUser_id() <= generateId) {
            users.put(user.getUser_id(), user);
            return user;
        }
        throw new NotFoundException("Такого пользователя не существует");
    }

    @Override
    public void deleteUser(Integer userId) {
        users.remove(userId);
    }

    @Override
    public Collection<User> findAllUsers() {
        return users.values();
    }

    @Override
    public void userValidate(User user) throws ValidationException {

            if (user.getEmail() == null || user.getEmail().isEmpty() || !user.getEmail().contains("@")) {
                log.error("Ошибка в электронной почте пользователя");
                throw new ValidationException("Ошибка в электронной почте пользователя");
            }
            if (user.getLogin().isEmpty() || user.getLogin().contains(" ")) {
                log.error("Ошибка в логине пользователя");
                throw new ValidationException("Ошибка в логине пользователя");
            }
            if (user.getName() == null || user.getName().isEmpty()) {
                user.setName(user.getLogin());
            }
            if (user.getBirthday().isAfter(LocalDate.now())) {
                log.error("Ошибка в дате рождения пользователя");
                throw new ValidationException("Ошибка в дате рождения пользователя");
            }
    }
    public User findById(int userId) throws NotFoundException {
        if (userId < 0 || generateId < userId) {
            throw new NotFoundException("Пользователь с " + userId + " не найден");
        }
        return users.get(userId);
    }

}
