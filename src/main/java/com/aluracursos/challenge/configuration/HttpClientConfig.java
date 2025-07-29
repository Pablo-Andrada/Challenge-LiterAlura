// src/main/java/com/aluracursos/challenge/configuration/HttpClientConfig.java
package com.aluracursos.challenge.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.time.Duration;

@Configuration
public class HttpClientConfig {

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }
}
