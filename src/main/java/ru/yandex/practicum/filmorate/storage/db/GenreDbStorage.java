package ru.yandex.practicum.filmorate.storage.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.GenreStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class GenreDbStorage implements GenreStorage {
    private final JdbcTemplate jdbcTemplate;

    public GenreDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Genre get(int genreId) throws NotFoundException {
        final String sqlQuery = "SELECT * FROM GENRES WHERE GENRE_ID = ?";
        final List<Genre> result = jdbcTemplate.query(sqlQuery, GenreDbStorage::makeGenre, genreId);
        if (result.size() != 1) {
            throw new NotFoundException("genre_id = " + genreId);
        }
        return result.get(0);
    }

    @Override
    public List<Genre> getAll() {
        String sqlQuery = "SELECT  * FROM GENRES";
        List<Genre> genres = jdbcTemplate.query(sqlQuery, GenreDbStorage::makeGenre);
        return genres;
    }

    @Override
    public void load(List<Film> films) {
        String inSql = String.join(",", Collections.nCopies(films.size(), "?"));
        final Map<Integer, Film> filmById = films.stream().collect(Collectors.toMap(Film::getId, Function.identity()));
        final String sqlQuery = "SELECT * FROM GENRES G, FILM_GENRE FG WHERE FG.GENRE_ID = G.GENRE_ID AND FG.FILM_ID " +
                "IN (" + inSql + ")";

        jdbcTemplate.query(sqlQuery, (rs) -> {
            final Film film = filmById.get(rs.getInt("FILM_ID"));
            film.addGenre(makeGenre(rs, 0));
        }, films.stream().map(Film::getId).toArray());
    }

    static Genre makeGenre(ResultSet resultSet, int rowNum) throws SQLException {
        return new Genre(
                resultSet.getInt("GENRE_ID"),
                resultSet.getString("GENRE_NAME")
        );
    }
}

