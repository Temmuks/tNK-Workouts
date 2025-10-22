package com.tNKWorkout.tNKWorkout.Workouts;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class PushService {

    private static final List<Push> pushes = new ArrayList<>();
    private static LocalDateTime date = LocalDateTime.now();
        static {
        pushes.add(new Push("Bänkpress", 40, date));
        pushes.add(new Push("Lutande hantelpress", 10, date));
        pushes.add(new Push("Militärpress", 15, date));
        pushes.add(new Push("Axelpress", 15, date));
        pushes.add(new Push("Triceps push", 35, date));
    }

    public static List<Push> getLatestPushesByType() {
        return pushes.stream()
            .collect(Collectors.toMap(
                Push::getName, // group by name (x, y, z)
                push -> push,  // map value is the push object
                (p1, p2) -> p1.getDate().isAfter(p2.getDate()) ? p1 : p2 // keep the latest
            ))
            .values() // return only the values (latest push per type)
            .stream()
            .collect(Collectors.toList());
    }

    public static void addPush(String name, int weight, LocalDateTime date){
        pushes.add(new Push(name, weight, date));
    }
    
}
