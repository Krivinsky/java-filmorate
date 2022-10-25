package ru.yandex.practicum.filmorate.dao.impl;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.UserDao;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao {

    @Override
    public Optional<User> findUserById(String id) {
        return Optional.empty();
    }
}
