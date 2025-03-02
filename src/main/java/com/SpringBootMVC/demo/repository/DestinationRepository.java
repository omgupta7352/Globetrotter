package com.SpringBootMVC.demo.repository;

import com.SpringBootMVC.demo.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
    @Query("SELECT d FROM Destination d ORDER BY random() LIMIT 1")
    Destination getRandomDestination();
}
