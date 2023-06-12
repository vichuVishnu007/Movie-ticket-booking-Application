package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int movieId;
    @Column(unique = true)
    private String movieName;

    private String description;



    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_movie_id", referencedColumnName = "movieId")
    private List<Theatre> theatreDetails;



    public Movie() {
    }

    public Movie(int movieId, String movieName, List<Theatre> theatreDetails, String description) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.theatreDetails = theatreDetails;
        this.description = description;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }


    public List<Theatre> getTheatreDetails() {
        return theatreDetails;
    }

    public void setTheatreDetails(List<Theatre> theatreDetails) {
        this.theatreDetails = theatreDetails;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
