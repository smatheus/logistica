package br.luizalabs.desafio.logistica.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Logística API")
                        .version("1.0")
                        .description("API para tranformação de arquivo legado e busca de pedidos por usuário."));
    }
}

