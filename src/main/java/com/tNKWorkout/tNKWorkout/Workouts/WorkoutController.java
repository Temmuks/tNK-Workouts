package com.tNKWorkout.tNKWorkout.Workouts;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tNKWorkout.tNKWorkout.user.User;

@Controller
public class WorkoutController {
    private static LocalDateTime date = LocalDateTime.now();

    private static List<User> users = new ArrayList<>();
    static {
        users.add(new User("Temmuks", "Jonte"));
        users.add(new User("Vigor", "Andersson"));
    }
    private boolean loggedIn = false;
    private String loggedInUser;

    @GetMapping({ "/", "/index" })
    public String getIndex(Model model) {
        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("user", new User(null, null));
        return "index";
    }

    @PostMapping("/new-login")
    String newLogin(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        for (User user : users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                loggedIn = true;
                loggedInUser = user.getUserName();
                return "redirect:/workouts";
            }
        }
        return "redirect:/index";
    }

    @PostMapping("/logout")
    String logout() {
        loggedIn = false;
        return "redirect:/index";
    }

    @GetMapping("/workouts")
    String getWorkouts(Model model) {
        System.out.println(loggedInUser);
        if (loggedIn) {
            for (User user : users) {
                if (user.getUserName().equals(loggedInUser)) {
                    List<Pull> latestPulls = user.getLatestPullsByType();
                    model.addAttribute("pulls", latestPulls);
                    List<Push> latestPushes = user.getLatestPushesByType();
                    model.addAttribute("pushes", latestPushes);
                    List<Legs> latestLegs = user.getLatestLegsByType();
                    model.addAttribute("loggedInUser", loggedInUser);
                    model.addAttribute("legs", latestLegs);
                    model.addAttribute("newPush", new Push(null, 0, date));
                    model.addAttribute("newPull", new Pull(null, 0, date));
                    model.addAttribute("newLegs", new Legs(null, 0, date));
                }
            }
            return "workouts";
        }
        return "redirect:/index";
    }

    @PostMapping("/new-push")
    String newPush(@RequestParam(value = "name") String name,
            @RequestParam("weight") int weight) {
        System.out.println(loggedInUser);
        for (User user : users) {
            if (user.getUserName().equals(loggedInUser)){
                user.addPush(name, weight, LocalDateTime.now());
            System.out.println("Added push to " + user.getUserName());
            }
        }
        return "redirect:/workouts";
    }

    @PostMapping("/new-pull")
    String newPull(@RequestParam(value = "name") String name,
            @RequestParam("weight") int weight) {
       for (User user : users) {
            if (user.getUserName().equals(loggedInUser)){
                user.addPull(name, weight, LocalDateTime.now());
            System.out.println("Added push to " + user.getUserName());
            }
        }
        return "redirect:/workouts";
    }

    @PostMapping("/new-legs")
    String newLegs(@RequestParam(value = "name") String name,
            @RequestParam("weight") int weight) {
       for (User user : users) {
            if (user.getUserName().equals(loggedInUser)){
                user.addLegs(name, weight, LocalDateTime.now());
            System.out.println("Added push to " + user.getUserName());
            }
        }
        return "redirect:/workouts";
    }

}
