package com.Sprint1.Sprint1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
@Table(name = "vuelos")
public class VuelosObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 10)
    private String nroVuelo;
    @Column
    private String origen;
    @Column
    private String destino;
    @Column
    private String tipoAsiento;
    @Column
    private Double precioPorPersona;
    @Column
    private LocalDate fechaIda;
    @Column
    private LocalDate fechaVuelta;

}
