package ru.yandex.practicum.filmorate.storage.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FriendshipStorage;

import java.util.List;

@Component
public class FriendshipDbStorage implements FriendshipStorage {
    private final JdbcTemplate jdbcTemplate;

    public FriendshipDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addFriend(int userId, int friendId) {
        String sqlQuery = "INSERT INTO FRIENDSHIP (USER_ID, FRIEND_ID) VALUES (?, ?)";
        jdbcTemplate.update(sqlQuery, userId, friendId);
    }

    @Override
    public void removeFriend(int userId, int friendId) {
        String sqlQuery = "DELETE FROM FRIENDSHIP where USER_ID = ? AND FRIEND_ID = ?";
        jdbcTemplate.update(sqlQuery, userId, friendId);
    }

    @Override
    public List<User> getFriends(int userId) {
        String sqlQuery = "SELECT * FROM USERS, FRIENDSHIP WHERE USERS.USER_ID = FRIENDSHIP.FRIEND_ID AND FRIENDSHIP.USER_ID = ?";
        List<User> result = jdbcTemplate.query(sqlQuery, UserDbStorage::makeUser, userId);
        System.out.println(result);
        return result;
    }

    @Override
    public List<User> getCommonFriends(int userId, int otherId) {
        String sqlQuery = "select * from USERS U, FRIENDSHIP F, FRIENDSHIP O " +
                "WHERE U.USER_ID = F.FRIEND_ID AND  U.USER_ID = O.FRIEND_ID AND F.USER_ID = ? AND O.USER_ID = ?";
        List<User> result = jdbcTemplate.query(sqlQuery, UserDbStorage::makeUser, userId, otherId);
        System.out.println(result);
        return result;
    }
}
