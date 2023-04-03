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
@Table(name = "hoteles")
public class HotelObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 10, unique = true)
    private String codigoHotel;
    @Column(length = 30)
    private String nombre;
    @Column(length = 30)
    private String lugarCiudad;
    @Column(length = 10)
    private String tipoDeHabitacion;
    @Column
    private Double precioPorNoche;
    @Column
    private LocalDate disponibleDesde;
    @Column
    private LocalDate disponibleHasta;
    @Column
    private boolean reservado;

}
