package com.galvanize.repositories;

import com.galvanize.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieJpaRepository extends JpaRepository<Movie, Long> {
    Movie findByImdbid(String imdbId);
}
