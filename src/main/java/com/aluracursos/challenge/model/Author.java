// src/main/java/com/aluracursos/challenge/model/Author.java
package com.aluracursos.challenge.model;

import jakarta.persistence.*;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer birthYear;

    private Integer deathYear;

    // Constructor vacío
    public Author() { }

    // Getters y setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }
    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }
    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    @Override
    public String toString() {
        return String.format(
                "Author{id=%d, name='%s', birthYear=%s, deathYear=%s}",
                id, name,
                birthYear != null ? birthYear : "?",
                deathYear != null ? deathYear : "?"
        );
    }
}
