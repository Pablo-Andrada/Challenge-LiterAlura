// src/main/java/com/aluracursos/challenge/service/PersistenceService.java
package com.aluracursos.challenge.service;

import com.aluracursos.challenge.dto.BookDTO;
import com.aluracursos.challenge.dto.AuthorDTO;
import com.aluracursos.challenge.model.Book;
import com.aluracursos.challenge.model.Author;
import com.aluracursos.challenge.repository.BookRepository;
import com.aluracursos.challenge.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersistenceService {

    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;

    public PersistenceService(BookRepository bookRepo,
                              AuthorRepository authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }

    /** Convierte un BookDTO a entidad Book (incluye Author) y lo persiste. */
    public void saveBook(BookDTO dto) {
        Book b = new Book();
        b.setId(dto.id());
        b.setTitle(dto.title());
        b.setLanguage(dto.languages().isEmpty() ? null : dto.languages().get(0));
        b.setDownloadCount(dto.downloadCount());

        if (!dto.authors().isEmpty()) {
            AuthorDTO ad = dto.authors().get(0);
            Author a = new Author();
            a.setName(ad.name());
            a.setBirthYear(ad.birth_year());
            a.setDeathYear(ad.death_year());
            b.setAuthor(a);
        }

        b.setSubjects(dto.subjects());
        bookRepo.save(b);
    }

    /** Lista todos los libros guardados. */
    public List<Book> listAllBooks() {
        return bookRepo.findAll();
    }

    /** Filtra libros por idioma. */
    public List<Book> listByLanguage(String lang) {
        return bookRepo.findByLanguage(lang);
    }

    /** Busca un libro exacto por título. */
    public Optional<Book> findBookByTitle(String title) {
        return bookRepo.findByTitleIgnoreCase(title);
    }

    /** Busca libros cuyo título contenga la cadena dada. */
    public List<Book> findBooksByTitleFragment(String fragment) {
        return bookRepo.findByTitleContainsIgnoreCase(fragment);
    }

    /** Lista todos los autores guardados. */
    public List<Author> listAllAuthors() {
        return authorRepo.findAll();
    }

    /** Lista autores vivos en el año indicado. */
    public List<Author> listAuthorsAliveIn(int year) {
        return authorRepo.findVivosEnAno(year);
    }
}
