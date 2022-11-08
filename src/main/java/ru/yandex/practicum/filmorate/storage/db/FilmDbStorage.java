package ru.yandex.practicum.filmorate.storage.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exeption.FilmException;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.exeption.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@Component
public class FilmDbStorage implements FilmStorage {

    private final JdbcTemplate jdbcTemplate;

    public FilmDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Film add(Film film) throws FilmException, ValidationException {
        return null;
    }

    @Override
    public void update(Film film) {
        String sqlQuery = "UPDATE FILMS SET TITLE = ?, DESCRIPTION = ?, RELEASE_DATE = ?, DURATION = ?, RATE = ?, MPA_ID = ? WHERE FILM_ID = ?";
        jdbcTemplate.update(sqlQuery, film.getName(), film.getDescription(), film.getReleaseDate(), film.getDuration(),
                film.getRate(), film.getMpa().getId(), film.getId());
        saveGenres(film);
    }

    @Override
    public void deleteFilm(Integer filmId) {
        String sqlQuery = "DELETE FROM FILMS WHERE FILM_ID = ?";
        jdbcTemplate.update(sqlQuery, filmId);
    }

    @Override
    public List<Film> getAllFilms() {
        String sqlQuery = "SELECT * FROM FILMS F, MPA M WHERE F.MPA_ID = M.MPA_ID";
        return jdbcTemplate.query(sqlQuery, FilmDbStorage::makeFilm);
    }

    static Film makeFilm(ResultSet resultSet, int rowNom) throws SQLException {
        return new Film(
                resultSet.getInt("FILM_ID"),
                resultSet.getString("TITLE"),
                resultSet.getString("DESCRIPTION"),
                resultSet.getDate("RELEASE_DATE").toLocalDate(),
                resultSet.getInt("DURATION"),
                resultSet.getInt("RATE"),
                new Mpa(resultSet.getInt("MPA_ID"), resultSet.getString("MPA_NAME"))
        );
    }

    @Override
    public Film get(int filmId) throws NotFoundException {
        String dqlQuery = "SELECT * FROM FILMS F, MPA M WHERE F.MPA_ID = M.MPA_ID AND F.FILM_ID = ?";
        final List<Film> films = jdbcTemplate.query(dqlQuery, FilmDbStorage::makeFilm, filmId);
        if (films.size() != 1) {
            throw new NotFoundException ("film_id = " + filmId);
        }
        Film film = films.get(0);
        return film;
    }

    @Override
    public List<Film> topFilms(int count) {
        return null;
    }

    public Film save(Film film) throws NotFoundException {
        String sqlQuery = "INSERT INTO FILMS (TITLE, DESCRIPTION, RELEASE_DATE, DURATION, RATE, MPA_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sqlQuery, new String[] {"FILM_ID"});
            statement.setString(1, film.getName());
            statement.setString(2, film.getDescription());
            statement.setDate(3, Date.valueOf(film.getReleaseDate()));
            statement.setInt(4,film.getDuration());
            statement.setInt(5, film.getRate());
            statement.setInt(6, film.getMpa().getId());
            return statement;
        }, keyHolder);
        film.setId(keyHolder.getKey().intValue());
        saveGenres(film);
        return get(film.getId());
    }

    private void saveGenres(Film film) {
        final Integer filmId = film.getId();
        jdbcTemplate.update("DELETE FROM FILM_GENRE WHERE FILM_ID = ?", filmId);
        final Set<Genre> genres = film.getGenres();

        if (genres == null || genres.isEmpty()) {
            return;
        }
        final ArrayList<Genre> genreArrayList = new ArrayList<>(genres);
        jdbcTemplate.batchUpdate("INSERT INTO FILM_GENRE(FILM_ID, GENRE_ID) values (?, ?)",
            new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setInt(1, filmId);
                    ps.setInt(2, genreArrayList.get(i).getId());
                }

                @Override
                public int getBatchSize() {
                        return genreArrayList.size();
                    }
            });
    }
}
