// src/main/java/com/aluracursos/challenge/model/Book.java
package com.aluracursos.challenge.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    private Integer id;

    private String title;

    private String language;

    private Integer downloadCount;

    @ManyToOne(cascade = jakarta.persistence.CascadeType.ALL)
    private Author author;

    // Cargamos los subjects en EAGER para que estén disponibles fuera de la sesión
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "book_subjects",
            joinColumns = @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "fk_book_subjects_book"))
    )
    @Column(name = "subject")
    private List<String> subjects;

    // Constructor vacío requerido por JPA
    public Book() { }

    // Getters y setters

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }
    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<String> getSubjects() {
        return subjects;
    }
    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return String.format(
                "Book{id=%d, title='%s', language='%s', downloads=%d, author=%s, subjects=%s}",
                id,
                title,
                language,
                downloadCount,
                author != null ? author.getName() : "null",
                subjects
        );
    }
}
