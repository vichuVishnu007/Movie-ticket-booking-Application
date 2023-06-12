package com.example.demo.controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.example.demo.exceptions.DuplicateMovieIdException;
import com.example.demo.model.Movie;
import com.example.demo.service.AdminService;
import com.example.demo.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {MovieController.class})
@ExtendWith(SpringExtension.class)
class MovieControllerTest {
    @MockBean
    private AdminService adminService;

    @Autowired
    private MovieController movieController;

    @MockBean
    private TicketService ticketService;

    /**
     * Method under test: {@link MovieController#addMovie(Movie)}
     */
    @Test
    void testAddMovie() throws Exception {
        Movie movie = new Movie();
        movie.setDescription("The characteristics of someone or something");
        movie.setMovieId(1);
        movie.setMovieName("Movie Name");
        movie.setTheatreDetails(new ArrayList<>());
        when(adminService.addMovie(Mockito.<Movie>any())).thenReturn(movie);

        Movie movie2 = new Movie();
        movie2.setDescription("The characteristics of someone or something");
        movie2.setMovieId(1);
        movie2.setMovieName("Movie Name");
        movie2.setTheatreDetails(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(movie2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/addMovie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"movieId\":1,\"movieName\":\"Movie Name\",\"description\":\"The characteristics of someone or something\","
                                        + "\"theatreDetails\":[]}"));
    }

    /**
     * Method under test: {@link MovieController#addMovie(Movie)}
     */
    @Test
    void testAddMovie2() throws Exception {
        when(adminService.addMovie(Mockito.<Movie>any())).thenThrow(new DuplicateMovieIdException());

        Movie movie = new Movie();
        movie.setDescription("The characteristics of someone or something");
        movie.setMovieId(1);
        movie.setMovieName("Movie Name");
        movie.setTheatreDetails(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(movie);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/addMovie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link MovieController#getMovieById(int)}
     */
    @Test
    void testGetMovieById() throws Exception {
        Movie movie = new Movie();
        movie.setDescription("The characteristics of someone or something");
        movie.setMovieId(1);
        movie.setMovieName("Movie Name");
        movie.setTheatreDetails(new ArrayList<>());
        when(adminService.getMovieById(anyInt())).thenReturn(movie);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/movieById/{mid}", 1);
        MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"movieId\":1,\"movieName\":\"Movie Name\",\"description\":\"The characteristics of someone or something\","
                                        + "\"theatreDetails\":[]}"));
    }

    /**
     * Method under test: {@link MovieController#deleteMovie(int)}
     */
    @Test
    void testDeleteMovie() throws Exception {
        when(adminService.deleteMovie(anyInt())).thenReturn(true);
        when(ticketService.deleteBooking(anyInt())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/delete/{mid}", 1);
        MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Movie record deleted"));
    }

    /**
     * Method under test: {@link MovieController#deleteMovie(int)}
     */
    @Test
    void testDeleteMovie2() throws Exception {
        when(adminService.deleteMovie(anyInt())).thenReturn(false);
        when(ticketService.deleteBooking(anyInt())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/delete/{mid}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Movie record not deleted"));
    }

    /**
     * Method under test: {@link MovieController#deleteMovie(int)}
     */
    @Test
    void testDeleteMovie3() throws Exception {
        when(adminService.deleteMovie(anyInt())).thenReturn(true);
        when(ticketService.deleteBooking(anyInt())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/delete/{mid}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Movie record not deleted"));
    }

    /**
     * Method under test: {@link MovieController#deleteMovie(int)}
     */
    @Test
    void testDeleteMovie4() throws Exception {
        when(adminService.deleteMovie(anyInt())).thenReturn(true);
        when(ticketService.deleteBooking(anyInt())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/delete/{mid}", 1);
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Movie record deleted"));
    }

    /**
     * Method under test: {@link MovieController#updateMovie(Movie)}
     */
    @Test
    void testUpdateMovie() throws Exception {
        when(adminService.updateMovie(Mockito.<Movie>any())).thenReturn(true);

        Movie movie = new Movie();
        movie.setDescription("The characteristics of someone or something");
        movie.setMovieId(1);
        movie.setMovieName("Movie Name");
        movie.setTheatreDetails(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(movie);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/updateMovie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Movie record updated"));
    }

    /**
     * Method under test: {@link MovieController#updateMovie(Movie)}
     */
    @Test
    void testUpdateMovie2() throws Exception {
        when(adminService.updateMovie(Mockito.<Movie>any())).thenReturn(false);

        Movie movie = new Movie();
        movie.setDescription("The characteristics of someone or something");
        movie.setMovieId(1);
        movie.setMovieName("Movie Name");
        movie.setTheatreDetails(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(movie);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/updateMovie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Movie record not updated"));
    }

    /**
     * Method under test: {@link MovieController#getMovie(String)}
     */
    @Test
    void testGetMovie() throws Exception {
        when(adminService.getAllMovie()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/getAllMovies")
                .header("authorization", "JaneDoe");
        MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

