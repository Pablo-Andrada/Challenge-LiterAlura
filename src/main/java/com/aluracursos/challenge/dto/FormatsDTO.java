// src/main/java/com/aluracursos/challenge/dto/FormatsDTO.java
package com.aluracursos.challenge.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FormatsDTO(
        @JsonProperty("text/plain; charset=utf-8") String txt,
        @JsonProperty("application/pdf")         String pdf
) {}
