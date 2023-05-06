package com.example.demo.model;



import javax.persistence.*;
import java.util.List;

@Entity
public class Movie
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int movieId;
	@Column(unique = true)
	private String movieName;

	private int theatreCapacity=100;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="fk_movie_id", referencedColumnName = "movieId")
	private List<Theatre> theatreDetails;

	private int availableSeats=100;

	public Movie() {
	}

	public Movie(int movieId, String movieName, List<Theatre> theatreDetails) {
		this.movieId = movieId;
		this.movieName = movieName;
		this.theatreDetails = theatreDetails;
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

	public int getTheatreCapacity() {
		return theatreCapacity;
	}

	public void setTheatreCapacity(int theatreCapacity) {
		this.theatreCapacity = theatreCapacity;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
}
