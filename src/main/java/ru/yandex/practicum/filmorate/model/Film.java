package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Data
public class Film {

    int id;

    @NotBlank
    private String name;

    @Size(min = 1, max = 200)
    private String description;

    @NotNull
    LocalDate releaseDate;

    @Min(1)
    long duration;

    @JsonIgnore
    int rate;

    @JsonIgnore
    Set<Integer> likes = new HashSet<>(); //хранит userId пользователя лайк фильм

    //добавление лайка
    public void addLike(int userId) {
        likes.add(userId);
        rate = likes.size();
    }

    //удаление лайка
    public void deleteLike(Integer userId) {
        likes.remove(userId);
        rate = likes.size();
    }
}
