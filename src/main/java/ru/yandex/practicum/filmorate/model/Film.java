package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Film {

    int film_id;

    @NotBlank
    private String title;

    @Size(min = 1, max = 200)
    private String description;

    @NotNull
    LocalDate releaseDate;

    @Min(1)
    long duration;

    @JsonIgnore
    int rate;

    String mpa_rating;

    public Film() {

    }



    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getMpa_rating() {
        return mpa_rating;
    }

    public void setMpa_rating(String mpa_rating) {
        this.mpa_rating = mpa_rating;
    }

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
