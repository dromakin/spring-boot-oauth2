package com.dromakin.spring.auth2.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {
    public static final String AUTH_SECURITY_SCHEME = "spring_oauth";

    @Value("${spring.application.name}")
    private String applicationName;


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes(AUTH_SECURITY_SCHEME, new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .description("Oauth2 flow")
                                .flows(new OAuthFlows()
                                        .clientCredentials(new OAuthFlow()
                                                .tokenUrl("http://localhost:8080" + "/oauth/token")
                                                .scopes(new Scopes()
                                                        .addString("read", "for read operations")
                                                        .addString("write", "for write operations")
                                                ))))
                )
                .security(Arrays.asList(
                        new SecurityRequirement().addList("spring_oauth")))
                .info(new Info().title(applicationName));
    }

    @Bean
    public GroupedOpenApi customApi() {
        return GroupedOpenApi.builder().group("secured").pathsToMatch("/secured/**").build();
    }

    @Bean
    public GroupedOpenApi actuatorApi() {
        return GroupedOpenApi.builder().group("actuator").pathsToMatch("/actuator/**").build();
    }

    @Bean
    public GroupedOpenApi tokenApi() {
        return GroupedOpenApi.builder().group("token").pathsToMatch("/oauth/**").build();
    }
}
