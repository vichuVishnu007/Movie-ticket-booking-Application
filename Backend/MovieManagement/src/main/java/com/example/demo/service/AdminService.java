package com.example.demo.service;



import com.example.demo.exceptions.DuplicateMovieIdException;
import com.example.demo.model.Movie;
import com.example.demo.model.Ticket;

import java.util.List;


public interface AdminService {
	
	public List<Movie> getAllMovie(); // public List<Movie> getAllMovies();
	
	public Movie addMovie(Movie movie) throws DuplicateMovieIdException;//public Movie addMovie(Movie movie);
	
	public boolean deleteMovie(int bid);
	
	public boolean updateMovie(Movie movie);
	
	public Movie getMovieById(int bid);



}
