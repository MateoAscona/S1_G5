package com.Sprint1.Sprint1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
@Table(name = "reservas_hotel_data")
public class HotelReservationData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private LocalDate fechaDesde;
    @Column
    private LocalDate fechaHasta;
    @Column
    private String destino;
    @Column
    private String codigoHotel;
    @Column
    private Integer cantidadPersonas;
    @Column
    private String tipoDeHabitacion;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<PersonasObject> personas;

    @OneToOne(cascade = CascadeType.PERSIST)
    private StatusCodeObject estado;
}
