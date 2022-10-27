package ru.yandex.practicum.filmorate.storage.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exeption.FilmException;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.exeption.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.List;

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
    public Film update(Film film) throws FilmException, ValidationException, NotFoundException {
        return null;
    }

    @Override
    public void deleteFilm(Integer id) {

    }

    @Override
    public List<Film> findAllFilms() {
        return null;
    }

    @Override
    public void filmValidate(Film film) throws FilmException, ValidationException {

    }

    @Override
    public Film findById(int film_id) {
        SqlRowSet userRows = jdbcTemplate.queryForRowSet("select * from PUBLIC.FILMS where FILM_ID = ?", film_id);
        if (userRows.next()) {
            Film film = new Film(

            );
        }
        return null;
    }

    @Override
    public List<Film> topFilms(int count) {
        return null;
    }
}
