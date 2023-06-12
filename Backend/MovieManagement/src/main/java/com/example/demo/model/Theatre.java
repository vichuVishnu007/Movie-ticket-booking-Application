package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@JsonInclude(JsonInclude.Include. NON_NULL)
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int TheatreId;

    @Column(unique = true)
    private String TheatreName;
    private double TicketPrice;

    private int availableSeats = 100;

    private int theatreCapacity = 100;

    public Theatre() {
    }

    public Theatre(int theatreId, String theatreName, double ticketPrice, int availableSeats, int theatreCapacity) {
        TheatreId = theatreId;
        TheatreName = theatreName;
        TicketPrice = ticketPrice;
        this.availableSeats = availableSeats;
        this.theatreCapacity = theatreCapacity;
    }

    public String getTheatreName() {
        return TheatreName;
    }

    public void setTheatreName(String theatreName) {
        TheatreName = theatreName;
    }

    public double getTicketPrice() {
        return TicketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        TicketPrice = ticketPrice;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getTheatreCapacity() {
        return theatreCapacity;
    }

    public void setTheatreCapacity(int theatreCapacity) {
        this.theatreCapacity = theatreCapacity;
    }
}
