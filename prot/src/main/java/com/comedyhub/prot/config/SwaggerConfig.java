package com.comedyhub.prot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
        		.components(new Components())
                .info(new Info().title("ComedyHub API")
                                .description("API for ComedyHub application")
                                .version("1.0"));
    }
    
}
