package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exeption.FilmException;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.exeption.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;
    @Autowired
    public FilmController (FilmService filmService, UserService userService) {
        this.filmService = filmService;
    }

    @PostMapping
    public Film create(@Valid @RequestBody final Film film) throws FilmException, ValidationException, NotFoundException { //добавление фильма
        filmService.validate(film);
        filmService.save(film);
        log.info("Добавлен новый фильм " + film.getName() + " в коллекцию");
        return filmService.get(film.getId());
    }

    @PutMapping
    public Film update(@Valid @RequestBody final Film film) throws FilmException, ValidationException, NotFoundException { //обновление фильма
        filmService.update(film);
        log.info("Обновлен фильм " + film.getName());
        return film;
    }

    @GetMapping
    public List<Film> getAllFilms() { // получение всех фильмов
        List<Film> films = filmService.getAllFilms();
        log.info("Отправлен список всех фильмов");
        return films;
    }

    @GetMapping("/{id}")
    public Film get(@PathVariable Integer id) throws NotFoundException {
        Film film = filmService.get(id);
        if (Objects.nonNull(film)) {
            log.info("Получен фильм " + id);
            return film;
        } else {
            log.error("Фильм с ID - " + id + " не найден");
            throw new NotFoundException("Фильм с ID - " + id + " не найден");
        }
    }

    @PutMapping("/{id}/like/{userId}")    // пользователь ставит лайк фильму
    public void addLike (@PathVariable int id, @PathVariable int userId) throws NotFoundException {
        filmService.addLike(id, userId);
        log.info("пользователь с " + userId + "поставил лайк фильму " + id);
    }

    @DeleteMapping ("/{id}/like/{userId}")    //  пользователь удаляет лайк
    public void deleteLike(@PathVariable int id, @PathVariable int userId) throws NotFoundException {
        filmService.deleteLike(id, userId);
        log.info("пользователь с userID:" + userId + " удалил лайк у фильма c ID" + id);
    }

    @GetMapping ("/popular")  // возвращает список из первых count фильмов по количеству лайков.
    public List<Film> getPopularFilm(@RequestParam (defaultValue = "10") int count) {
        log.info("Получен список самых популярных фильмов" + count);
        return filmService.getPopular(count);
    }
}