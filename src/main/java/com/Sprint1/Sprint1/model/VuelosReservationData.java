package com.Sprint1.Sprint1.model;

import com.Sprint1.Sprint1.dto.request.PersonasDto;
import com.Sprint1.Sprint1.dto.response.StatusCodeDto;
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
@Table(name = "data_reservas_vuelos")
public class VuelosReservationData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private LocalDate fechaDesde;
    @Column
    private LocalDate fechaHasta;
    @Column
    private String origen;
    @Column
    private String destino;
    @Column
    private String codigoVuelo;
    @Column
    private Integer cantidadAsientos;
    @Column
    private String claseAsiento;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<PersonasObject> personas;

    @OneToOne(cascade = CascadeType.PERSIST)
    private StatusCodeObject estado;
}
