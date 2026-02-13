package com.gsa.movie.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI movieApi() {

        return new OpenAPI()

                .info(new Info()
                        .title("Movie Management Service API")
                        .version("v1.0.0")
                        .description("""
                                This service manages movie metadata for the booking platform.
                                
                                Core capabilities:
                                • Create and update movie details
                                • Bulk movie ingestion
                                • Retrieve movie catalog
                                • Manage movie attributes (genre, language, duration)
                                
                                Acts as the master movie catalog service used by show scheduling.
                                """)
                        .contact(new Contact()
                                .name("Platform Engineering Team")
                                .email("platform-support@gsa.com")
                                .url("https://gsa.com"))
                        .license(new License()
                                .name("Internal Platform License")
                                .url("https://gsa.com/license"))
                )

                .servers(List.of(
                        new Server()
                                .url("http://localhost:8083")
                                .description("Local Development"),

                        new Server()
                                .url("https://api-dev.gsa.com/movie")
                                .description("Development Environment"),

                        new Server()
                                .url("https://api.gsa.com/movie")
                                .description("Production Environment")
                ))

                .externalDocs(new ExternalDocumentation()
                        .description("Movie Service Architecture Documentation")
                        .url("https://gsa.com/docs/movie-service"));
    }
}
