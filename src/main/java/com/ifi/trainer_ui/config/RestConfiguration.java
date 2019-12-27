package com.ifi.trainer_ui.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class RestConfiguration {
    @Value("${trainer.service.username}")
    String user;
    @Value("${trainer.service.password}")
    String password;

    @Bean
    RestTemplate trainerApiRestTemplate(){
        BasicAuthenticationInterceptor interceptor = new BasicAuthenticationInterceptor(user, password);
        RestTemplate template = restTemplate();
        template.setInterceptors(Collections.singletonList(interceptor));
        return template;
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
