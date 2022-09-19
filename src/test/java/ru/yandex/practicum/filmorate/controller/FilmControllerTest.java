package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exeption.FilmExeption;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {

    @Test
    void add() {
        Film film = new Film();
        film.setReleaseDate(LocalDate.of(1795,1,1));

        final FilmExeption ex = assertThrows(
                FilmExeption.class,
                () -> {
                    FilmController filmController = new FilmController();
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