package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exeption.FilmException;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.exeption.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
//@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;
    private final UserService userService;
    @Autowired
    public FilmController (FilmService filmService, UserService userService) {
        this.filmService = filmService;
        this.userService = userService;
    }

    @PostMapping("/films")
    public Film add(@Valid @RequestBody final Film film) throws FilmException, ValidationException { //добавление фильма
        filmService.filmStorage.add(film);
        log.info("Добавлен новый фильм " + film.getName() + " в коллекцию");
        return film;
    }

    @PutMapping("/films")
    public Film update(@Valid @RequestBody final Film film) throws FilmException, ValidationException, NotFoundException { //обновление фильма
        filmService.filmStorage.update(film);
        log.info("Обновлен фильм " + film.getName());
        return film;
    }

    @GetMapping("/films")
    public List<Film> findAllFilms() { // получение всех фильмов
        log.info("Отправлен список всех фильмов");
        return filmService.filmStorage.findAllFilms();
    }

    @GetMapping("/films/{id}")
    public Film get(@PathVariable Integer id) throws NotFoundException {
        Film film = filmService.filmStorage.findById(id);
        if (Objects.nonNull(film)) {
            log.info("Получен фильм " + id);
            return film;
        } else {
            log.error("Фильм с ID - " + id + " не найден");
            throw new NotFoundException("Фильм с ID - " + id + " не найден");
        }
    }

    @PutMapping("/films/{id}/like/{userId}")    // пользователь ставит лайк фильму
    public void addLike (@PathVariable int id, @PathVariable int userId) {
        Film film = filmService.filmStorage.findById(id);
        log.info("пользователь с " + userId + "поставил лайк фильму " + id);
        film.addLike(userId);
    }

    @DeleteMapping ("/films/{id}/like/{userId}")    //  пользователь удаляет лайк
    public void deleteLike(@PathVariable int id, @PathVariable int userId) throws NotFoundException {
        Film film = filmService.filmStorage.findById(id);
        if (Objects.isNull(film)) {
            throw new NotFoundException("Такого фильма не существует");
        }
        User user = userService.findById(userId);
        if (Objects.isNull(user)) {
            throw new NotFoundException("Такого пользователя не существует");
        }
        log.info("пользователь с userID:" + userId + " удалил лайк у фильма c ID" + id);
        film.deleteLike(userId);
    }

    @GetMapping ("/films/popular")  // возвращает список из первых count фильмов по количеству лайков.
    public List<Film> topFilm(@RequestParam (defaultValue = "10") int count) {
        List<Film> topFilm = filmService.topTenFilms(count);
        if (count < topFilm.size()) {
            return topFilm.subList(0,count);
        }
        log.info("Получен список самых популярных фильмов" + topFilm);
        return topFilm;
    }
}