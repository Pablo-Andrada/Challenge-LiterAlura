// src/main/java/com/aluracursos/challenge/model/Book.java
package com.aluracursos.challenge.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    private Integer id;

    private String title;

    private String language;

    private Integer downloadCount;

    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

    @ElementCollection
    @CollectionTable(name = "book_subjects", joinColumns = @JoinColumn(name = "book_id"))
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
                id, title, language, downloadCount,
                author != null ? author.getName() : "null",
                subjects
        );
    }
}
