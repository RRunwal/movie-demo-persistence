package com.galvanize.repositories;

import com.galvanize.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MovieJdbcRepository {
//    Start with this vvvvvvv
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//    ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

//    This is for when we implement save()
    JdbcTemplate jdbcTemplate;
    SimpleJdbcInsert insertMovie;

    @Autowired
    public MovieJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        //This is what gives us save functionality
        insertMovie = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("movies")
                .usingGeneratedKeyColumns("movie_id");
    }

    public List<Movie> findAll(){
        return jdbcTemplate.query("select * from movies",
                (rs, rowNum) -> new Movie(rs.getLong("movie_id"),
                                          rs.getString("imdbid"),
                                          rs.getString("actors"),
                                          rs.getString("director"),
                                          rs.getString("title"),
                                          rs.getString("year"),
                                          rs.getDate("released"))
                                  );
    }

    public boolean existsById(long id) {
        return jdbcTemplate.queryForObject(
                "select exists(select 1 from movies where movie_id = ?)", Boolean.class, id);
    }

    public Optional<Movie> findById(int movieId) {
        if(!existsById(movieId)) return Optional.empty();

        return Optional.ofNullable(
                jdbcTemplate.queryForObject("select * from movies where movie_id = ?",
                        (rs, rowNum) -> new Movie(rs.getLong("movie_id"),
                                rs.getString("imdbid"),
                                rs.getString("actors"),
                                rs.getString("director"),
                                rs.getString("title"),
                                rs.getString("year"),
                                rs.getDate("released")), movieId)
        );
    }

    //NOTE: need to add SimpleJdbcInsert to a constructor
    public Movie save(Movie movie) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("imdbid", movie.getImdbid());
        parameters.put("actors", movie.getActors());
        parameters.put("director", movie.getDirector());
        parameters.put("title", movie.getTitle());
        parameters.put("year", movie.getYear());
        parameters.put("released", movie.getReleased());
        Long newId = insertMovie.executeAndReturnKey(parameters).longValue();
        movie.setMovieId(newId);
        return movie;
    }
}
