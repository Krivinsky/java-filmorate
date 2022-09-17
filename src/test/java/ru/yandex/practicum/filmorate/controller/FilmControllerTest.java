package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exeption.FilmExeption;
import ru.yandex.practicum.filmorate.model.Film;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {

    @Test
    void add() {
        Film film = new Film();

        final FilmExeption ex = assertThrows(
                FilmExeption.class,
                () -> {
                    FilmController filmController = new FilmController();
                    filmController.add(film);
                });
        assertEquals("Ошибка в названии фильма", ex.getMessage());
    }

    @Test
    void update() {
    }

    @Test
    void findAllFilms() {
    }
}