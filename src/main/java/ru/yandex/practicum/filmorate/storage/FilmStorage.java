package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.exeption.FilmException;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.exeption.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {
    Film add(Film film) throws FilmException, ValidationException;
    void update(Film film) throws FilmException, ValidationException, NotFoundException;
    void deleteFilm(Integer id);
    List<Film> getAllFilms();

    Film get(int filmId) throws NotFoundException;

    List<Film> topFilms(int count);

    Film save(Film film) throws NotFoundException;
}
