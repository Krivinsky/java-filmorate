package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.List;

public interface MpaStorage {
    Mpa get(int mpaId) throws NotFoundException;

    List<Mpa> getAll();
}
