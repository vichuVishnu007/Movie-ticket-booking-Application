package com.example.demo.repository;


import com.example.demo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;

@Repository
@Transactional
public interface AdminRepository extends JpaRepository<Movie, Integer>
{
	

}
