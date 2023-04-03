package com.Sprint1.Sprint1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
@Table(name = "reservas_vuelos")
public class VuelosReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String userName;
    @Column
    private Double total;
    @OneToOne(cascade = CascadeType.PERSIST)
    private VuelosReservationData vuelosReservationData;
}
