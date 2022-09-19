package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Slf4j
@Data
public class Film {

    int id;
    @NotBlank
    String name;
    @Size(min = 1, max = 200)
    String description;
    LocalDate releaseDate;
    @Min(1)
    long duration;
}
