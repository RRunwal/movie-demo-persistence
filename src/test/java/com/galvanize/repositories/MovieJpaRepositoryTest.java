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
    void save() {
        Movie movie = new Movie("imdbid","directors","great title", new Date());
        movieJpaRepository.save(movie);
        assertNotNull(movie.getMovieId());
    }

    @Test
    void findById() {
        Movie movie = movieJpaRepository.findById(50l).get();
        assertEquals(50, movie.getMovieId());
    }

    @Test
    void findByImdbId() {
        Movie movie = movieJpaRepository.findByImdbid("tt1401642");
        assertNotNull(movie);
        assertEquals("tt1401642", movie.getImdbid());
    }
}