// src/main/java/com/aluracursos/challenge/service/ChallengeConsumoAPI.java
package com.aluracursos.challenge.service;

import com.aluracursos.challenge.dto.BookDTO;
import com.aluracursos.challenge.dto.GutendexResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Service
public class ChallengeConsumoAPI {

    private static final String BASE = "https://gutendex.com/books";
    private final HttpClient client;
    private final ObjectMapper mapper = new ObjectMapper();

    public ChallengeConsumoAPI(HttpClient client) {
        this.client = client;
    }

    private HttpRequest buildRequest(String texto, String idioma, int pagina) {
        String uri = String.format("%s?search=%s&languages=%s&page=%d",
                BASE,
                URLEncoder.encode(texto, StandardCharsets.UTF_8),
                idioma,
                pagina
        );
        System.out.println("▶️ Consultando URI: " + uri);
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(uri))
                .header("Accept", "application/json")
                .timeout(Duration.ofSeconds(10))
                .build();
    }

    public GutendexResponse<BookDTO> buscarLibros(String texto, String idioma, int pagina) {
        try {
            HttpResponse<String> response =
                    client.send(buildRequest(texto, idioma, pagina),
                            HttpResponse.BodyHandlers.ofString());
            System.out.println("▶️ Cuerpo recibido: " + response.body());
            int status = response.statusCode();
            if (status < 200 || status >= 300) {
                throw new RuntimeException("HTTP error: " + status);
            }
            return mapper.readValue(
                    response.body(),
                    new TypeReference<GutendexResponse<BookDTO>>() {}
            );
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Falló llamada a Literalura API: " + e.getMessage(), e);
        }
    }
}
