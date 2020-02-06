package com.galvanize.repositories;

import com.galvanize.entities.Movie;
import com.galvanize.repositories.MovieJdbcRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MovieJdbcRepositoryTest {

    @Autowired
    MovieJdbcRepository repo;

    @Test
    void save() {
        Movie movie = new Movie("imdbid999","director", "The Best Movie Since Stripes", new Date());
        movie = repo.save(movie);
        assertNotNull(movie);
        assertNotNull(movie.getMovieId());
    }

    @Test
    void update() {
        Movie movie = repo.findById(50).get();
        Map<String, Object> parameters = new HashMap<>();
        String newImdbId = movie.getImdbid()+"-NEW";
        parameters.put("imdbid", newImdbId);
        repo.update(movie.getMovieId(), parameters);
        Movie movieNew = repo.findById(50).get();
        assertEquals(newImdbId, movieNew.getImdbid());
    }

    @Test
    void findAll() {
        List<Movie> movies = repo.findAll();
        movies.forEach(System.out::println);
        assertTrue(movies.size()>0);
    }

    @Test
    void exists() {
        assertTrue(repo.existsById(60));
        assertFalse(repo.existsById(99999));
    }

    @Test
    void findById() {
        Optional<Movie> oMovie = repo.findById(60);
        assertTrue(oMovie.isPresent());
    }
}