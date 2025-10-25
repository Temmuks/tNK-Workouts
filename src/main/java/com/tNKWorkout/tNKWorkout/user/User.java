package com.tNKWorkout.tNKWorkout.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.tNKWorkout.tNKWorkout.Workouts.Legs;
import com.tNKWorkout.tNKWorkout.Workouts.Pull;
import com.tNKWorkout.tNKWorkout.Workouts.Push;

public class User {
    private String userName;
    private String password;

    private final List<Legs> legs = new ArrayList<>();
    private final List<Pull> pulls = new ArrayList<>();
    private final List<Push> pushes = new ArrayList<>();
    private static LocalDateTime date = LocalDateTime.now();

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void addPull(String name, int weight, LocalDateTime date) {
        pulls.add(new Pull(name, weight, date));
    }

    public void addPush(String name, int weight, LocalDateTime date) {
        pushes.add(new Push(name, weight, date));
    }

    public void addLegs(String name, int weight, LocalDateTime date) {
        legs.add(new Legs(name, weight, date));
    }

    public void initiatePpl() {
        pushes.add(new Push("Bänkpress", 40, date));
        pushes.add(new Push("Lutande hantelpress", 10, date));
        pushes.add(new Push("Militärpress", 15, date));
        pushes.add(new Push("Axelpress", 15, date));
        pushes.add(new Push("Triceps push", 35, date));

        legs.add(new Legs("Lårcurl", 50, date));
        legs.add(new Legs("Benspark", 55, date));
        legs.add(new Legs("Knäböj", 70, date));
        legs.add(new Legs("Crunches", 5, date));

        pulls.add(new Pull("Marklyft", 55, date));
        pulls.add(new Pull("Latsdrag", 40, date));
        pulls.add(new Pull("Hammer curl", 15, date));
        pulls.add(new Pull("StångCurl", 20, date));
        pulls.add(new Pull("Rodd", 50, date));
    }

    public List<Push> getLatestPushesByType() {
        return pushes.stream()
                .collect(Collectors.toMap(
                        Push::getName, // group by name (x, y, z)
                        push -> push, // map value is the push object
                        (p1, p2) -> p1.getDate().isAfter(p2.getDate()) ? p1 : p2 // keep the latest
                ))
                .values() // return only the values (latest push per type)
                .stream()
                .collect(Collectors.toList());
    }

    public List<Pull> getLatestPullsByType() {
        return pulls.stream()
                .collect(Collectors.toMap(
                        Pull::getName,
                        push -> push, // map value is the push object
                        (p1, p2) -> p1.getDate().isAfter(p2.getDate()) ? p1 : p2 // keep the latest
                ))
                .values() // return only the values (latest push per type)
                .stream()
                .collect(Collectors.toList());
    }

    public List<Legs> getLatestLegsByType() {
        return legs.stream()
                .collect(Collectors.toMap(
                        Legs::getName, // group by name (x, y, z)
                        push -> push, // map value is the push object
                        (p1, p2) -> p1.getDate().isAfter(p2.getDate()) ? p1 : p2 // keep the latest
                ))
                .values() // return only the values (latest push per type)
                .stream()
                .collect(Collectors.toList());
    }

}
