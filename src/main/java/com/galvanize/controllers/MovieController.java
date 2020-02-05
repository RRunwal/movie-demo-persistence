package com.galvanize.controllers;

import com.galvanize.entities.Movie;
import com.galvanize.repositories.MovieJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    MovieJpaRepository movieJpaRepository;

    @GetMapping("/movies")
    public List<Movie> getAllMovies(){
        return movieJpaRepository.findAll();
    }
}
