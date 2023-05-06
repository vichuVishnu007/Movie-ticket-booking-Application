package com.example.demo.service;

import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TicketServiceImpl implements TicketService{


    @Autowired
    TicketRepository ticketRepository;
    @Override
    public Set<Ticket> getAllBooking(int movieId) {
        Set<Ticket> bookingDetails = ticketRepository.getBookingList(movieId);
        return bookingDetails;
    }

    @Override
    public boolean BookMovie(Ticket ticket) {

        Ticket ticket1 =new Ticket();
        ticket1.setMovie_id_fk(ticket.getMovie_id_fk());
        ticket1.setBookedSeats(ticket.getBookedSeats());
        ticket1.setIssuedAt(ticket.getIssuedAt());
        ticketRepository.saveAndFlush(ticket1);

        return true;
    }

    @Override
    public boolean deleteBooking(int movieId) {

         ticketRepository.deleteBooking(movieId);
        return true;

    }
}
