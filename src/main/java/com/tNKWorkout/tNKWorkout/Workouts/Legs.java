package com.tNKWorkout.tNKWorkout.Workouts;

import java.time.LocalDateTime;

public class Legs {
    private String name;
    private int weight;
    private LocalDateTime date;
    
    public Legs(String name, int weight, LocalDateTime date) {
        this.name = name;
        this.weight = weight;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
