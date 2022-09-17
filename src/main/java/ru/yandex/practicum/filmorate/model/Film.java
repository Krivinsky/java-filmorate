package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Slf4j
@Data
public class Film {

    int id;
    @NotBlank
    String name;
    String description;
    LocalDate releaseDate;
    long duration;  //проверить формат
}
