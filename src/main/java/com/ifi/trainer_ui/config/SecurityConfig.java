package com.ifi.trainer_ui.config;

import com.ifi.trainer_ui.trainers.bo.Trainer;
import com.ifi.trainer_ui.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private TrainerService trainerService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
       /* return username -> Optional.of(trainerService.listTrainers().stream().filter(trainer -> trainer.getName().equals()))
                .map(trainers -> trainers,(trainer -> User.withUsername(trainer.getName()).password(trainer.getPassword()).roles("USER").build()))
                .orElseThrow(() -> new BadCredentialsException("No such user"));*/
        return  new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                Trainer user = trainerService.getTrainer(s);
                if(user == null){
                    throw  new BadCredentialsException("No such user");
                }
                return User.withUsername(user.getName()).password(user.getPassword()).roles("USER").build();
            }
        };
    }

    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    public TrainerService getTrainerService() {
        return trainerService;
    }
}

