// src/main/java/com/aluracursos/challenge/repository/AuthorRepository.java
package com.aluracursos.challenge.repository;

import com.aluracursos.challenge.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Derived query para listar autores vivos (deathYear == null)
    List<Author> findByDeathYearIsNull();
}
