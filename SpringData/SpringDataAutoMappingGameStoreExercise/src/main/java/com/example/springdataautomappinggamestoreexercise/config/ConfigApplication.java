package com.example.springdataautomappinggamestoreexercise.config;

import com.example.springdataautomappinggamestoreexercise.entities.Game;
import com.example.springdataautomappinggamestoreexercise.entities.dtos.GameAddDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigApplication {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(GameAddDTO.class, Game.class)
                .addMappings(m -> m.map(GameAddDTO::getThumbnailURL, Game::setImageThumbnail));

        return modelMapper;
    }
}
