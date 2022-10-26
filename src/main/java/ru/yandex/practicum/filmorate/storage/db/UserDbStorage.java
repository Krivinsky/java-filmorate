package ru.yandex.practicum.filmorate.storage.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.exeption.UserException;
import ru.yandex.practicum.filmorate.exeption.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.Collection;
import java.util.Objects;

@Slf4j
@Component
public class UserDbStorage implements UserStorage {

    private final JdbcTemplate jdbcTemplate;

    public UserDbStorage(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public User creat(User user) throws ValidationException {
        return null;
    }

    @Override
    public User update(User user) throws ValidationException, NotFoundException {
        return null;
    }

    @Override
    public void deleteUser(Integer userId) {

    }

    @Override
    public Collection<User> findAllUsers() {
        return null;
    }

    @Override
    public void userValidate(User user) throws UserException, ValidationException {

    }

    @Override
    public User findById(int userId) throws NotFoundException {
        SqlRowSet userRows = jdbcTemplate.queryForRowSet("select * from PUBLIC.USERS where USER_ID = ?", userId);
        if (userRows.next()) {
            log.info("Найден пользователь: {} {}", userRows.getInt("user_id"), userRows.getString("login"));

            User user = new User(
                    userRows.getInt("user_id"),
                    userRows.getString("email"),
                    userRows.getString("login"),
                    userRows.getString("name"),
                    Objects.requireNonNull(userRows.getDate("birthday")).toLocalDate());
            return user;
        } else {
            log.info("Пользователь с идентификатором {} не найден.", userId);
            return null;
        }
    }
}
