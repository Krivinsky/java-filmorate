package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.*;


@Data
@AllArgsConstructor
@Builder
public class Film {

    Integer id;

    private String name;

    private String description;

    private LocalDate releaseDate;

    private int duration;

    private Integer rate;

    private final Set<Integer> likes = new HashSet<>();

    private Mpa mpa;

    private final LinkedHashSet<Genre> genres = new LinkedHashSet<>();

    public void addGenre(Genre genre) {
        genres.add(genre);
    }
}
