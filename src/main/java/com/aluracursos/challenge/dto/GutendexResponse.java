// GutendexResponse.java
package com.aluracursos.challenge.dto;

import java.util.List;

public record GutendexResponse<T>(
        int count,
        String next,
        String previous,
        List<T> results
) {}
