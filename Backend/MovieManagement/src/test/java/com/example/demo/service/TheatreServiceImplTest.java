package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.model.Theatre;
import com.example.demo.repository.TheatreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TheatreServiceImpl.class})
@ExtendWith(SpringExtension.class)
class TheatreServiceImplTest {
    @MockBean
    private TheatreRepository theatreRepository;

    @Autowired
    private TheatreServiceImpl theatreServiceImpl;

    /**
     * Method under test: {@link TheatreServiceImpl#updateTheatre(Theatre)}
     */
    @Test
    void testUpdateTheatre() {
        Theatre theatre = new Theatre();
        theatre.setAvailableSeats(1);
        theatre.setTheatreCapacity(1);
        theatre.setTheatreName("Theatre Name");
        theatre.setTicketPrice(10.0d);
        when(theatreRepository.getTheatreByName(Mockito.<String>any())).thenReturn(theatre);

        Theatre theatre2 = new Theatre();
        theatre2.setAvailableSeats(1);
        theatre2.setTheatreCapacity(1);
        theatre2.setTheatreName("Theatre Name");
        theatre2.setTicketPrice(10.0d);
        assertTrue(theatreServiceImpl.updateTheatre(theatre2));
        verify(theatreRepository, atLeast(1)).getTheatreByName(Mockito.<String>any());
    }
}

