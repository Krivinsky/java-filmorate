package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exeption.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.inMemory.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.storage.inMemory.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FilmControllerTest {

    @Test
    void add() {
        Film film = new Film();
        film.setReleaseDate(LocalDate.of(1795,1,1));

        FilmStorage filmStorage = new InMemoryFilmStorage();
        UserStorage userStorage = new InMemoryUserStorage();
        FilmService filmService = new FilmService(filmStorage, userStorage);
        UserService userService = new UserService(userStorage);

        final ValidationException ex = assertThrows(
                ValidationException.class,
                () -> {
                    FilmController filmController = new FilmController(filmService, userService);
                    filmController.add(film);
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