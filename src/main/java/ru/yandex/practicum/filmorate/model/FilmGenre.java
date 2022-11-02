package ru.yandex.practicum.filmorate.model;

public class FilmGenre {
    int film_id;
    int genre_id;

    public FilmGenre(int film_id, int genre_id) {
        this.film_id = film_id;
        this.genre_id = genre_id;
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }
}
