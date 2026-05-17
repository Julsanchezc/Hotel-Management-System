package com.hotel.gestion_hotelera.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "hoteles")
@Data
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hotel")
    private Integer id;

    @Column(length = 100)
    private String nombre;

    @Column(length = 200)
    private String direccion;

    @Column(length = 100)
    private String ciudad;

    @Column(length = 20)
    private String telefono;

    @Column(length = 150)
    private String correo;

    @Column(length = 50)
    private String categoria;

    @Column(length = 30)
    private String estado;
}

