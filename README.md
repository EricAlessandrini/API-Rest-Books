# Book Management API 📝  
API RESTful desarrollada con Spring Boot para realizar operaciones CRUD de libros.

## 🚀 Características  
✔️ Creación, edición y eliminación de registros de libros, incorporando autores y generos al registro.  
✔️ Busqueda de todos los registros, busqueda individual y busqueda filtrada de registros.  
✔️ Base de datos en PostgreSQL utilizando Spring Data JPA/Hibernate.  
✔️ Tests unitarios con JUnit y Mockito.
✔️ Documentacion de la aplicacion a traves del uso de Swagger.

## 🛠️ Tecnologías utilizadas  
- Java 17  
- Spring Boot 3
- Lombok
- Spring Validation
- Spring Data JPA (PostgreSQL)  
- JUnit 5 y Mockito  
- Swagger

## 📌 Endpoints principales

### Endpoints de Libros

| Método | Endpoint       | Descripción                 |
|--------|--------------|-----------------------------|
| GET    | `/books`      | Obtiene todas las tareas    |
| GET    | `/books/title`      | Obtiene libros por titulo    |
| GET    | `/books/byAuthorName`      | Obtiene libros por nombre del autor    |
| GET    | `/books/byGenre`      | Obtiene libros por genero    |
| POST   | `/books/save`      | Crea un nuevo libro        |
| PUT    | `/books/edit/{id}` | Actualiza un libro         |
| DELETE | `/books/delete/{id}` | Elimina un libro           |

### Endpoints de Autores

| Método | Endpoint      | Descripción                 |
|--------|---------------|-----------------------------|
| GET    | `/author`      | Obtiene todos los autores registrados |
| GET    | `/author/authorName` | Obtiene un autor por nombre |
| PUT    | `/author/editAuthorInfo/{id}` | Actualiza un libro |

### Endpoints de Generos

| Método | Endpoint      | Descripción                 |
|--------|---------------|-----------------------------|
| GET    | `/genre`      | Obtiene todos los generos registrados |
| GET    | `/genre/genreName` | Obtiene un genero por nombre |

## 📫 Contacto  
LinkedIn: [Eric Alessandrini](https://www.linkedin.com/in/eric-alessandrini29)  
GitHub: [@EricAlessandrini](https://github.com/EricAlessandrini)  
Correo: eric.alessandrini1@gmail.com  
