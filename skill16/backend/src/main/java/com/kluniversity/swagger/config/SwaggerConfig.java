package com.kluniversity.swagger.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Student CRUD API - FSAD Skill 16")
                        .description(
                            "Full Stack Application Development — KL University\n\n" +
                            "This API provides complete CRUD operations for managing student records.\n\n" +
                            "**Endpoints:**\n" +
                            "- `POST /students` — Add a new student\n" +
                            "- `GET /students` — Retrieve all students\n" +
                            "- `GET /students/{id}` — Retrieve student by ID\n" +
                            "- `PUT /students/{id}` — Update a student\n" +
                            "- `DELETE /students/{id}` — Delete a student"
                        )
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Team FSAD")
                                .email("fsad@kluniversity.in"))
                        .license(new License()
                                .name("KL University Academic License")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local Development Server")
                ));
    }
}
