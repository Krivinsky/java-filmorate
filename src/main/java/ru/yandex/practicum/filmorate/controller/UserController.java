package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.exeption.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) throws ValidationException { //создание пользователя
        userService.userStorage.save(user);
        log.info("Создан новый пользователь" + user.toString());
        return user;
    }

    @PutMapping
    User update (@Valid @RequestBody User user) throws ValidationException, NotFoundException { //обновление пользователя
        userService.userStorage.update(user);
        if (Objects.nonNull(user)) {
            log.info("Обновлен пользователь " + user.toString());
            return user;
        }
        throw new ValidationException("Нет пользователя с таким ID");
    }

    @GetMapping
    public Collection<User> findAllUsers() {  //получение списка всех пользователей
        log.info("Отправлен список всех пользователей");
        return userService.userStorage.getAllUsers();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable int id) throws NotFoundException {
        User user = userService.findById(id);
        if (Objects.isNull(user)) {
            throw new NotFoundException("Нет пользователя с таким ID");
        }
        log.info("Получен пользователь : {} {} {}", user.getId(), user.getEmail(), user.getLogin());
        return user;
    }

    @PutMapping("/{id}/friends/{friendId}")  //добавление в друзья
    public void addFriend (@PathVariable int id, @PathVariable int friendId) throws NotFoundException {
        log.info("Пользователь " + id + " добавил в друзья пользователя " + friendId);
        userService.addFriend(id, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")   //удаление из друзей
    public void deleteFriend(@PathVariable int id, @PathVariable int friendId) throws NotFoundException {
        log.info("Пользователь " + id + "удалил из друзей ользователя " + friendId);
        userService.removeFriend(id, friendId);
    }

    @GetMapping("/{id}/friends")    //список пользователей, являющихся его друзьями
    public List<User> getFriends(@PathVariable int id) throws NotFoundException {
        List<User> friends = userService.getFriends(id);
        log.info("список друзей пользователя c ID=" + id + " : " + friends);
        return friends;
    }

    @GetMapping("/{id}/friends/common/{otherId}") // список друзей, общих с другим пользователем
    public List<User> getMutualFriends(@PathVariable int id, @PathVariable int otherId) throws NotFoundException {
        List<User> friends = userService.getMutualFriends(id, otherId);
        log.info("список друзей пользователя c ID=" + id + " с пользователем c ID=" + otherId + " -- " + friends);
        return friends;
    }
}
