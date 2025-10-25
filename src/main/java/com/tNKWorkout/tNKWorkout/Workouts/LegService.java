package com.tNKWorkout.tNKWorkout.Workouts;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class LegService {

    private static final List<Legs> legs = new ArrayList<>();
    private static LocalDateTime date = LocalDateTime.now();
    static {
        legs.add(new Legs("Lårcurl", 50, date));
        legs.add(new Legs("Benspark", 55, date));
        legs.add(new Legs("Knäböj", 70, date));
        legs.add(new Legs("Crunches", 5, date));
    }

    public static List<Legs> getLatestLegsByType() {
        return legs.stream()
            .collect(Collectors.toMap(
                Legs::getName, // group by name (x, y, z)
                push -> push,  // map value is the push object
                (p1, p2) -> p1.getDate().isAfter(p2.getDate()) ? p1 : p2 // keep the latest
            ))
            .values() // return only the values (latest push per type)
            .stream()
            .collect(Collectors.toList());
    }

    public static void addLegs(String name, int weight, LocalDateTime date){
        legs.add(new Legs(name, weight, date));
    }
    
}