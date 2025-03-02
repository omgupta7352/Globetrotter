package com.SpringBootMVC.demo.entity;

import jakarta.persistence.*;

@Table(name = "clues")
@Entity
public class Clue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clueText;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String getClueText() {
        return clueText;
    }

    public void setClueText(String clueText) {
        this.clueText = clueText;
    }
// Getters and Setters
}
