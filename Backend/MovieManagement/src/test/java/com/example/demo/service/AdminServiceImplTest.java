package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.exceptions.DuplicateMovieIdException;
import com.example.demo.model.Movie;
import com.example.demo.model.Theatre;
import com.example.demo.repository.AdminRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AdminServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AdminServiceImplTest {
    @MockBean
    private AdminRepository adminRepository;

    @Autowired
    private AdminServiceImpl adminServiceImpl;

    /**
     * Method under test: {@link AdminServiceImpl#getAllMovie()}
     */
    @Test
    void testGetAllMovie() {
        when(adminRepository.findAll()).thenReturn(new ArrayList<>());
        assertNull(adminServiceImpl.getAllMovie());
        verify(adminRepository).findAll();
    }

    /**
     * Method under test: {@link AdminServiceImpl#getAllMovie()}
     */
    @Test
    void testGetAllMovie2() {
        Movie movie = new Movie();
        movie.setDescription("The characteristics of someone or something");
        movie.setMovieId(1);
        movie.setMovieName("Movie Name");
        movie.setTheatreDetails(new ArrayList<>());

        ArrayList<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        when(adminRepository.findAll()).thenReturn(movieList);
        List<Movie> actualAllMovie = adminServiceImpl.getAllMovie();
        assertSame(movieList, actualAllMovie);
        assertEquals(1, actualAllMovie.size());
        verify(adminRepository).findAll();
    }

    /**
     * Method under test: {@link AdminServiceImpl#addMovie(Movie)}
     */
    @Test
    void testAddMovie() throws DuplicateMovieIdException {
        Movie movie = new Movie();
        movie.setDescription("The characteristics of someone or something");
        movie.setMovieId(1);
        movie.setMovieName("Movie Name");
        movie.setTheatreDetails(new ArrayList<>());

        Movie movie2 = new Movie();
        movie2.setDescription("The characteristics of someone or something");
        movie2.setMovieId(1);
        movie2.setMovieName("Movie Name");
        movie2.setTheatreDetails(new ArrayList<>());
        Optional<Movie> ofResult = Optional.of(movie2);
        when(adminRepository.saveAndFlush(Mockito.<Movie>any())).thenReturn(movie);
        when(adminRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Movie movie3 = new Movie();
        movie3.setDescription("The characteristics of someone or something");
        movie3.setMovieId(1);
        movie3.setMovieName("Movie Name");
        movie3.setTheatreDetails(new ArrayList<>());
        assertThrows(DuplicateMovieIdException.class, () -> adminServiceImpl.addMovie(movie3));
        verify(adminRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#addMovie(Movie)}
     */
    @Test
    void testAddMovie2() throws DuplicateMovieIdException {
        Movie movie = new Movie();
        movie.setDescription("The characteristics of someone or something");
        movie.setMovieId(1);
        movie.setMovieName("Movie Name");
        movie.setTheatreDetails(new ArrayList<>());
        when(adminRepository.saveAndFlush(Mockito.<Movie>any())).thenReturn(movie);
        when(adminRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.empty());

        Movie movie2 = new Movie();
        movie2.setDescription("The characteristics of someone or something");
        movie2.setMovieId(1);
        movie2.setMovieName("Movie Name");
        movie2.setTheatreDetails(new ArrayList<>());
        assertSame(movie, adminServiceImpl.addMovie(movie2));
        verify(adminRepository).saveAndFlush(Mockito.<Movie>any());
        verify(adminRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#deleteMovie(int)}
     */
    @Test
    void testDeleteMovie() {
        doNothing().when(adminRepository).deleteById(Mockito.<Integer>any());
        assertTrue(adminServiceImpl.deleteMovie(1));
        verify(adminRepository).deleteById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateMovie(Movie)}
     */
    @Test
    void testUpdateMovie() {
        Movie movie = new Movie();
        movie.setDescription("The characteristics of someone or something");
        movie.setMovieId(1);
        movie.setMovieName("Movie Name");
        movie.setTheatreDetails(new ArrayList<>());

        Movie movie2 = new Movie();
        movie2.setDescription("The characteristics of someone or something");
        movie2.setMovieId(1);
        movie2.setMovieName("Movie Name");
        movie2.setTheatreDetails(new ArrayList<>());
        Optional<Movie> ofResult = Optional.of(movie2);

        Movie movie3 = new Movie();
        movie3.setDescription("The characteristics of someone or something");
        movie3.setMovieId(1);
        movie3.setMovieName("Movie Name");
        movie3.setTheatreDetails(new ArrayList<>());
        when(adminRepository.saveAndFlush(Mockito.<Movie>any())).thenReturn(movie3);
        when(adminRepository.getById(Mockito.<Integer>any())).thenReturn(movie);
        when(adminRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Movie movie4 = new Movie();
        movie4.setDescription("The characteristics of someone or something");
        movie4.setMovieId(1);
        movie4.setMovieName("Movie Name");
        movie4.setTheatreDetails(new ArrayList<>());
        assertTrue(adminServiceImpl.updateMovie(movie4));
        verify(adminRepository).getById(Mockito.<Integer>any());
        verify(adminRepository).saveAndFlush(Mockito.<Movie>any());
        verify(adminRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#updateMovie(Movie)}
     */
    @Test
    void testUpdateMovie2() {
        Movie movie = new Movie();
        movie.setDescription("The characteristics of someone or something");
        movie.setMovieId(1);
        movie.setMovieName("Movie Name");
        movie.setTheatreDetails(new ArrayList<>());
        Movie movie2 = mock(Movie.class);
        when(movie2.getMovieId()).thenReturn(-1);
        doNothing().when(movie2).setDescription(Mockito.<String>any());
        doNothing().when(movie2).setMovieId(anyInt());
        doNothing().when(movie2).setMovieName(Mockito.<String>any());
        doNothing().when(movie2).setTheatreDetails(Mockito.<List<Theatre>>any());
        movie2.setDescription("The characteristics of someone or something");
        movie2.setMovieId(1);
        movie2.setMovieName("Movie Name");
        movie2.setTheatreDetails(new ArrayList<>());
        Optional<Movie> ofResult = Optional.of(movie2);

        Movie movie3 = new Movie();
        movie3.setDescription("The characteristics of someone or something");
        movie3.setMovieId(1);
        movie3.setMovieName("Movie Name");
        movie3.setTheatreDetails(new ArrayList<>());
        when(adminRepository.saveAndFlush(Mockito.<Movie>any())).thenReturn(movie3);
        when(adminRepository.getById(Mockito.<Integer>any())).thenReturn(movie);
        when(adminRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Movie movie4 = new Movie();
        movie4.setDescription("The characteristics of someone or something");
        movie4.setMovieId(1);
        movie4.setMovieName("Movie Name");
        movie4.setTheatreDetails(new ArrayList<>());
        assertFalse(adminServiceImpl.updateMovie(movie4));
        verify(adminRepository).getById(Mockito.<Integer>any());
        verify(adminRepository).findById(Mockito.<Integer>any());
        verify(movie2).getMovieId();
        verify(movie2).setDescription(Mockito.<String>any());
        verify(movie2).setMovieId(anyInt());
        verify(movie2).setMovieName(Mockito.<String>any());
        verify(movie2).setTheatreDetails(Mockito.<List<Theatre>>any());
    }


    /**
     * Method under test: {@link AdminServiceImpl#getMovieById(int)}
     */
    @Test
    void testGetMovieById() {
        Movie movie = new Movie();
        movie.setDescription("The characteristics of someone or something");
        movie.setMovieId(1);
        movie.setMovieName("Movie Name");
        movie.setTheatreDetails(new ArrayList<>());
        Optional<Movie> ofResult = Optional.of(movie);
        when(adminRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        assertSame(movie, adminServiceImpl.getMovieById(1));
        verify(adminRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#getMovieById(int)}
     */
    @Test
    void testGetMovieById2() {
        when(adminRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.empty());
        assertNull(adminServiceImpl.getMovieById(1));
        verify(adminRepository).findById(Mockito.<Integer>any());
    }
}

