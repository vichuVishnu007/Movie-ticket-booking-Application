package com.example.demo.repository;

import com.example.demo.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.Set;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket,Integer> {

    @Query(value="select t from Ticket t where t.movie_id_fk = :movieId")
    public Set<Ticket> getBookingList(int movieId);

    @Modifying
    @Query(value="delete from Ticket where movie_id_fk =:movieId")
    public void deleteBooking(int movieId);
}
