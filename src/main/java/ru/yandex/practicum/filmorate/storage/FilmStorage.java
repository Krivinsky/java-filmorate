package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.exeption.FilmException;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.exeption.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {
    Film add(Film film) throws FilmException, ValidationException;
    Film update(Film film) throws FilmException, ValidationException, NotFoundException;
    void deleteFilm(Integer id);
    List<Film> findAllFilms();
    void filmValidate(Film film) throws FilmException, ValidationException;

    Film findById(int id);

    List<Film> topFilms(int count);
}
