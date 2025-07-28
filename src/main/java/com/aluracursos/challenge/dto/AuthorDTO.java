// src/main/java/com/aluracursos/challenge/dto/AuthorDTO.java
package com.aluracursos.challenge.dto;

public record AuthorDTO(
        String name,
        Integer birth_year,
        Integer death_year
) {}
