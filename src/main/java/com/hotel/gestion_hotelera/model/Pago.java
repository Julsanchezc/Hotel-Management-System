package com.hotel.gestion_hotelera.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "pagos")
@Data
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Integer id;

    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;

    @Column(precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(name = "metodo_pago", length = 50)
    private String metodoPago;

    @ManyToOne
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;
}
