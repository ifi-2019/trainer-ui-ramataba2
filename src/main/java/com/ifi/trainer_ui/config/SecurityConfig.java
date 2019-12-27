package com.ifi.trainer_ui.config;

import com.ifi.trainer_ui.trainers.bo.Trainer;
import com.ifi.trainer_ui.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
    @Override
    public UserDetailsService userDetailsService() {
        return s -> {
            Trainer user = trainerService.getTrainer(s);
            if(user == null){
                throw  new BadCredentialsException("No such user");
            }
            return User.withUsername(user.getName()).password(user.getPassword()).roles("USER").build();
        };
    }

  @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/registerTrainer", "/images/**").permitAll()
                .antMatchers("/pokedex").authenticated()
                .and().formLogin().permitAll()
        .and().logout().permitAll();
    }

    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    public TrainerService getTrainerService() {
        return trainerService;
    }
}

