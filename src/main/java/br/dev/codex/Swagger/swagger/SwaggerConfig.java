package br.dev.codex.Swagger.swagger;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI userOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Codex API")
                        .description("User and Address simples")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("See the README")
                        .url("https://github.com/loopfagundes/swagger-example/blob/openapi/README.md"));
    }
}