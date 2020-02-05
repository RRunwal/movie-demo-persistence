package com.galvanize.repositories;

import com.galvanize.entities.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MovieRepositoryTest {
    @Autowired
    MovieJdbcRepository movieRepository;

    @Test
    void findAll() {
        List<Movie> movieList = movieRepository.findAll();
//        movieList.forEach(System.out::println);
        assertTrue(movieList.size()>0);
    }

    @Test
    void existsById() {
        assertTrue(movieRepository.existsById(50l));
        assertFalse(movieRepository.existsById(99999l));
    }

    @Test
    void findById() {
        Movie movie = movieRepository.findById(50).get();
        assertNotNull(movie);
        assertEquals(50, movie.getMovieId());
    }

    @Test
    void save() {
        Movie movie = new Movie("imdbid", "mr director", "title of the movie", new Date());
        movie = movieRepository.save(movie);
        assertNotNull(movie);
        assertNotNull(movie.getMovieId());
    }
}