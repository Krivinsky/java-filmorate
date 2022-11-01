package ru.yandex.practicum.filmorate.storage.inMemory;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.exeption.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.time.LocalDate;
import java.util.*;

@Slf4j

public class InMemoryFilmStorage implements FilmStorage {
    final static int MAX_NAME_SIZE = 200;

    final static LocalDate DATE_OF_FIRST_FILM = LocalDate.of(1895,12,28);
    private final Map<Integer, Film> films = new HashMap<>();
    protected int generateId = 0;

    private int generateId() {
        return ++generateId;
    }

    @Override
    public Film add(Film film) throws ValidationException {
        filmValidate(film);
        film.setId(generateId());
        films.put(film.getId(), film);
        return film;
    }

    public void update(Film film) throws ValidationException, NotFoundException {
        filmValidate(film);
        if (film.getId() > 0 && film.getId() <= generateId) {
            films.put(film.getId(), film);
        }
        throw new NotFoundException("Такого фильма не существует");
    }

    @Override
    public void deleteFilm(Integer id) {
        films.remove(id);
    }

    @Override
    public List<Film> getAllFilms() {
        return new ArrayList<>(films.values());
    }

    public void filmValidate(Film film) throws ValidationException {
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
    }


    public Film findById(int id) {
        return films.get(id);
    }

    @Override
    public Film get(int filmId) throws NotFoundException {
        return null;
    }

    public List<Film> topFilms(int count) {
        Collection<Film> allFilmsCollection = films.values();
        List<Film> allFilms = new ArrayList<>(allFilmsCollection);

        FilmComparator filmComparator = new FilmComparator();
        allFilms.sort(filmComparator);

        return allFilms;
    }

    @Override
    public Film save(Film film) {
        return null;
    }

    static class FilmComparator implements Comparator<Film> {
        @Override
        public int compare (Film film1, Film film2) {
            return film2.getRate()  - film1.getRate();
        }
    }
}
