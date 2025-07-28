// src/main/java/com/aluracursos/challenge/dto/BookDTO.java
package com.aluracursos.challenge.dto;

import java.util.List;

public record BookDTO(
        Integer id,
        String title,
        List<AuthorDTO> authors,
        List<String> languages,
        List<String> subjects,
        FormatsDTO formats
) {}
