package com.example.demo.controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.example.demo.model.Ticket;
import com.example.demo.repository.TheatreRepository;
import com.example.demo.service.AdminService;
import com.example.demo.service.TheatreService;
import com.example.demo.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TicketController.class})
@ExtendWith(SpringExtension.class)
class TicketControllerTest {
    @MockBean
    private AdminService adminService;

    @MockBean
    private TheatreRepository theatreRepository;

    @MockBean
    private TheatreService theatreService;

    @Autowired
    private TicketController ticketController;

    @MockBean
    private TicketService ticketService;

    /**
     * Method under test: {@link TicketController#BookTicket(int, Ticket)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testBookTicket() throws Exception {


        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/v1/ticket/add/{mid}", 1)
                .contentType(MediaType.APPLICATION_JSON);

        Ticket ticket = new Ticket();
        ticket.setBookedSeats(1);
        ticket.setIssuedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        ticket.setMovie_id_fk(1);
        ticket.setMoviename("Moviename");
        ticket.setTheatre("Theatre");
        ticket.setTransactionId(1);
        ticket.setUsername("janedoe");
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString(ticket));
        MockMvcBuilders.standaloneSetup(ticketController).build().perform(requestBuilder);
    }

    /**
     * Method under test: {@link TicketController#getMovieBookingByName(String)}
     */
    @Test
    void testGetMovieBookingByName() throws Exception {
        when(ticketService.getMovieBookingByName(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/ticket/getMyBookingByName/{username}", "janedoe");
        MockMvcBuilders.standaloneSetup(ticketController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TicketController#getMovieBookingByName(String)}
     */
    @Test
    void testGetMovieBookingByName2() throws Exception {
        when(ticketService.getMovieBookingByName(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/ticket/getMyBookingByName/{username}", "janedoe");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(ticketController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TicketController#DeleteBookingById(int)}
     */
    @Test
    void testDeleteBookingById() throws Exception {
        when(ticketService.deleteBookingByTransactionID(anyInt())).thenReturn(1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/ticket/deleteBookingById/{transactionId}", 1);
        MockMvcBuilders.standaloneSetup(ticketController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Booking deleted..."));
    }

    /**
     * Method under test: {@link TicketController#DeleteBookingById(int)}
     */
    @Test
    void testDeleteBookingById2() throws Exception {
        when(ticketService.deleteBookingByTransactionID(anyInt())).thenReturn(0);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/ticket/deleteBookingById/{transactionId}", 1);
        MockMvcBuilders.standaloneSetup(ticketController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Oops..deletion failed"));
    }

    /**
     * Method under test: {@link TicketController#DeleteBookingById(int)}
     */
    @Test
    void testDeleteBookingById3() throws Exception {
        when(ticketService.deleteBookingByTransactionID(anyInt())).thenReturn(1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/ticket/deleteBookingById/{transactionId}", 1);
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(ticketController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Booking deleted..."));
    }
}

