package com.ifi.trainer_ui.trainers.service;

import com.ifi.trainer_ui.trainers.bo.Trainer;

import java.util.List;

public interface TrainerService {

    List<Trainer> listTrainers();
    Trainer getTrainer(String name);

    List<Trainer> listOtherTrainers(String name);
}
