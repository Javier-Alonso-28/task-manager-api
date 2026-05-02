# Task Manager API

API REST para gestión de tareas personales construida con **Spring Boot 3**, **PostgreSQL** y **arquitectura en capas**. Implementa autenticación con **JWT**, roles de usuario y buenas prácticas empresariales.

## 🚀 Tecnologías

- **Java 21**
- **Spring Boot 3.4**
- **Spring Data JPA + Hibernate**
- **Spring Security + JWT**
- **PostgreSQL 16**
- **Maven**
- **Lombok**
- **Bean Validation**

## 📐 Arquitectura

Proyecto organizado en capas siguiendo principios de Clean Architecture:

controller → service → repository → entity


Con paquetes adicionales para `dto`, `mapper`, `exception`, `security` y `config`.

## 🔧 Requisitos

- Java 21
- Maven 3.9+
- PostgreSQL 16+

## ⚙️ Configuración

1. Clona el repositorio:
   ```bash
   git clone https://github.com/Javier-Alonso-28/task-manager-api.git
   
2. Crea la base de datos:  
    ```sql  
   CREATE DATABASE task_manager_db;



3. Configura application.yml con tus credenciales de PostgreSQL.


4. Ejecuta:   
     ```bash  
   ./mvnw spring-boot:run

## 🧪 Probar la API con Postman

En la carpeta `postman/` encontrarás la colección lista para importar:

1. Abre Postman → clic en **Import** (esquina superior izquierda).
2. Arrastra los archivos:
   - `postman/Task-Manager-API.postman_collection.json`
   - `postman/Task-Manager-Local.postman_environment.json`
3. Selecciona el environment **"Task Manager"** en la esquina superior derecha.
4. Asegúrate de que la app esté corriendo (`./mvnw spring-boot:run`).
5. Ejecuta `Auth → Register` para crear un usuario y obtener tu JWT.
6. El token se guarda automáticamente y todas las requests de `Tasks` lo usarán.

### Endpoints disponibles

| Método | Endpoint | Descripción | Auth |
|--------|----------|-------------|------|
| POST | `/api/v1/auth/register` | Registrar usuario | ❌ |
| POST | `/api/v1/auth/login` | Iniciar sesión | ❌ |
| POST | `/api/v1/tasks` | Crear tarea | ✅ |
| GET | `/api/v1/tasks` | Listar tareas (con filtros y paginación) | ✅ |
| GET | `/api/v1/tasks/{id}` | Obtener tarea por id | ✅ |
| PUT | `/api/v1/tasks/{id}` | Actualizar tarea | ✅ |
| DELETE | `/api/v1/tasks/{id}` | Eliminar tarea | ✅ |


## 📚 Endpoints (en construcción)   
Próximamente: documentación completa con Swagger/OpenAPI.

## 👨‍💻 Autor
Javier Contreras — GitHub






