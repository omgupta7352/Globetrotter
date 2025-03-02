package com.SpringBootMVC.demo.entity;

import jakarta.persistence.*;

@Table(name = "fun_facts")
@Entity
public class FunFact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String funFactText;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFunFactText() {
        return funFactText;
    }

    public void setFunFactText(String funFactText) {
        this.funFactText = funFactText;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
// Getters and Setters
}
