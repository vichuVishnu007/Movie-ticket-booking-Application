package com.example.demo.repository;

import com.example.demo.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TheatreRepository extends JpaRepository<Theatre,Integer> {

    @Query(value="select t from Theatre t where t.TheatreName = :theatreName")
    public Theatre getTheatreByName(String theatreName);
}
