package com.example.demo.controller;


import com.example.demo.model.Movie;
import com.example.demo.model.Theatre;
import com.example.demo.model.Ticket;
import com.example.demo.repository.TheatreRepository;
import com.example.demo.service.AdminService;
import com.example.demo.service.TheatreService;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ticket")
@CrossOrigin("*")
public class TicketController {

    @Autowired
    TicketService ts;

    @Autowired
    AdminService as;

    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    TheatreService theatreService;

    Movie movie = new Movie();


    @PostMapping("/add/{mid}")
    public ResponseEntity<?> BookTicket(@PathVariable("mid") int movieId, @RequestBody Ticket ticket)
    {


        Movie existMovie = as.getMovieById(movieId);

        Theatre theatre = theatreRepository.getTheatreByName(ticket.getTheatre());


        if(theatre.getAvailableSeats()!=0) {
            if (existMovie != null) {
                System.out.println(theatre.getAvailableSeats()+ticket.getUsername()+ticket.getMoviename());
                theatre.setAvailableSeats(theatre.getAvailableSeats() - ticket.getBookedSeats());
                ticket.setBookedSeats(ticket.getBookedSeats());
                ticket.setIssuedAt(java.time.LocalDateTime.now());
                ticket.setMovie_id_fk(ticket.getMovie_id_fk());
                ticket.setTheatre(theatre.getTheatreName());
                ticket.setUsername(ticket.getUsername());
                ticket.setMoviename(ticket.getMoviename());
                if (theatreService.updateTheatre(theatre) && ts.BookMovie(ticket)) {
                    return new ResponseEntity<Ticket>(ticket, HttpStatus.CREATED);
                }


            }
        }
        else{
            return new ResponseEntity<String>("Tickets sold out...", HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<String>("Oops....Booking failed!!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/getMyBookingByName/{username}")
    public List<Ticket> getMovieBookingByName(@PathVariable String username){
        return ts.getMovieBookingByName(username);
    }

    @DeleteMapping("/deleteBookingById/{transactionId}")
    public String DeleteBookingById(@PathVariable int transactionId){
        String status;
        System.out.println(transactionId);
        if(ts.deleteBookingByTransactionID(transactionId) == 1){
            status="Booking deleted...";
        }
        else {
            status="Oops..deletion failed";
        }
        return status;
    }
}
