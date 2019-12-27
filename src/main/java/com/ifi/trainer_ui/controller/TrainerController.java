package com.ifi.trainer_ui.controller;

import com.ifi.trainer_ui.trainers.bo.Trainer;
import com.ifi.trainer_ui.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TrainerController {

    TrainerService trainerService;

    @GetMapping("/trainers")
    public ModelAndView trainers(){
       // List<Trainer> trainers = trainerService.listTrainers();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Principal principal = (Principal) auth.getPrincipal();
        List<Trainer> trainers = trainerService.listOtherTrainers(principal.getName());

        if(trainers == null){
            trainers = new ArrayList<>();
        }
        var modelAndView = new ModelAndView("trainers");
        modelAndView.addObject("trainers", trainers);
        return modelAndView;
    }

    @GetMapping("/profile")
    public ModelAndView profile(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Principal principal = (Principal) auth.getPrincipal();
        Trainer trainer = trainerService.getTrainer(principal.getName());

        var modelAndView = new ModelAndView("profile");
        modelAndView.addObject("profile", trainer);
        return modelAndView;
    }

}
