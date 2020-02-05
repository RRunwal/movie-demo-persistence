package com.galvanize.controllers;

import com.galvanize.entities.Movie;
import com.galvanize.repositories.MovieJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.List;

@RestController
public class MovieController {

    public static Logger LOGGER = LoggerFactory.getLogger(MovieJpaRepository.class);

    @Autowired
    MovieJpaRepository movieJpaRepository;

    @GetMapping("/movies")
    public List<Movie> getAllMovies(){
        return movieJpaRepository.findAll();
    }

    @GetMapping("/selected-movies")
    public List<Movie> getSelectedMovies(){
        return movieJpaRepository.findAllByActorsContainingAndReleasedAfter("Chris Pratt", new Date(1/1/2010));
    }

    @GetMapping("/selected-movies-2")
    public Movie getFirstMovie(){
        return movieJpaRepository.findFirstByActorsContainingAndReleasedAfter("Chris Pratt", new Date(1/1/2010));
    }

    @GetMapping("/selected-movies-by-title")
    public List<Movie> getSelectedMovieByTitle(){
        LOGGER.info("I'm in selected movies method");
        return movieJpaRepository.findAllByTitleContains("Star");
    }
}
