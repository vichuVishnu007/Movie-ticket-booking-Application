package com.example.demo.service;

import com.example.demo.model.Theatre;
import com.example.demo.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheatreServiceImpl implements TheatreService{

    @Autowired
    TheatreRepository theatreRepository;

    @Override
    public boolean updateTheatre(Theatre theatre) {
        Theatre theatre1 = theatreRepository.getTheatreByName(theatre.getTheatreName());
        if(theatre !=null && theatreRepository.getTheatreByName(theatre.getTheatreName()).equals(theatre.getTheatreName())) {
//			movie1.setTicketPrice(movie.getTicketPrice());
            theatreRepository.saveAndFlush(theatre1);
            return true;
        } else if(theatre!=null){
            return true;
        }
        return false;
    }
}
