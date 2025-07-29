// src/main/java/com/aluracursos/challenge/repository/BookRepository.java
package com.aluracursos.challenge.repository;

import com.aluracursos.challenge.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    /** Derived query para listar libros por idioma */
    List<Book> findByLanguage(String language);

    /** Buscar un libro por título exacto (ignore case) */
    Optional<Book> findByTitleIgnoreCase(String title);

    /** Buscar todos los libros cuyo título contenga la cadena dada (ignore case) */
    List<Book> findByTitleContainsIgnoreCase(String fragment);
}
