package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exeption.FilmException;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.exeption.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.GenreStorage;
import ru.yandex.practicum.filmorate.storage.LikesStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class FilmService {
    public FilmStorage filmStorage;
    public LikesStorage likesStorage;
    public UserStorage userStorage;
    public GenreStorage genreStorage;

    @Autowired
    public FilmService(FilmStorage filmStorage, UserStorage userStorage, GenreStorage genreStorage, LikesStorage likesStorage) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
        this.genreStorage = genreStorage;
        this.likesStorage = likesStorage;
    }

    public List<Film> topTenFilms(Integer count) {
        return filmStorage.topFilms(count);
    }

    public Film findById(int id) {
        return filmStorage.findById(id);
    }

    public List<Film> findAllFilms() {
        return filmStorage.getAllFilms();
    }

    public Film update(Film film) throws ValidationException, FilmException, NotFoundException {
        final Film film1 = filmStorage.get(film.getId());
        validate(film);
        film.setRate(film1.getRate());
        filmStorage.update(film);
        return film;
    }

    public Film add(Film film) throws ValidationException, FilmException {
        return filmStorage.add(film);
    }

    public void addLike(int filmId, int userId) throws NotFoundException {
        final Film film = filmStorage.get(filmId);
        final User user = userStorage.get(userId);
        likesStorage.addLike(film.getId(), user.getId());
    }

    public void deleteLike(int filmId, int userId) throws NotFoundException {
        final Film film = filmStorage.get(filmId);
        final User user = userStorage.get(userId);
        likesStorage.removeLike(film.getId(), user.getId());
    }

    public List<Film> getPopular(int count) {
        final List<Film> films = likesStorage.getPopular(count);
        genreStorage.load(films);
        return films;
    }

    public List<Film> getAllFilms() {
        final List<Film> films = filmStorage.getAllFilms();
        genreStorage.load(films);
        return films;
    }

    public Film save (Film film) throws NotFoundException {
        film.setRate(0);
        return filmStorage.save(film);
    }

    public Film get (int filmId) throws NotFoundException {
        final Film film = filmStorage.get(filmId);
        genreStorage.load(Collections.singletonList(film));
        return film;
    }

    protected void validate(Film film) { //todo этот метод

    }
}
