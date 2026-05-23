# 🏨 Sistema de Gestión Hotelera

🔗 **Repositorio:** https://github.com/Julsanchezc/Hotel-Management-System.git

Sistema backend desarrollado con **Spring Boot** y **PostgreSQL** para la administración integral de hoteles, incluyendo gestión de usuarios, habitaciones, reservas, pagos, facturación y servicios adicionales.

## 👥 Equipo

- Omar Daniel Calvache Madroñero
- Julian Santiago Sanchez Castro
- Kelly Yhojanna Marin

---

## 🛠️ Tecnologías Utilizadas

- Java 17
- Spring Boot 3.4.5
- Spring Data JPA / Hibernate
- PostgreSQL
- Flyway (migraciones automáticas)
- Lombok
- Maven

---

## 📁 Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/hotel/gestion_hotelera/
│   │   ├── GestionHoteleraApplication.java
│   │   ├── controller/               ← Capa de API REST 
│   │   │   ├── UsuarioController.java
│   │   │   ├── RolController.java
│   │   │   ├── HotelController.java
│   │   │   ├── HabitacionController.java
│   │   │   └── ClienteController.java
│   │   ├── model/
│   │   │   ├── Rol.java
│   │   │   ├── Usuario.java
│   │   │   ├── Hotel.java
│   │   │   ├── Habitacion.java
│   │   │   ├── Reserva.java
│   │   │   ├── Pago.java
│   │   │   ├── Factura.java
│   │   │   ├── Servicio.java
│   │   │   ├── Cliente.java          ← nueva
│   │   │   ├── Empleado.java         ← nueva
│   │   │   └── Administrador.java    ← nueva
│   │   ├── repository/
│   │   │   ├── RolRepository.java
│   │   │   ├── UsuarioRepository.java
│   │   │   ├── HotelRepository.java
│   │   │   ├── HabitacionRepository.java
│   │   │   ├── ReservaRepository.java
│   │   │   ├── PagoRepository.java
│   │   │   ├── FacturaRepository.java
│   │   │   ├── ServicioRepository.java
│   │   │   ├── ClienteRepository.java       ← nuevo
│   │   │   ├── EmpleadoRepository.java      ← nuevo
│   │   │   └── AdministradorRepository.java ← nuevo
│   │   └── service/
│   │       ├── UsuarioService.java
│   │       ├── RolService.java
│   │       ├── HotelService.java
│   │       ├── ClienteService.java
│   │       ├── EmpleadoService.java
│   │       └── AdministradorService.java
│   │       ├── HabitacionService.java       
│   │       └── ReservaService.java          
│   └── resources/
│       ├── application.properties
│       └── db/migration/
│           ├── V1__crear_tablas_iniciales.sql
│           └── V2__crear_tablas_cliente_empleado_administrador.sql
```

---

## 🗄️ Modelo Relacional

El sistema cuenta con 12 tablas principales:

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
| `clientes` | Clientes del hotel con datos personales |
| `empleados` | Personal del hotel por departamento |
| `administradores` | Administradores vinculados a usuario y hotel |

### Relaciones principales

- `roles` → `usuarios` (1:N)
- `hoteles` → `habitaciones` (1:N)
- `hoteles` → `empleados` (1:N)
- `hoteles` → `administradores` (1:N)
- `usuarios` → `reservas` (1:N)
- `usuarios` → `administradores` (1:1)
- `habitaciones` → `reservas` (1:N)
- `reservas` → `pagos` (1:N)
- `pagos` → `facturas` (1:1)
- `reservas` ↔ `servicios` (N:M)

---

## 🧩 Capa de Servicios

Cada servicio implementa las siguientes operaciones CRUD:

| Método | Descripción |
|---|---|
| `save()` | Guardar un nuevo registro |
| `findAll()` | Listar todos los registros |
| `findById()` | Buscar por ID |
| `update()` | Actualizar un registro existente |
| `delete()` | Eliminar por ID |

### Servicios disponibles

| Servicio | Entidad que gestiona |
|---|---|
| `UsuarioService` | Usuario |
| `RolService` | Rol |
| `HotelService` | Hotel |
| `ClienteService` | Cliente |
| `EmpleadoService` | Empleado |
| `AdministradorService` | Administrador |
| `HabitacionService` | Habitación |
| `ReservaService` | Reserva |
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

O desde IntelliJ: clic derecho en `GestionHoteleraApplication.java` → **Run**.

### 5. Verificar

Flyway ejecutará automáticamente las migraciones y creará las 12 tablas. En la consola deberías ver:

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
| `V1__crear_tablas_iniciales.sql` | Tablas base: roles, usuarios, hoteles, habitaciones, reservas, pagos, facturas, servicios |
| `V2__crear_tablas_cliente_empleado_administrador.sql` | Nuevas tablas: clientes, empleados, administradores |

Flyway lleva el historial de migraciones en la tabla `flyway_schema_history` dentro de la base de datos.

---

## 📌 Notas

- El proyecto usa `spring.jpa.hibernate.ddl-auto=validate`, lo que significa que Hibernate **no crea ni modifica tablas** — eso lo maneja exclusivamente Flyway.
- Las credenciales de la base de datos se manejan mediante variables de entorno para evitar exponer contraseñas en el repositorio.
- La versión de Spring Boot fue corregida de `4.0.6` (inexistente) a `3.4.5` (estable).
