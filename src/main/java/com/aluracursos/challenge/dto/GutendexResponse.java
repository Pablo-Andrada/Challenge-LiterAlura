// src/main/java/com/aluracursos/challenge/dto/GutendexResponse.java
package com.aluracursos.challenge.dto;

import java.util.List;

public record GutendexResponse<T>(
        Integer count,
        String next,
        String previous,
        List<T> results
) {}
