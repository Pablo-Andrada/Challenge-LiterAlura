// src/main/java/com/aluracursos/challenge/principal/PrincipalChallenge.java
package com.aluracursos.challenge.principal;

import com.aluracursos.challenge.dto.BookDTO;
import com.aluracursos.challenge.dto.GutendexResponse;
import com.aluracursos.challenge.model.Book;
import com.aluracursos.challenge.service.ChallengeConsumoAPI;
import com.aluracursos.challenge.service.PersistenceService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class PrincipalChallenge {

    private final ChallengeConsumoAPI api;
    private final PersistenceService persistenceService;
    private final Scanner teclado = new Scanner(System.in);
    private BookDTO lastSearch;

    public PrincipalChallenge(ChallengeConsumoAPI api,
                              PersistenceService persistenceService) {
        this.api = api;
        this.persistenceService = persistenceService;
    }

    public void muestraElMenu() {
        int opcion;
        do {
            System.out.println("""
                1 - Buscar libros
                2 - Guardar último buscado
                3 - Listar libros guardados
                4 - Buscar libros por idioma
                5 - Salir
                """);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1 -> buscarYMostrarLibros();
                case 2 -> guardarUltimoBuscado();
                case 3 -> listarLibrosGuardados();
                case 4 -> buscarLibrosPorIdioma();
                case 5 -> System.out.println("Adiós");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 5);
    }

    private void buscarYMostrarLibros() {
        System.out.print("Texto de búsqueda: ");
        String texto = teclado.nextLine();
        System.out.print("Idioma (es, en, fr…): ");
        String idioma = teclado.nextLine();

        try {
            GutendexResponse<BookDTO> resp = api.buscarLibros(texto, idioma, 1);
            System.out.println("Total encontrados: " + resp.count());

            for (BookDTO b : resp.results()) {
                System.out.printf(
                        "%d – %s (%s), descargas: %d%n",
                        b.id(), b.title(), b.languages(), b.downloadCount()
                );
                if (!b.authors().isEmpty()) {
                    var a = b.authors().get(0);
                    System.out.printf("   Autor: %s (%s–%s)%n",
                            a.name(),
                            a.birth_year() != null ? a.birth_year() : "?",
                            a.death_year() != null ? a.death_year() : "?"
                    );
                }
                if (!b.subjects().isEmpty()) {
                    System.out.println("   Temas: " + String.join(", ", b.subjects()));
                }
                System.out.println();
            }

            // Guardamos el primer resultado para la opción 2
            if (!resp.results().isEmpty()) {
                lastSearch = resp.results().get(0);
            } else {
                lastSearch = null;
            }

        } catch (RuntimeException e) {
            System.out.println("⚠️ Error al consultar la API: " + e.getMessage());
        }
    }

    private void guardarUltimoBuscado() {
        if (lastSearch == null) {
            System.out.println("No hay libro para guardar. Primero busca un libro.");
        } else {
            persistenceService.saveBook(lastSearch);
            System.out.println("✅ Libro guardado: " + lastSearch.title());
        }
    }

    private void listarLibrosGuardados() {
        List<Book> libros = persistenceService.listAllBooks();
        if (libros.isEmpty()) {
            System.out.println("No hay libros guardados.");
        } else {
            libros.forEach(System.out::println);
        }
    }

    private void buscarLibrosPorIdioma() {
        System.out.print("Idioma a filtrar: ");
        String lang = teclado.nextLine();
        List<Book> libros = persistenceService.listByLanguage(lang);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma: " + lang);
        } else {
            libros.forEach(System.out::println);
        }
    }
}
