package com.galvanize.controllers;

import com.galvanize.entities.Movie;
import com.galvanize.repositories.MovieJdbcRepository;
import com.galvanize.repositories.MovieJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {
    @Autowired
    MovieJpaRepository movieJpaRepository;

    @Autowired
    MovieJdbcRepository movieJdbcRepository;

    @GetMapping
    public List<Movie> getAllMovies(){
        return movieJpaRepository.findAll();
    }

    @GetMapping("/jdbc")
    public List<Movie> getAllJdbcMovies(){
        return movieJdbcRepository.findAll();
    }
}
