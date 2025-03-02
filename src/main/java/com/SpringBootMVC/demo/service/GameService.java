package com.SpringBootMVC.demo.service;

import com.SpringBootMVC.demo.entity.Clue;
import com.SpringBootMVC.demo.entity.Destination;
import com.SpringBootMVC.demo.entity.FunFact;
import com.SpringBootMVC.demo.repository.ClueRepository;
import com.SpringBootMVC.demo.repository.DestinationRepository;
import com.SpringBootMVC.demo.repository.FunFactRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private ClueRepository clueRepository;

    @Autowired
    private FunFactRepository funFactRepository;

    public Destination getRandomDestination() {
        return destinationRepository.getRandomDestination();
    }

    public Destination getDestinationById(Long destinationId) {
        return destinationRepository.findById(destinationId)
                .orElseThrow(() -> new RuntimeException("Destination not found"));
    }


    public List<Clue> getCluesForDestination(Long destinationId) {
        return clueRepository.findByDestinationId(destinationId);
    }

    public List<FunFact> getFunFactsForDestination(Long destinationId) {
        return funFactRepository.findByDestinationId(destinationId);
    }
}
