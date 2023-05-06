package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int TheatreId;
    @Column(unique = true)
    private String TheatreName;
    private double TicketPrice;

    public Theatre() {
    }

    public Theatre(String theatreName, double ticketPrice) {
        TheatreName = theatreName;
        TicketPrice = ticketPrice;
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
}
