package com.Herbaciarnia.DrinkTeaWithMe.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.Herbaciarnia.DrinkTeaWithMe"})
@EnableAutoConfiguration
public class ApplicationConfig {


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}




