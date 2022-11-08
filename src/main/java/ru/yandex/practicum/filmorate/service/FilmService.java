package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exeption.FilmException;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.exeption.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.GenreStorage;
import ru.yandex.practicum.filmorate.storage.LikesStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class FilmService {
    public FilmStorage filmStorage;
    public LikesStorage likesStorage;
    public UserStorage userStorage;
    public GenreStorage genreStorage;

    final static int MAX_NAME_SIZE = 200;

    final static LocalDate DATE_OF_FIRST_FILM = LocalDate.of(1895,12,28);

    @Autowired
    public FilmService(FilmStorage filmStorage, UserStorage userStorage, GenreStorage genreStorage, LikesStorage likesStorage) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
        this.genreStorage = genreStorage;
        this.likesStorage = likesStorage;
    }

    public FilmService(FilmStorage filmStorage, UserStorage userStorage) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
    }

    public Film get (int filmId) throws NotFoundException {
        final Film film = filmStorage.get(filmId);
        genreStorage.load(Collections.singletonList(film));
        return film;
    }

    public List<Film> getAllFilms() {
        final List<Film> films = filmStorage.getAllFilms();
        genreStorage.load(films);
        return films;
    }

    public Film update(Film film) throws ValidationException, FilmException, NotFoundException {
        validate(film);
        final Film film1 = filmStorage.get(film.getId());
        film.setRate(film1.getRate());
        filmStorage.update(film);
        return get(film.getId());
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

    public Film save (Film film) throws NotFoundException, ValidationException {
        validate(film);
        film.setRate(0);
        return filmStorage.save(film);
    }

    public void validate(Film film) throws ValidationException {
        if (film.getReleaseDate().isBefore(DATE_OF_FIRST_FILM)) {
            throw new ValidationException("Ошибка в дате релиза фильма");
        }
        if (film.getName() == null || film.getName().isEmpty()) {
            throw new ValidationException("Ошибка в названии фильма");
        }
        if (film.getDescription() != null && film.getDescription().length() > MAX_NAME_SIZE) {
            throw new ValidationException("Ошибка в описании фильма");
        }
        if (film.getDuration() <= 0) {
            throw new ValidationException("Ошибка в продолжительности фильма");
        }
        final Mpa mpa = film.getMpa();
        if (mpa == null || mpa.getId() == 0) {
            throw new ValidationException("Ошибка в MPA-рейтинге фильма");
        }
    }
}
