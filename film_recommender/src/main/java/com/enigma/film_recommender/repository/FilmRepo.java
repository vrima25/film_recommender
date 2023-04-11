package com.enigma.film_recommender.repository;

import com.enigma.film_recommender.model.Film;
import com.enigma.film_recommender.model.User;
import com.enigma.film_recommender.utils.AgeRating;
import com.enigma.film_recommender.utils.Genre;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("filmRepo")
public class FilmRepo implements IRepo<Film> {

    private JdbcTemplate jdbcTemplate;

    public FilmRepo(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final String SQL_INSERT_FILM = "INSERT INTO films(title, releaseDate, genre, ageRating, goers, rating) values(?,?,?,?,?,?)";
    private final String SQL_GET_ALL = "SELECT * FROM films";
    private final String SQL_FIND_BY_ID = "SELECT * FROM films WHERE id=?";
//    private final String SQL_UPDATE_USER = "UPDATE films SET name=? email=?";
    private final String SQL_DELETE_FILM = "DELETE FROM films where id=?";

    @Override
    public Film create(Film film) throws Exception {
        int result = jdbcTemplate.update(SQL_INSERT_FILM, film.getTitle(), film.getReleaseDate()
                ,film.getGenre(), film.getAgeRating(), film.getGoers(), film.getRating());
        if(result <= 0){
            throw new Exception("INSERT DATA FAILED");
        }
        return film;
    }

    @Override
    public List<Film> getAll() throws Exception {
        return jdbcTemplate.query(SQL_GET_ALL,
                new RowMapper<Film>() {
                    @Override
                    public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Film addFilm = new Film();
                        addFilm.setId(rs.getInt("id"));
                        addFilm.setTitle(rs.getString("name"));
                        addFilm.setReleaseDate(rs.getDate("releaseDate"));
                        addFilm.setGenre(Collections.singletonList(Genre.values()[rs.getRow()]));
                        addFilm.setAgeRating(AgeRating.values()[rs.getRow()]);
                        addFilm.setGoers(rs.getInt("goers"));
                        addFilm.setRating(rs.getDouble("rating"));
                        return addFilm;
                    }
                });
    }

    @Override
    public Optional<Film> getById(Integer id) throws Exception {
        return Optional.empty();
    }

    @Override
    public void update(Film params, Integer id) throws Exception {

    }

    @Override
    public void delete(Integer id) throws Exception {

    }
}