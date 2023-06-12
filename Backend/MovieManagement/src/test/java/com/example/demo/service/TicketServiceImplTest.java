package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TicketServiceImpl.class})
@ExtendWith(SpringExtension.class)
class TicketServiceImplTest {
    @MockBean
    private TicketRepository ticketRepository;

    @Autowired
    private TicketServiceImpl ticketServiceImpl;

    /**
     * Method under test: {@link TicketServiceImpl#getAllBooking(int)}
     */
    @Test
    void testGetAllBooking() {
        HashSet<Ticket> ticketSet = new HashSet<>();
        when(ticketRepository.getBookingList(anyInt())).thenReturn(ticketSet);
        Set<Ticket> actualAllBooking = ticketServiceImpl.getAllBooking(1);
        assertSame(ticketSet, actualAllBooking);
        assertTrue(actualAllBooking.isEmpty());
        verify(ticketRepository).getBookingList(anyInt());
    }

    /**
     * Method under test: {@link TicketServiceImpl#BookMovie(Ticket)}
     */
    @Test
    void testBookMovie() {
        Ticket ticket = new Ticket();
        ticket.setBookedSeats(1);
        ticket.setIssuedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        ticket.setMovie_id_fk(1);
        ticket.setMoviename("Moviename");
        ticket.setTheatre("Theatre");
        ticket.setTransactionId(1);
        ticket.setUsername("janedoe");
        when(ticketRepository.saveAndFlush(Mockito.<Ticket>any())).thenReturn(ticket);

        Ticket ticket2 = new Ticket();
        ticket2.setBookedSeats(1);
        ticket2.setIssuedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        ticket2.setMovie_id_fk(1);
        ticket2.setMoviename("Moviename");
        ticket2.setTheatre("Theatre");
        ticket2.setTransactionId(1);
        ticket2.setUsername("janedoe");
        assertTrue(ticketServiceImpl.BookMovie(ticket2));
        verify(ticketRepository).saveAndFlush(Mockito.<Ticket>any());
    }

    /**
     * Method under test: {@link TicketServiceImpl#deleteBooking(int)}
     */
    @Test
    void testDeleteBooking() {
        doNothing().when(ticketRepository).deleteBooking(anyInt());
        assertTrue(ticketServiceImpl.deleteBooking(1));
        verify(ticketRepository).deleteBooking(anyInt());
    }

    /**
     * Method under test: {@link TicketServiceImpl#getMovieBookingByName(String)}
     */
    @Test
    void testGetMovieBookingByName() {
        ArrayList<Ticket> ticketList = new ArrayList<>();
        when(ticketRepository.getMovieBookingByName(Mockito.<String>any())).thenReturn(ticketList);
        List<Ticket> actualMovieBookingByName = ticketServiceImpl.getMovieBookingByName("Name");
        assertSame(ticketList, actualMovieBookingByName);
        assertTrue(actualMovieBookingByName.isEmpty());
        verify(ticketRepository).getMovieBookingByName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link TicketServiceImpl#deleteBookingByTransactionID(int)}
     */
    @Test
    void testDeleteBookingByTransactionID() {
        when(ticketRepository.deleteBookingByTransactionID(anyInt())).thenReturn(1);
        assertEquals(1, ticketServiceImpl.deleteBookingByTransactionID(1));
        verify(ticketRepository).deleteBookingByTransactionID(anyInt());
    }
}

