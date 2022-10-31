package ru.yandex.practicum.filmorate.storage.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.exeption.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.sql.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class UserDbStorage implements UserStorage {

    private final JdbcTemplate jdbcTemplate;

    public UserDbStorage(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public void save(User user) throws ValidationException {
        userValidate(user);
        String sqlQuery = "INSERT INTO PUBLIC.USERS (EMAIL, LOGIN, NAME, BIRTHDAY) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(sqlQuery, new String[]{"USER_ID"});
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getName());
            final LocalDate birthday = user.getBirthday();
            if (birthday == null) {
                statement.setNull(4, Types.DATE);
            } else {
                statement.setDate(4, Date.valueOf(birthday));
            }
            return statement;
        }, keyHolder);
        user.setId(keyHolder.getKey().intValue());
    }

    @Override
    public User get(int userId) throws NotFoundException {
        final String sqlQuery = "SELECT *FROM PUBLIC.USERS WHERE USER_ID = ?";
        final List<User> users = jdbcTemplate.query(sqlQuery, UserDbStorage::makeUser, userId);
        if (users.size() != 1 ) {
            throw new NotFoundException ("user_id = " + userId);
        }
        return users.get(0);
    }

    @Override
    public User creat(User user) throws ValidationException {
        return null;
    }

        @Override
    public User update(User user) throws ValidationException, NotFoundException {
        userValidate(user);
        if (Objects.nonNull(findById(user.getId()))) {
            String sqlQuery = "UPDATE PUBLIC.USERS SET EMAIL = ?, LOGIN = ?, NAME = ?, BIRTHDAY = ? WHERE USER_ID = ?";
            jdbcTemplate.update(sqlQuery, user.getEmail(), user.getLogin(), user.getName(), user.getBirthday(), user.getId());
            return user;
        } else {
            throw new NotFoundException("Пользователь не найден");
        }
    }

    @Override
    public void deleteUser(Integer userId) {
        String sqlQuery = "DELETE FROM PUBLIC.USERS WHERE USER_ID = ?";
        jdbcTemplate.update(sqlQuery, userId);
    }

    @Override
    public Collection<User> getAllUsers() {

        return jdbcTemplate.query("SELECT * FROM PUBLIC.USERS", UserDbStorage::makeUser);
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

    public static User makeUser(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User(
            resultSet.getInt("USER_ID"),
            resultSet.getString("EMAIL"),
            resultSet.getString("LOGIN"),
            resultSet.getString("NAME"),
            resultSet.getDate("BIRTHDAY").toLocalDate()
        );
        System.out.println(user);
        return user;
    }

    @Override
    public void userValidate(User user) throws ValidationException {

        if (user.getEmail() == null || user.getEmail().isEmpty() || !user.getEmail().contains("@")) {
            log.error("Ошибка в электронной почте пользователя");
            throw new ValidationException("Ошибка в электронной почте пользователя");
        }
        if (user.getLogin().isEmpty() || user.getLogin().contains(" ")) {
            log.error("Ошибка в логине пользователя");
            throw new ValidationException("Ошибка в логине пользователя");
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            log.error("Ошибка в дате рождения пользователя");
            throw new ValidationException("Ошибка в дате рождения пользователя");
        }
    }
}
