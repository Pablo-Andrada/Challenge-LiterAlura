// src/main/java/com/aluracursos/challenge/principal/PrincipalChallenge.java
package com.aluracursos.challenge.principal;

import com.aluracursos.challenge.dto.BookDTO;
import com.aluracursos.challenge.dto.GutendexResponse;
import com.aluracursos.challenge.service.ChallengeConsumoAPI;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class PrincipalChallenge {

    private final ChallengeConsumoAPI api;
    private final Scanner teclado = new Scanner(System.in);

    public PrincipalChallenge(ChallengeConsumoAPI api) {
        this.api = api;
    }

    public void muestraElMenu() {
        int opcion;
        do {
            System.out.println("""
                1 - Buscar libros
                2 - Salir
                """);
            opcion = teclado.nextInt(); teclado.nextLine();
            switch (opcion) {
                case 1 -> buscarLibros();
                case 2 -> System.out.println("Adiós");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 2);
    }

    private void buscarLibros() {
        System.out.print("Texto de búsqueda: ");
        String texto = teclado.nextLine();
        System.out.print("Idioma (e.g. es, en): ");
        String idioma = teclado.nextLine();

        try {
            GutendexResponse<BookDTO> resp = api.buscarLibros(texto, idioma, 1);
            System.out.println("Total encontrados: " + resp.count());
            resp.results().forEach(b ->
                    System.out.printf("%d – %s (%s)%n", b.id(), b.title(), b.languages())
            );
        } catch (RuntimeException e) {
            System.out.println("⚠️ Error al consultar la API: " + e.getMessage());
            // opcional: e.printStackTrace();
        }
    }
}
