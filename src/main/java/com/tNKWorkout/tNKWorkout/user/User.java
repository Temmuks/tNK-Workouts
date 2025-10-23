package com.tNKWorkout.tNKWorkout.user;

import java.time.LocalDateTime;
import java.util.List;

import com.tNKWorkout.tNKWorkout.Workouts.LegService;
import com.tNKWorkout.tNKWorkout.Workouts.PullService;
import com.tNKWorkout.tNKWorkout.Workouts.Push;
import com.tNKWorkout.tNKWorkout.Workouts.PushService;

public class User {
    private String userName;
    private String password;
    private static PullService pullService;
    private static PushService pushService;
    private static LegService legService;

    public User(String userName, String password, PullService pullService, PushService pushService,
            LegService legService) {
        this.userName = userName;
        this.password = password;
        this.pullService = pullService;
        this.pushService = pushService;
        this.legService = legService;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public static void addPull(String name, int weight, LocalDateTime date) {
        pullService.addPull(name, weight, date);
    }

    public static void addPush(String name, int weight, LocalDateTime date) {
        pushService.addPush(name, weight, date);
    }

    public static void addLegs(String name, int weight, LocalDateTime date) {
        legService.addLegs(name, weight, date);
    }

    public static List<Push> getLatestPushesByType(){
        List<Push> latestPushes = PushService.getLatestPushesByType();
        return latestPushes;
    }
}
