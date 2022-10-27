package ru.yandex.practicum.filmorate.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exeption.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.inMemory.InMemoryFilmStorage;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryFilmStorageTest {

    Film film1 = new Film();

    Film film2 = new Film();

    InMemoryFilmStorage inMemoryFilmStorage = new InMemoryFilmStorage();


    @BeforeEach
    void setUp() {
        film1.setFilm_id(1);
        film1.setName("film1 Name");
        film1.setReleaseDate(LocalDate.of(2001,1,1));
        film1.setDescription("film1 Description");
        film1.setDuration(100);

        film2.setFilm_id(2);
        film2.setName("film2 Name");
        film2.setReleaseDate(LocalDate.of(2002,2,2));
        film2.setDescription("film12 Description");
        film2.setDuration(200);
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteFilm() {
    }

    @Test
    void findAllFilms() {
    }

    @Test
    void filmValidate() {
    }

    @Test
    void findById() {
    }

    @Test
    void topFilms() throws ValidationException {
        film2.addLike(1);
        film2.addLike(2);
        film1.addLike(3);
        inMemoryFilmStorage.add(film1);
        inMemoryFilmStorage.add(film2);

        List<Film> films = inMemoryFilmStorage.topFilms(10);

        assertEquals(film2, films.get(0));
        assertEquals(film1, films.get(1));
    }
}