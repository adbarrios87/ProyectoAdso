# Arquitectura del Backend: Capas de la Aplicación

Para mantener el código organizado, escalable y fácil de mantener, este proyecto utiliza una arquitectura basada en capas

## 1. Entities (Entidades)
Las **Entities** son la representación directa de las tablas de la base de datos en código Java. 
- **Ubicación:** `src/main/java/proyecto/ADSO/proveedores/entities`
- **Función:** Cada clase es una tabla y cada atributo es una columna.
- **Tecnología:** Utilizan JPA/Hibernate (anotaciones como `@Entity`, `@Table`, `@Column`).
- **Ejemplo:** `RolesEntity.java` mapea directamente a la tabla `roles`.

## 2. Repositories (Repositorios)
Los **Repositories** son interfaces que se encargan de la comunicación con la base de datos.
- **Ubicación:** `src/main/java/proyecto/ADSO/proveedores/repositories`
- **Función:** Permiten realizar operaciones CRUD (Crear, Leer, Actualizar, Borrar) sin escribir SQL manualmente.
- **Tecnología:** Extienden de `JpaRepository`.
- **Ejemplo:** `RolesRepository` permite buscar roles por ID o nombre con métodos predefinidos.

## 3. DTOs (Data Transfer Objects)
Los **DTOs** son objetos diseñados exclusivamente para transportar datos entre el cliente (Frontend) y el servidor (Backend).
- **Ubicación:** `src/main/java/proyecto/ADSO/proveedores/dtos`
- **Función:** 
    - **Seguridad:** Evitan exponer toda la entidad de la base de datos al exterior.
    - **Flexibilidad:** Permiten enviar solo los campos necesarios para una operación específica (ej. un DTO para "Crear" y otro para "Ver detalle").
- **Ejemplo:** `RolesCreateRequestDto.java` solo contiene los campos que el usuario debe enviar para crear un rol.

## 4. Controllers (Controladores)
Los **Controllers** son los puntos de entrada de tu aplicación (la API).
- **Ubicación:** `src/main/java/proyecto/ADSO/proveedores/controllers`
- **Función:** Reciben las peticiones HTTP (GET, POST, PUT, DELETE), validan los datos de entrada (DTOs) y devuelven una respuesta al cliente.
- **Regla de oro:** No deben contener lógica de negocio compleja; solo deben delegar el trabajo a los **Services**.
- **Ejemplo:** `RolesController.java` expone el endpoint `/roles`.

---

### ¿Cómo fluye la información?
Cuando un usuario crea un nuevo registro (ej. un Rol):
1. El **Controller** recibe un **DTO** con los datos.
2. El **Service** (capa intermedia) procesa la lógica y convierte ese **DTO** en una **Entity**.
3. El **Repository** guarda la **Entity** en la base de datos.
4. El proceso se invierte para devolver la respuesta al usuario.

