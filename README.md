# Proyecto Literalura

## Descripción

LiterAlura es una aplicación de consola desarrollada en Java y Spring Boot que permite buscar información de libros mediante la API de Gutendex, convertir los datos JSON en entidades Java, y persistirlos en una base de datos PostgreSQL usando Spring Data JPA.

---

## Tecnologías y versiones

* Java JDK 17
* Spring Boot 3.2.3
* Spring Data JPA
* PostgreSQL 16
* Jackson 2.16
* HttpClient (Java 11+)
* Maven 4

---

## Dependencias clave (`pom.xml`)

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <scope>runtime</scope>
</dependency>
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>2.16.0</version>
</dependency>
```

---

## Estructura de paquetes

```
com.aluracursos.challenge
├─ dto           # Clases de transferencia JSON ↔ Java
├─ model         # Entidades JPA (Book, Author)
├─ repository    # Interfaces Spring Data (BookRepository, AuthorRepository)
├─ service       # Lógica de negocio y persistencia
│   ├─ ChallengeConsumoAPI.java  # Cliente HttpClient
│   └─ PersistenceService.java   # Guardado y consultas JPA
├─ principal     # Clase de consola con menú (CommandLineRunner)
└─ application   # Main SpringBoot
```

---

## Configuración

1. Crea la base de datos en PostgreSQL:

   ```sql
   CREATE DATABASE literalura;
   ```
2. Ajusta `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
   spring.datasource.username=<tu_usuario>
   spring.datasource.password=<tu_contraseña>
   spring.jpa.hibernate.ddl-auto=update
   ```

---

## Cómo ejecutar

```bash
mvn clean install
mvn spring-boot:run
```

Luego, el menú se desplegará en la consola.

---

## Opciones del menú

1. **Buscar libros en API**: solicita texto e idioma, muestra resultados de Gutendex.
2. **Guardar último buscado**: persiste el primer resultado en BD.
3. **Listar libros guardados**: muestra todos los libros en PostgreSQL.
4. **Buscar libros por idioma**: filtra por idioma en la base.
5. **Listar autores guardados**: muestra todos los autores.
6. **Listar autores vivos en un año**: filtra por año de muerte.
7. **Buscar libros guardados por fragmento de título**: búsqueda por subcadena.
8. **Salir**: cierra la aplicación.

---

## Ejemplos

```text
1  - Buscar libros en API
2  - Guardar último buscado
...

1
Texto de búsqueda: quijote
Idioma (es, en, fr…): es
Total encontrados: 4
2000 – Don Quijote ([es]), descargas: 17831
...

2
Libro guardado: Don Quijote

3
Book{id=2000, title='Don Quijote', language='es', downloads=17831, author='Cervantes Saavedra', subjects=[...]}
```

---

## Notas y mejoras futuras

* Agregar paginación al buscar API.
* Añadir consultas avanzadas (por número de descargas).
* Implementar interfaz web o REST.

---
Proyecto Desafio de Alura Latam y Oracle Next Education 2025.
 
