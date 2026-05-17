# 🏨 Sistema de Gestión Hotelera
 
🔗 **Repositorio:** https://github.com/Julsanchezc/Hotel-Management-System.git
 
Sistema backend desarrollado con **Spring Boot** y **PostgreSQL** para la administración integral de hoteles, incluyendo gestión de usuarios, habitaciones, reservas, pagos, facturación y servicios adicionales.
 
## 👥 Equipo
 
- Omar Daniel Calvache Madroñero
- Julia Santiago Sanchez Castro
- Kelly Yhojanna Marin
---
 
## 🛠️ Tecnologías Utilizadas
 
- Java 26
- Spring Boot 4.0.6
- Spring Data JPA / Hibernate 7
- PostgreSQL 18
- Flyway 11 (migraciones automáticas)
- Lombok
- Maven
---
 
## 📁 Estructura del Proyecto
 
```
src/
├── main/
│   ├── java/com/hotel/gestion_hotelera/
│   │   ├── GestionHoteleraApplication.java
│   │   ├── model/
│   │   │   ├── Rol.java
│   │   │   ├── Usuario.java
│   │   │   ├── Hotel.java
│   │   │   ├── Habitacion.java
│   │   │   ├── Reserva.java
│   │   │   ├── Pago.java
│   │   │   ├── Factura.java
│   │   │   └── Servicio.java
│   │   └── repository/
│   │       ├── RolRepository.java
│   │       ├── UsuarioRepository.java
│   │       ├── HotelRepository.java
│   │       ├── HabitacionRepository.java
│   │       ├── ReservaRepository.java
│   │       ├── PagoRepository.java
│   │       ├── FacturaRepository.java
│   │       └── ServicioRepository.java
│   └── resources/
│       ├── application.properties
│       └── db/migration/
│           └── V1__crear_tablas_iniciales.sql
```
 
---
 
## 🗄️ Modelo Relacional
 
El sistema cuenta con 9 tablas principales:
 
| Tabla | Descripción |
|---|---|
| `roles` | Roles del sistema (admin, cliente, empleado) |
| `usuarios` | Usuarios registrados en el sistema |
| `hoteles` | Información de los hoteles |
| `habitaciones` | Habitaciones por hotel |
| `reservas` | Reservas realizadas por usuarios |
| `pagos` | Pagos asociados a reservas |
| `facturas` | Facturas generadas por pago |
| `servicios` | Servicios adicionales disponibles |
| `reserva_servicio` | Relación N:M entre reservas y servicios |
 
### Relaciones principales
 
- `roles` → `usuarios` (1:N)
- `hoteles` → `habitaciones` (1:N)
- `usuarios` → `reservas` (1:N)
- `habitaciones` → `reservas` (1:N)
- `reservas` → `pagos` (1:N)
- `pagos` → `facturas` (1:1)
- `reservas` ↔ `servicios` (N:M)
---
 
## ⚙️ Requisitos Previos
 
- [Java JDK 17+](https://www.oracle.com/java/technologies/downloads/)
- [PostgreSQL 14+](https://www.postgresql.org/download/)
- [Maven 3.8+](https://maven.apache.org/download.cgi) (o usar el wrapper incluido `./mvnw`)
- [pgAdmin](https://www.pgadmin.org/) (opcional, para visualizar la BD)
---
 
## 🚀 Instrucciones de Ejecución
 
### 1. Clonar el repositorio
 
```bash
git clone https://github.com/Julsanchezc/Hotel-Management-System.git
cd Hotel-Management-System
```
 
### 2. Crear la base de datos en PostgreSQL
 
Abre pgAdmin o cualquier cliente SQL y ejecuta:
 
```sql
CREATE DATABASE gestion_hotelera_db;
```
 
### 3. Configurar credenciales
 
Abre `src/main/resources/application.properties` y ajusta tu contraseña:
 
```properties
spring.datasource.password=${DB_PASSWORD:TU_CONTRASEÑA_AQUI}
```
 
### 4. Correr el proyecto
 
```bash
./mvnw spring-boot:run
```
 
O desde IntelliJ (o tu herramienta de desarrollo de preferencia): clic derecho en `GestionHoteleraApplication.java` → **Run**.
 
### 5. Verificar
 
Flyway ejecutará automáticamente `V1__crear_tablas_iniciales.sql` y creará las 9 tablas. En la consola deberías ver:
 
```
Started GestionHoteleraApplication in X.XXX seconds
```
 
El servidor queda disponible en: `http://localhost:8081`
 
---
 
## 🗂️ Migraciones con Flyway
 
Las migraciones se encuentran en:
 
```
src/main/resources/db/migration/
```
 
| Archivo | Descripción |
|---|---|
| `V1__crear_tablas_iniciales.sql` | Creación de todas las tablas del sistema |
 
Flyway lleva el historial de migraciones en la tabla `flyway_schema_history` dentro de la base de datos.
 
---
 
## 📌 Notas
 
- El proyecto usa `spring.jpa.hibernate.ddl-auto=validate`, lo que significa que Hibernate **no crea ni modifica tablas** — eso lo maneja exclusivamente Flyway.
- Las credenciales de la base de datos se manejan mediante variables de entorno para evitar exponer contraseñas en el repositorio.
