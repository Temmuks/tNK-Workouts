package com.tNKWorkout.tNKWorkout.Workouts;

import java.time.LocalDateTime;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WorkoutController {
    private static LocalDateTime date = LocalDateTime.now();

    @GetMapping("/workouts")
    String getWorkouts(Model model) {
        List<Pull> latestPulls = PullService.getLatestPullsByType();
        model.addAttribute("pulls", latestPulls);
        List<Push> latestPushes = PushService.getLatestPushesByType();
        model.addAttribute("pushes", latestPushes);
        List<Legs> latestLegs = LegService.getLatestLegsByType();
        model.addAttribute("legs", latestLegs);
        model.addAttribute("newPush", new Push(null, 0, date));
        model.addAttribute("newPull", new Pull(null, 0, date));
        model.addAttribute("newLegs", new Legs(null, 0, date));
        return "workouts";
    }

    @PostMapping("/new-push")
    String newPush(@RequestParam(value = "name") String name,
            @RequestParam("weight") int weight) {
        PushService.addPush(name, weight, LocalDateTime.now());
        return "redirect:/workouts";
    }

    @PostMapping("/new-pull")
    String newPull(@RequestParam(value = "name") String name,
            @RequestParam("weight") int weight) {
        PullService.addPull(name, weight, LocalDateTime.now());
        return "redirect:/workouts";
    }

    @PostMapping("/new-legs")
    String newLegs(@RequestParam(value = "name") String name,
            @RequestParam("weight") int weight) {
        LegService.addLegs(name, weight, LocalDateTime.now());
        return "redirect:/workouts";
    }

}
