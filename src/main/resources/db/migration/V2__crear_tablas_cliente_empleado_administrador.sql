-- ============================================================
-- V2: Nuevas tablas: clientes, empleados, administradores
-- ============================================================

CREATE TABLE clientes (
    id_cliente        SERIAL PRIMARY KEY,
    nombre            VARCHAR(100) NOT NULL,
    apellido          VARCHAR(100) NOT NULL,
    correo            VARCHAR(150) UNIQUE,
    telefono          VARCHAR(20),
    documento_identidad VARCHAR(50),
    direccion         VARCHAR(200),
    nacionalidad      VARCHAR(100)
);

CREATE TABLE empleados (
    id_empleado         SERIAL PRIMARY KEY,
    nombre              VARCHAR(100) NOT NULL,
    apellido            VARCHAR(100) NOT NULL,
    correo              VARCHAR(150) UNIQUE,
    telefono            VARCHAR(20),
    cargo               VARCHAR(100),
    departamento        VARCHAR(50),
    fecha_contratacion  DATE,
    salario             DECIMAL(10, 2),
    estado              VARCHAR(30),
    id_hotel            INTEGER,
    FOREIGN KEY (id_hotel) REFERENCES hoteles(id_hotel)
);

CREATE TABLE administradores (
    id_administrador  SERIAL PRIMARY KEY,
    nombre            VARCHAR(100) NOT NULL,
    apellido          VARCHAR(100) NOT NULL,
    correo            VARCHAR(150) UNIQUE,
    telefono          VARCHAR(20),
    nivel_acceso      VARCHAR(50),
    fecha_asignacion  DATE,
    estado            VARCHAR(30),
    id_usuario        INTEGER UNIQUE,
    id_hotel          INTEGER,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_hotel)   REFERENCES hoteles(id_hotel)
);
