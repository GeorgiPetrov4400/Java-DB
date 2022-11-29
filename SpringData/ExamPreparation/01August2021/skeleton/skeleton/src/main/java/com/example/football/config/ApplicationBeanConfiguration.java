package com.example.football.config;

import com.example.football.models.dto.playerDtos.PlayerImportDTO;
import com.example.football.models.entity.Player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.tomcat.jni.Local;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<String, LocalDate> convertToLocalDate = context -> LocalDate.parse(context.getSource());
        modelMapper.addConverter(convertToLocalDate, String.class, LocalDate.class);

        modelMapper.addConverter(s -> LocalDate.parse(s.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                String.class, LocalDate.class);

//        this.modelMapper.addConverter(new Converter<String, LocalDate>() {
//            @Override
//            public LocalDate convert(MappingContext<String, LocalDate> context) {
//                return LocalDate.parse(context.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//            }
//        });


//        this.modelMapper.addConverter((Converter<String, LocalDate>)
//                context1 -> LocalDate.parse(context1.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));


//        this.modelMapper.addConverter(ctx -> LocalDate.parse(ctx.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
//                String.class, LocalDate.class);


//        TypeMap<PlayerImportDTO, Player> typeMap = this.modelMapper()
//                .createTypeMap(PlayerImportDTO.class, Player.class);
//        typeMap.addMappings(map -> map.using(localDateConverter)
//                .map(PlayerImportDTO::getBirthDate, Player::setBirthDate));

        return modelMapper;
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().create();
    }

}
