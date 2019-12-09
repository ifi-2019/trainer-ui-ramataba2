package com.ifi.trainer_ui.trainers.service;

import com.ifi.trainer_ui.trainers.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerServiceImpl implements TrainerService {
    private RestTemplate template;
    private String trainerUrl;
    @Override
    public List<Trainer> listTrainers() {
        List<Trainer> pokemons = null;
        Trainer[] trainersResponse = template.getForObject(trainerUrl + "trainers", Trainer[].class);
        if (trainersResponse != null) {
            pokemons = Arrays.asList(trainersResponse);

        }
        return pokemons;
    }

    @Override
    public Trainer getTrainer(String name) {
        return template.getForObject(trainerUrl + "trainers/" + name, Trainer.class);
    }

    @Override
    public List<Trainer> listOtherTrainers(String name) {
        List<Trainer> trainers = listTrainers();

        if(trainers != null){
            trainers = trainers.stream().filter(trainer -> !trainer.getName().equals(name)).collect(Collectors.toList());
        }
        return trainers;

    }


    @Autowired
    @Qualifier("trainerApiRestTemplate")
    void setRestTemplate(RestTemplate restTemplate) {
        this.template = restTemplate;
    }
    @Value("${trainer.service.url}")
    public void setTrainerUrl(String trainerUrl) {
        this.trainerUrl = trainerUrl;
    }
}
