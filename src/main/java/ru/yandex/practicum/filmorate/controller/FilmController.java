package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exeption.FilmExeption;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {
    final static LocalDate DATE_OF_FIRST_FILM = LocalDate.of(1895,12,28);
    private final Map<Integer, Film> films = new HashMap<>();
    protected int generateId = 0;
    private int generateId() {
        return ++generateId;
    }

    @PostMapping
    public Film add(@Valid @RequestBody Film film) throws FilmExeption { //добавление фильма
        filmValidate(film);
        film.setId(generateId());
        films.put(film.getId(), film);
        log.info("Добавлен новый фильм " + film.getName() + "в коллекцию");
        return film;
    }

    @PutMapping
    Film update(@Valid @RequestBody Film film) throws FilmExeption { //обновление фильма
        filmValidate(film);
        if (film.getId() > 0) {
            films.put(film.getId(), film);
            log.info("Обновлен фильм " + film.getName());
            return film;
        }
        throw new FilmExeption("Ошибка Id фильма");
    }

    @GetMapping
    public Collection<Film> findAllFilms() { //получение всех фильмов
        log.info("Отправлен список всех фильмов");
        return films.values();
    }

    private void filmValidate(Film film) throws FilmExeption {
//        if (film.getName() == null || film.getName().isEmpty()) {
//            log.error("Ошибка в названии фильма");
//            throw new FilmExeption("Ошибка в названии фильма");
//        }
//        if (film.getDescription().length() > 200) {
//            log.error("Слишком длинное описание фильма");
//            throw new FilmExeption("Слишком длинное описание фильма");
//        }
        if (film.getReleaseDate().isBefore(DATE_OF_FIRST_FILM)) {
            log.error("Ошибка в дате релиза фильма");
            throw new FilmExeption("Ошибка в дате релиза фильма");
        }
//        if (film.getDuration() < 0) {
//            log.error("Ошибка в продолжительности фильма");
//            throw new FilmExeption("Ошибка в продолжительности фильма");
//        }
    }
}
