// src/main/java/com/aluracursos/challenge/repository/BookRepository.java
package com.aluracursos.challenge.repository;

import com.aluracursos.challenge.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    // Derived query para listar libros por idioma
    List<Book> findByLanguage(String language);
}
