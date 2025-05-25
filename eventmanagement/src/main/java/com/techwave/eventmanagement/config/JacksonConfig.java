//package com.techwave.eventmanagement.config;
//
//// Ce @Bean permettra à Spring Boot de savoir comment convertir les objets LocalDateTime dans les fichiers JSON.
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class JacksonConfig {
//
//    // Enregistre le module JavaTime dans l'ObjectMapper
//    @Bean
//    public ObjectMapper objectMapper() {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new JavaTimeModule());
//        return mapper;
//    }
//}
