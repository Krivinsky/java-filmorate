package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    public UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    //добавление в друзья
    public void addFriend(Integer id, Integer friendId) throws NotFoundException {
        User userId = findById(id);
        User userFriendId = findById(friendId);

        userId.getFriends().add(friendId);
        userFriendId.getFriends().add(id);
    }

    //удаление из друзей
    public void deleteFriend(int userId, int friendId) throws NotFoundException {
        userStorage.findById(userId).getFriends().remove(friendId);
        userStorage.findById(friendId).getFriends().remove(userId);
    }

    //вывод списка общих друзей
    public List<User> getMutualFriends(int id, int otherId) throws NotFoundException {
        List<User> result = new ArrayList<>();
        User user1 = userStorage.findById(id);
        User user2 = userStorage.findById(otherId);
        for (int someId : user1.getFriends()) {
            if (user2.getFriends().contains(someId)) {
                result.add(userStorage.findById(someId));
            }
        }
        return result;
    }

    public User findById(int userId) throws NotFoundException {
        if (userId < 0 ) {
            throw new NotFoundException("Пользователь с " + userId + " не найден");
        }
        User user = userStorage.findById(userId);
        if (Objects.isNull(user)) {
            throw new NotFoundException("Пользователь с " + userId + " не найден");
        }
        return user;
    }

    public List<User> getFriends(int id) throws NotFoundException {
        User user = userStorage.findById(id);
        ArrayList<User> friends = new ArrayList<>();
        for (Integer friendId : user.getFriends()) {
            friends.add(findById(friendId));
        }
        return friends;
    }
}
