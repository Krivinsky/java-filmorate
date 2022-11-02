package ru.yandex.practicum.filmorate.storage.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exeption.NotFoundException;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.MpaStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class MpaDbStorage implements MpaStorage {
    private final JdbcTemplate jdbcTemplate;

    public MpaDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Mpa get(int mpaId) throws NotFoundException {
        final String sqlQuery = "SELECT * FROM MPA WHERE MPA_ID = ?";
        final List<Mpa> mpas = jdbcTemplate.query(sqlQuery, MpaDbStorage::makeMpa, mpaId);
        if (mpas.size() != 1) {
            throw new NotFoundException("mpa_id = " + mpaId);
        }
        return mpas.get(0);
    }

    @Override
    public List<Mpa> getAll() {
        String sqlQuery = "SELECT * FROM MPA";
        return jdbcTemplate.query(sqlQuery, MpaDbStorage::makeMpa);
    }

    private static Mpa makeMpa(ResultSet resultSet, int rowNum) throws SQLException {
        return new Mpa(
                resultSet.getInt("MPA_ID"),
                resultSet.getString("MPA_NAME")
        );
    }
}
