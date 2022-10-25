package ru.yandex.practicum.filmorate.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dao.UserDao;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public User findById(int id) {

        return User;
    }

    //добавление в друзья
    public void addFriend(Integer id, Integer friendId) { //todo

    }

    //удаление из друзей
    public void deleteFriend(int userId, int friendId) { //todo

    }

    //вывод списка общих друзей
    public List<User> getMutualFriends(int id, int otherId) { //todo
        List<User> result = new ArrayList<>();
        return result;
    }

    //получние друзей пользователя
    public List<User> getFriends(int id) { //todo
        ArrayList<User> friends = new ArrayList<>();
        return friends;
    }
}
