CREATE TABLE roles (
                       id_rol SERIAL PRIMARY KEY,
                       nombre VARCHAR(50) NOT NULL
);

CREATE TABLE usuarios (
                          id_usuario SERIAL PRIMARY KEY,
                          nombre VARCHAR(100) NOT NULL,
                          apellido VARCHAR(100) NOT NULL,
                          correo VARCHAR(150) UNIQUE,
                          telefono VARCHAR(20),
                          contraseña VARCHAR(255) NOT NULL,
                          id_rol INTEGER,
                          FOREIGN KEY(id_rol) REFERENCES roles(id_rol)
);

CREATE TABLE hoteles (
                         id_hotel SERIAL PRIMARY KEY,
                         nombre VARCHAR(100),
                         direccion VARCHAR(200),
                         ciudad VARCHAR(100),
                         telefono VARCHAR(20),
                         correo VARCHAR(150),
                         categoria VARCHAR(50),
                         estado VARCHAR(30)
);

CREATE TABLE habitaciones (
                              id_habitacion SERIAL PRIMARY KEY,
                              numero VARCHAR(10),
                              tipo VARCHAR(50),
                              capacidad INTEGER,
                              precio DECIMAL(10,2),
                              disponibilidad BOOLEAN,
                              estado VARCHAR(30),
                              piso INTEGER,
                              id_hotel INTEGER,
                              FOREIGN KEY(id_hotel) REFERENCES hoteles(id_hotel)
);

CREATE TABLE reservas (
                          id_reserva SERIAL PRIMARY KEY,
                          fecha_inicio DATE,
                          fecha_fin DATE,
                          estado VARCHAR(30),
                          id_usuario INTEGER,
                          id_habitacion INTEGER,
                          FOREIGN KEY(id_usuario) REFERENCES usuarios(id_usuario),
                          FOREIGN KEY(id_habitacion) REFERENCES habitaciones(id_habitacion)
);

CREATE TABLE pagos (
                       id_pago SERIAL PRIMARY KEY,
                       fecha_pago TIMESTAMP,
                       monto DECIMAL(10,2),
                       metodo_pago VARCHAR(50),
                       id_reserva INTEGER,
                       FOREIGN KEY(id_reserva) REFERENCES reservas(id_reserva)
);

CREATE TABLE facturas (
                          id_factura SERIAL PRIMARY KEY,
                          fecha_factura TIMESTAMP,
                          total DECIMAL(10,2),
                          id_pago INTEGER UNIQUE,
                          FOREIGN KEY(id_pago) REFERENCES pagos(id_pago)
);

CREATE TABLE servicios (
                           id_servicio SERIAL PRIMARY KEY,
                           nombre VARCHAR(100),
                           descripcion TEXT,
                           precio DECIMAL(10,2)
);

CREATE TABLE reserva_servicio (
                                  id_reserva INTEGER,
                                  id_servicio INTEGER,
                                  PRIMARY KEY(id_reserva, id_servicio),
                                  FOREIGN KEY(id_reserva) REFERENCES reservas(id_reserva),
                                  FOREIGN KEY(id_servicio) REFERENCES servicios(id_servicio)
);
