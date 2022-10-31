package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.service.MpaService;

import java.util.Collection;

@Slf4j
@RestController
public class MpaController {
    private final MpaService mpaService;

    @Autowired
    public MpaController(MpaService mpaService) {
        this.mpaService = mpaService;
    }

    @GetMapping ("/mpa")
    public Collection<Mpa> getAll() {
        log.info("Подучен список всех MPA");
        return mpaService.getAll();
    }

    @GetMapping("/mpa/{id}")
    public Mpa get (@PathVariable Integer id) throws NotFoundException {
        Mpa mpa = mpaService.get(id);
        log.info("Получен MPA " + mpa);
        return mpa;
    }
}
