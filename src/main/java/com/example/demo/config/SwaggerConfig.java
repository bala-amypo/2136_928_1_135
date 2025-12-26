package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "Bearer Authentication";
        
        return new OpenAPI()
                .info(new Info()
                        .title("Digital Event Broadcasting API")
                        .version("1.0")
                        .description("API for broadcasting digital events with JWT Security"))
                // 1. Sets the custom Sandbox/Server URL
                .servers(List.of(
                        new Server().url("https://9150.408procr.amypo.ai/")
                ))
                // 2. Adds the Global Authorize button requirement
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                // 3. Configures the Security Scheme as "Bearer"
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, createAPIKeyScheme()));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}