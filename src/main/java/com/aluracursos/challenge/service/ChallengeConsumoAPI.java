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

    // Añadimos la barra al final para evitar redirecciones 301
    private static final String BASE = "https://gutendex.com/books/";

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
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(uri))
                .header("Accept", "application/json")
                // timeout de 10 segundos
                .timeout(Duration.ofSeconds(10))
                .build();
    }

    public GutendexResponse<BookDTO> buscarLibros(String texto, String idioma, int pagina) {
        HttpRequest request = buildRequest(texto, idioma, pagina);
        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

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
