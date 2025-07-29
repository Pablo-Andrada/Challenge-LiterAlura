package com.aluracursos.challenge.service;

import com.aluracursos.challenge.dto.BookDTO;
import com.aluracursos.challenge.dto.AuthorDTO;
import com.aluracursos.challenge.model.Book;
import com.aluracursos.challenge.model.Author;
import com.aluracursos.challenge.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersistenceService {

    private final BookRepository bookRepo;

    public PersistenceService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    /**
     * Convierte un BookDTO a entidad Book (incluye Author) y lo persiste.
     */
    public void saveBook(BookDTO dto) {
        Book b = new Book();
        b.setId(dto.id());
        b.setTitle(dto.title());
        // Solo el primer idioma según el desafío
        b.setLanguage(dto.languages().isEmpty() ? null : dto.languages().get(0));
        b.setDownloadCount(dto.downloadCount());

        // Mapeo del primer autor
        if (!dto.authors().isEmpty()) {
            AuthorDTO ad = dto.authors().get(0);
            Author a = new Author();
            a.setName(ad.name());
            a.setBirthYear(ad.birth_year());
            a.setDeathYear(ad.death_year());
            b.setAuthor(a);
        }

        // Lista de subjects
        b.setSubjects(dto.subjects());

        bookRepo.save(b);
    }

    /**
     * Lista todos los libros guardados.
     */
    public List<Book> listAllBooks() {
        return bookRepo.findAll();
    }

    /**
     * Filtra libros por idioma.
     */
    public List<Book> listByLanguage(String lang) {
        return bookRepo.findByLanguage(lang);
    }
}
