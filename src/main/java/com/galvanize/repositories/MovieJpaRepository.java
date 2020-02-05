package com.galvanize.repositories;

import com.galvanize.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovieJpaRepository extends JpaRepository<Movie, Long> {
    Movie findByImdbid(String imdbid);

    //finding all movies (list) for an actor after a specific release date
    List<Movie> findAllByActorsContainingAndReleasedAfter(String actor, Date released);
    //finding first movie (single movie) for an actor after a specific release date
    Movie findFirstByActorsContainingAndReleasedAfter(String actor, Date released);
    //find using like
    List<Movie> findAllByTitleContains(String title);

    @Query(value = "SELECT * from movies m order by RAND() LIMIT ?1", nativeQuery = true)
    List<Movie> findRandomMovies(int qty);

}
