package com.SpringBootMVC.demo.repository;

import com.SpringBootMVC.demo.entity.FunFact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FunFactRepository extends JpaRepository<FunFact, Long> {
    List<FunFact> findByDestinationId(Long destinationId);
}
