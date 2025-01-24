package com.casejoin.project.presentation.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CASE JOIN")
                        .description("TESTE DESENVOLVEDOR FULLSTACK JAVA + REACT - Sênior")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Paulo Tito")
                                .url("https://github.com")
                                .email("paulinho.tito@gmail.com")))
                .components(new Components()
                        .addParameters("IdGet", new Parameter()
                                .in("path")
                                .name("id")
                                .required(true)
                                .description("Id GET")
                                .schema(new io.swagger.v3.oas.models.media.Schema().type("integer").format("int64"))))
                ;
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }
}
