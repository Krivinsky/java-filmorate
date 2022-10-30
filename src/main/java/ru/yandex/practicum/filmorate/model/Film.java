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
    int duration;

    @JsonIgnore
    int rate;

    Mpa mpa;

    public Set<Genre> genres;

    public Film() {
    }

    public Film(int film_id, String title, String description, LocalDate releaseDate, int duration, int rate, Mpa mpa) {
        this.film_id = film_id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.rate = rate;
        this.mpa = mpa;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Mpa getMpa() {
        return mpa;
    }



    public void setMpa(Mpa mpa) {
        this.mpa = mpa;
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

    public Set<Genre> getGenres() {
        return genres;
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }
}
