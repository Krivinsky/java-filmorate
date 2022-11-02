package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.exeption.UserException;
import ru.yandex.practicum.filmorate.exeption.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;

public interface UserStorage {
    void save(User user) throws ValidationException;

    User get(int userId) throws NotFoundException;

    User creat(User user) throws  ValidationException;

    User update(User user) throws ValidationException, NotFoundException;
    void deleteUser(Integer userId);
    Collection<User> getAllUsers();
    void userValidate(User user) throws UserException, ValidationException;
    User findById(int userId) throws NotFoundException;
}
