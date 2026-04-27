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


## 📚 Endpoints (en construcción)   
Próximamente: documentación completa con Swagger/OpenAPI.

## 👨‍💻 Autor
Javier Contreras — GitHub






