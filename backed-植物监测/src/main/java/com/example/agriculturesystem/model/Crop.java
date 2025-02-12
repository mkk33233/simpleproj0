package com.example.agriculturesystem.model;

import jakarta.persistence.*;

@Entity
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "growth_cycle")
    private String growthCycle;
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrowthCycle() {
        return growthCycle;
    }

    public void setGrowthCycle(String growthCycle) {
        this.growthCycle = growthCycle;
    }
}
