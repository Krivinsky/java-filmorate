package ru.yandex.practicum.filmorate.dao;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    User findById(int id);

    //добавление в друзья
    void addFriend(Integer id, Integer friendId);

    //удаление из друзей
    void deleteFriend(int userId, int friendId);

    //вывод списка общих друзей
    List<User> getMutualFriends(int id, int otherId);

    //получние друзей пользователя
    List<User> getFriends(int id);
}
