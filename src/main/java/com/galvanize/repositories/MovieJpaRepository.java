package com.galvanize.repositories;

import com.galvanize.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieJpaRepository extends JpaRepository<Movie, Long> {
    Movie findByImdbid(String imdbid);

    @Query(value = "SELECT * from movies m order by RAND() LIMIT ?1", nativeQuery = true)
    List<Movie> findRandomMovies(int qty);

}
