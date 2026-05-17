package com.hotel.gestion_hotelera.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "habitaciones")
@Data
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habitacion")
    private Integer id;

    @Column(length = 10)
    private String numero;

    @Column(length = 50)
    private String tipo;

    private Integer capacidad;

    @Column(precision = 10, scale = 2)
    private BigDecimal precio;

    private Boolean disponibilidad;

    @Column(length = 30)
    private String estado;

    private Integer piso;

    @ManyToOne
    @JoinColumn(name = "id_hotel")
    private Hotel hotel;
}
