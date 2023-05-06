package com.example.demo.controller;

import com.example.demo.model.Movie;
import com.example.demo.model.Theatre;
import com.example.demo.model.Ticket;
import com.example.demo.service.AdminService;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ticket")
public class TicketController {

    @Autowired
    TicketService ts;

    @Autowired
    AdminService as;

    Movie movie = new Movie();


    @PostMapping("/add/{mid}")
    public ResponseEntity<?> BookTicket(@PathVariable("mid") int movieId, @RequestBody Ticket ticket)
    {


        Movie existMovie = as.getMovieById(movieId);


        if(existMovie !=null)
        {
            existMovie.setAvailableSeats(existMovie.getAvailableSeats()-ticket.getBookedSeats());// updating it in Book table --> PUT
            ticket.setBookedSeats(ticket.getBookedSeats());
            ticket.setIssuedAt(java.time.LocalDateTime.now());
            ticket.setMovie_id_fk(ticket.getMovie_id_fk());
            if(as.updateMovie(existMovie) && ts.BookMovie(ticket))
            {
                return new ResponseEntity<Ticket>(ticket, HttpStatus.CREATED);
            }


        }

        return new ResponseEntity<String>("Oops....Booking failed!!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
