package com.example.demo.controller;



import com.example.demo.exceptions.DuplicateMovieIdException;
import com.example.demo.model.Movie;
import com.example.demo.service.AdminService;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin("*")
public class MovieController {
    @Autowired
    private AdminService adminService; // = new BookServiceImpl(); upcasting

    @Autowired
    private TicketService ticketService;

    //	@RolesAllowed({"admin"})
    @PostMapping("/addMovie")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) throws DuplicateMovieIdException {
        if (adminService.addMovie(movie) != null) {
            return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
        }

        return new ResponseEntity<String>("Movie is not created in DB", HttpStatus.CONFLICT);
    }

    @GetMapping("/getAllMovies")
    public List<Movie> getMovie(@RequestHeader("authorization") String bearerToken) {
        System.out.println(bearerToken);
        List<Movie> movielist = adminService.getAllMovie();
        if (movielist != null) {
            //return new ResponseEntity<List<Book>>(movielist, HttpStatus.OK);

            return movielist;

            //return ResponseHandler.generateResponse("Successfully fetched movielist", HttpStatus.OK, movielist);
        }

        return movielist;
    }

    @GetMapping("/movieById/{mid}")
    public ResponseEntity<?> getMovieById(@PathVariable("mid") int mid) {
        Movie movieexist = adminService.getMovieById(mid);

        if (movieexist != null) {
            return new ResponseEntity<Movie>(movieexist, HttpStatus.OK);

        }
        return new ResponseEntity<String>("Movie record missing", HttpStatus.NO_CONTENT);
    }

    //	@RolesAllowed("admin")
    @DeleteMapping("/delete/{mid}")
    public ResponseEntity<?> deleteMovie(@PathVariable("mid") int mid) {
        if (ticketService.deleteBooking(mid) && adminService.deleteMovie(mid)) {
            return new ResponseEntity<String>("Movie record deleted", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Movie record not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/updateMovie")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie) {
        if (adminService.updateMovie(movie)) {
            return new ResponseEntity<String>("Movie record updated", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("Movie record not updated", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}












