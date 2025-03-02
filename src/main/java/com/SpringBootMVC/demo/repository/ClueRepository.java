package com.SpringBootMVC.demo.repository;


import com.SpringBootMVC.demo.entity.Clue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClueRepository extends JpaRepository<Clue, Long> {
    List<Clue> findByDestinationId(Long destinationId);
}
