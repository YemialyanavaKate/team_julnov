package by.ita.je.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
    @Bean
    public RestTemplate serviceAppClient() {
        return new RestTemplateBuilder()
                .defaultHeader("webAppClient", String.valueOf(2))
                .build();
    }
}
