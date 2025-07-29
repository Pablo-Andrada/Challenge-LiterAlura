// src/main/java/com/aluracursos/challenge/dto/BookDTO.java
package com.aluracursos.challenge.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
        Integer id,
        String title,
        List<AuthorDTO> authors,
        List<String> languages,
        List<String> subjects,
        FormatsDTO formats,

        // Nuevo campo para descargas
        @JsonProperty("download_count")
        Integer downloadCount
) {}
