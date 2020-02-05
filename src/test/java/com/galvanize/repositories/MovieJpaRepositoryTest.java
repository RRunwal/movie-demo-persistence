package com.galvanize.repositories;

import com.galvanize.entities.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MovieJpaRepositoryTest {

    @Autowired
    MovieJpaRepository movieJpaRepository;

    @Test
    void findAll() {
        List<Movie> movies = movieJpaRepository.findAll();
        movies.forEach(System.out::println);
        assertTrue(movies.size()>0);
    }

    @Test
    void findById() {
        Movie movie = movieJpaRepository.findById(50l).get();
        assertEquals(50l, movie.getMovieId());
    }

    @Test
    void save() {
        Movie movie = new Movie("imdb12345", "director", "title", new Date());
        movie = movieJpaRepository.save(movie);
        assertNotNull(movie.getMovieId());
    }

    @Test
    void findByImDbId() {
        Movie movie = movieJpaRepository.findByImdbid("tt0084726");
        assertEquals("tt0084726", movie.getImdbid());
    }

    @Test
    void randomMovies() {
        List<Movie> randomMovies = movieJpaRepository.findRandomMovies(3);
        assertEquals(3, randomMovies.size());
    }
}