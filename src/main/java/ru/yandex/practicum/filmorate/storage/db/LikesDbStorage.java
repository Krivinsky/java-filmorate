package ru.yandex.practicum.filmorate.storage.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.LikesStorage;

import java.util.List;

@Component
public class LikesDbStorage implements LikesStorage {
    private final JdbcTemplate jdbcTemplate;

    public LikesDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addLike(int filmId, int userId) {
        String sqlQuery = "INSERT INTO LIKES (FILM_ID, USER_ID) VALUES (?, ?)";
        jdbcTemplate.update(sqlQuery, filmId, userId);
        updateRate(filmId);
    }

    @Override
    public void removeLike(int filmId, int userId) {
        String sqlQuery = "DELETE FROM LIKES WHERE FILM_ID = ? AND USER_ID = ?";
        jdbcTemplate.update(sqlQuery, filmId, userId);
        updateRate(filmId);
    }

    @Override
    public List<Film> getPopular(int count) {
        final String sqlQuery = "SELECT * FROM FILMS F, MPA M WHERE F.MPA_ID = M.MPA_ID ORDER BY RATE DESC LIMIT ?";
        List<Film> films = jdbcTemplate.query(sqlQuery, FilmDbStorage::makeFilm, count);
        return films;
    }

    public void updateRate(int filmId) {
        String sqlQuery = "UPDATE FILMS F SET  RATE = (SELECT COUNT(L.USER_ID) FROM LIKES L " +
                "WHERE L.FILM_ID = F.FILM_ID) WHERE F.FILM_ID = ?";
        jdbcTemplate.update(sqlQuery, filmId);
    }
}
