package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.yandex.practicum.filmorate.exeption.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.*;
import ru.yandex.practicum.filmorate.storage.db.FilmDbStorage;
import ru.yandex.practicum.filmorate.storage.db.FriendshipDbStorage;
import ru.yandex.practicum.filmorate.storage.db.GenreDbStorage;
import ru.yandex.practicum.filmorate.storage.db.LikesDbStorage;
import ru.yandex.practicum.filmorate.storage.inMemory.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.storage.inMemory.InMemoryUserStorage;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FilmControllerTest {

    @Test
    void add() {
        Film film = new Film();
        film.setReleaseDate(LocalDate.of(1795,1,1));

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        FilmStorage filmStorage = new FilmDbStorage(jdbcTemplate);
        UserStorage userStorage = new InMemoryUserStorage();
        GenreStorage genreStorage = new GenreDbStorage(jdbcTemplate);
        LikesStorage likesStorage = new LikesDbStorage(jdbcTemplate);
        FilmService filmService = new FilmService(filmStorage, userStorage, genreStorage, likesStorage);

        final ValidationException ex = assertThrows(
                ValidationException.class,
                () -> {
                    FilmController filmController = new FilmController(filmService);
                    filmController.create(film);
                });
        assertEquals("Ошибка в дате релиза фильма", ex.getMessage());
    }

    @Test
    void update() {
    }

    @Test
    void findAllFilms() {
    }
}