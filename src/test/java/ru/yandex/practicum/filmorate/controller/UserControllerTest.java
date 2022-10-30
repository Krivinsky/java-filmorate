package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.yandex.practicum.filmorate.exeption.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.FriendshipStorage;
import ru.yandex.practicum.filmorate.storage.db.FriendshipDbStorage;
import ru.yandex.practicum.filmorate.storage.inMemory.InMemoryUserStorage;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserControllerTest {

    @Test
    void create() {
        User user = new User();
        user.setId(1);
        user.setLogin("login");
        user.setName("name");
        user.setBirthday(LocalDate.of(2000, 1,1));

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        InMemoryUserStorage inMemoryUserStorage = new InMemoryUserStorage();
        FriendshipStorage friendshipStorage = new FriendshipDbStorage(jdbcTemplate);
        UserService userService = new UserService(inMemoryUserStorage, friendshipStorage);

        final ValidationException ex = assertThrows(
                ValidationException.class,
                () -> {
                    UserController userController = new UserController(userService);
                    userController.create(user);
                });
        assertEquals("Ошибка в электронной почте пользователя", ex.getMessage());
    }

    @Test
    void update() {
    }

    @Test
    void findAllUsers() {
    }
}