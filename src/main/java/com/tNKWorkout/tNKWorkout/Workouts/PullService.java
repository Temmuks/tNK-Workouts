package com.tNKWorkout.tNKWorkout.Workouts;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class PullService {

    private static final List<Pull> pulls = new ArrayList<>();
    private static LocalDateTime date = LocalDateTime.now();
    static {
        pulls.add(new Pull("Marklyft", 55, date));
        pulls.add(new Pull("Latsdrag", 40, date));
        pulls.add(new Pull("Hammer curl", 15, date));
        pulls.add(new Pull("StångCurl", 20, date));
        pulls.add(new Pull("Rodd", 50, date));
    }

    public static List<Pull> getLatestPullsByType() {
        return pulls.stream()
            .collect(Collectors.toMap(
                Pull::getName, // group by name (x, y, z)
                push -> push,  // map value is the push object
                (p1, p2) -> p1.getDate().isAfter(p2.getDate()) ? p1 : p2 // keep the latest
            ))
            .values() // return only the values (latest push per type)
            .stream()
            .collect(Collectors.toList());
    }

    public static void addPull(String name, int weight, LocalDateTime date){
        pulls.add(new Pull(name, weight, date));
    }
    
}