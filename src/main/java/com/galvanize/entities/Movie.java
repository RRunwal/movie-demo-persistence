package com.galvanize.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    private String imdbid;
    private String actors;
    private String director;
    private String title;
    private String year;
    private Date released;

    public Movie() {
    }

    public Movie(String imdbid, String director, String title, Date released) {
        this.imdbid = imdbid;
        this.director = director;
        this.title = title;
        this.released = released;
    }

    public Movie(Long movieId, String imdbid, String actors, String director, String title, String year, Date released) {
        this.movieId = movieId;
        this.imdbid = imdbid;
        this.actors = actors;
        this.director = director;
        this.title = title;
        this.year = year;
        this.released = released;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getImdbid() {
        return imdbid;
    }

    public void setImdbid(String imdbid) {
        this.imdbid = imdbid;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Date getReleased() {
        return released;
    }

    public void setReleased(Date released) {
        this.released = released;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", imdbid='" + imdbid + '\'' +
//                ", actors='" + actors + '\'' +
                ", director='" + director + '\'' +
                ", title='" + title + '\'' +
//                ", year='" + year + '\'' +
                ", released=" + released +
                '}';
    }
}
