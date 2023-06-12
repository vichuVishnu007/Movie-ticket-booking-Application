package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@JsonInclude(JsonInclude.Include. NON_NULL)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int TransactionId;

    private String theatre;

    @NotNull
    private int BookedSeats; //mandatory

    private LocalDateTime issuedAt;

    private String username;

    private int movie_id_fk;

    private String moviename;
    public int getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(int transactionId) {
        TransactionId = transactionId;
    }

    public int getBookedSeats() {
        return BookedSeats;
    }

    public void setBookedSeats(int bookedSeats) {
        BookedSeats = bookedSeats;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    public int getMovie_id_fk() {
        return movie_id_fk;
    }

    public void setMovie_id_fk(int movie_id_fk) {
        this.movie_id_fk = movie_id_fk;
    }

    public String getTheatre() {
        return theatre;
    }

    public void setTheatre(String theatre) {
        this.theatre = theatre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }
}
