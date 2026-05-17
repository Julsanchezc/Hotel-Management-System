package com.hotel.gestion_hotelera.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "facturas")
@Data
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Integer id;

    @Column(name = "fecha_factura")
    private LocalDateTime fechaFactura;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    @OneToOne
    @JoinColumn(name = "id_pago", unique = true)
    private Pago pago;
}