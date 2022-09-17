package ru.yandex.practicum.filmorate.controller;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exeption.FilmExeption;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

    private final Map<Integer, Film> films = new HashMap<>();

    //добавление фильма
    @PostMapping
    public Film add(@RequestBody Film film) throws FilmExeption {
        filmValidate(film);
        film.setId(1);
        films.put(film.getId(), film);
        log.info("Добавлен новый фильм в коллекцию");
        return film;
    }

    //обновление фильма
    @PutMapping
    Film update(@RequestBody Film film) throws FilmExeption {
        filmValidate(film);
        if (film.getId() > 0) {
            films.put(film.getId(), film);
            log.info("Обновлен фильм " + film.getName());
            return film;
        }
        throw new FilmExeption("Ошибка Id фильма");
    }

    //получение всех фильмов
    @GetMapping
    public Collection<Film> findAllFilms() {
        log.info("Отправлен список всех фильмов");
        return films.values();
    }

    private void filmValidate(@NonNull Film film) throws FilmExeption {
        if (film.getName() == null || film.getName().isEmpty()) {
            log.error("Ошибка в названии фильма");
            throw new FilmExeption("Ошибка в названии фильма");
        }
        if (film.getDescription().length() > 200) {
            log.error("Слишком длинное описание фильма");
            throw new FilmExeption("Слишком длинное описание фильма");
        }
        if (film.getReleaseDate().isBefore(LocalDate.of(1895,12, 28))) {
            log.error("Ошибка в дате релиза фильма");
            throw new FilmExeption("Ошибка в дате релиза фильма");
        }
        if (film.getDuration() < 0) {
            log.error("Ошибка в продолжительности фильма");
            throw new FilmExeption("Ошибка в продолжительности фильма");
        }
    }
}
