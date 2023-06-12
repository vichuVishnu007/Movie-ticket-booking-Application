package com.example.demo.service;


import com.example.demo.exceptions.DuplicateMovieIdException;
import com.example.demo.model.Movie;
import com.example.demo.model.Ticket;
import com.example.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService
{

	@Autowired
	private AdminRepository movieRepo;

	@Override
	public List<Movie> getAllMovie() {

		List<Movie> movielist = movieRepo.findAll();

		if(movielist !=null && movielist.size()>0) {
			return movielist;
		}
		return null;
	}

	@Override
	public Movie addMovie(Movie movie) throws DuplicateMovieIdException {
		Optional<Movie> mvObj = movieRepo.findById(movie.getMovieId());

		if(mvObj.isPresent()) {
			System.out.println(mvObj);
			throw new DuplicateMovieIdException();
		}
		return movieRepo.saveAndFlush(movie);
	}

	@Override
	public boolean deleteMovie(int mid) {
		movieRepo.deleteById(mid);
		return true;
	}

	@Override
	public boolean updateMovie(Movie movie) {
		Movie movie1 = movieRepo.getById(movie.getMovieId());
		if(movie !=null && movieRepo.findById(movie.getMovieId()).get().getMovieId() == movie.getMovieId()) {
//			movie1.setTicketPrice(movie.getTicketPrice());
			movieRepo.saveAndFlush(movie1);
			return true;
		} else if(movie!=null){
			return false;
		}
		return false;
	}

	@Override
	public Movie getMovieById(int mid) {
		Optional<Movie> movie = movieRepo.findById(mid);
		if(movie.isPresent()) {
			return movie.get();
		}
		return null;
	}


}




