package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FriendshipStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    public FriendshipStorage friendshipStorage;
    public UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage, FriendshipStorage friendshipStorage) {
        this.userStorage = userStorage;
        this.friendshipStorage = friendshipStorage;
    }

    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    //добавление в друзья
    public void addFriend(int id, int friendId) throws NotFoundException {
        final User userId = findById(id);
        final User userFriendId = findById(friendId);

        friendshipStorage.addFriend(userId.getId(), userFriendId.getId());
        userId.getFriends().add(friendId);
        userFriendId.getFriends().add(id);
    }

    //удаление из друзей
    public void removeFriend(int id, int friendId) throws NotFoundException {
        final User userId = findById(id);
        final User userFriendId = findById(friendId);
        friendshipStorage.removeFriend(userId.getId(), userFriendId.getId());
    }

    //вывод списка общих друзей
    public List<User> getMutualFriends(int id, int otherId) throws NotFoundException {
        return friendshipStorage.getCommonFriends(id, otherId);
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
        return friendshipStorage.getFriends(user.getId());
    }
}
