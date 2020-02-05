package com.galvanize.repositories;

import com.galvanize.entities.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public static Logger LOGGER = LoggerFactory.getLogger(MovieJpaRepository.class);

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertMovie;

    @Autowired
    public MovieJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        //This is what gives us save functionality
        insertMovie = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("movies")
                .usingGeneratedKeyColumns("movie_id");
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

    public Optional<Movie> findById(long movieId) {
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

    //Update
    public void update(Long id, Map<String, Object> parameters) {
        String sql = "update movies set";
        Optional<Movie> officer = findById(id);
        if (!officer.isPresent()) {
            LOGGER.error("Movie with id " + id + " not found!");
//            throw new RuntimeException("Movie with id " + id + " not found!");
        }else if(parameters.isEmpty()){
            LOGGER.error("No changes provided: "+parameters);
//            throw new RuntimeException("No changes provided: "+parameters);
        }else{
            StringBuilder paras = new StringBuilder();
            for (String key : parameters.keySet()){
                if (paras.length() != 0) paras.append(", ");
                paras.append(" `"+key+"` = '"+parameters.get(key)+"' ");
                LOGGER.debug("Appended: "+" `"+key+"` = '"+parameters.get(key)+"' ");
            }

            String update = sql+paras+" where movie_id = ?";
            System.out.println("update sql: "+update);
            LOGGER.info("Update SQL: "+ update);

            jdbcTemplate.update(update, id);
        }

    }


}
