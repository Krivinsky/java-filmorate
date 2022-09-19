package ru.yandex.practicum.filmorate.controller;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exeption.UserExeption;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    protected int generateId = 0;
    private final Map<Integer, User> users = new HashMap<>();
    private int generateId() {
        return ++generateId;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) throws UserExeption { //создание пользователя
        userValidate(user);
        user.setId(generateId());
        users.put(user.getId(), user);
        log.info("Создан новый пользователь");
        return user;
    }

    @PutMapping User update (@Valid @RequestBody User user) throws UserExeption { //обновление пользователя
        //userValidate(user);
        if (user.getId() > 0) {
            users.put(user.getId(), user);
            log.info("Обновлен пользователь " + user.getName());
            return user;
        }
        throw new UserExeption("Нет такого ID пользователя");
    }

    //получение списка всех пользователей
    @GetMapping public Collection<User> findAllUsers() {
        log.info("Отправлен список всех пользователей");
        return users.values();
    }

    private void userValidate(@NonNull User user) throws UserExeption {
        if (user.getEmail() == null || user.getEmail().isEmpty() || !user.getEmail().contains("@") ) {
            log.error("Ошибка в электронной почте пользователя");
            throw new UserExeption("Ошибка в электронной почте пользователя");
        }
        if (user.getLogin().isEmpty() || user.getLogin().contains(" ")) {
            log.error("Ошибка в логине пользователя");
            throw new UserExeption("Ошибка в логине пользователя");
        }
        if (user.getName() == null) {
            user.setName(user.getLogin());
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            log.error("Ошибка в дате рождения пользователя");
            throw new UserExeption("Ошибка в дате рождения пользователя");
        }


    }
}
