package com.example.json.carDealer.config;

import com.google.gson.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class ConfigApp {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Gson createGson() {
        JsonDeserializer<LocalDateTime> toLocalDate =
                (json, t, c) -> LocalDateTime.parse(json.getAsString());

        JsonSerializer<String> fromLocalDate =
                (date, t, c) -> new JsonPrimitive(date);

        return new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .registerTypeAdapter(LocalDateTime.class, toLocalDate)
                .registerTypeAdapter(LocalDateTime.class, fromLocalDate)
                .create();
    }
}
