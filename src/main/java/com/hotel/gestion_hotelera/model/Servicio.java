package com.hotel.gestion_hotelera.model;


import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.math.BigDecimal;

@Entity
@Table(name = "servicios")
@Data
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Integer id;

    @Column(length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(precision = 10, scale = 2)
    private BigDecimal precio;

    @ManyToMany(mappedBy = "servicios")
    private List<Reserva> reservas;
}
