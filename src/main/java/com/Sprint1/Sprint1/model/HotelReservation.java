package com.Sprint1.Sprint1.model;

import com.Sprint1.Sprint1.dto.response.HotelReservaResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "reservas_hotel")
public class HotelReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50)
    private String nombreUsuario;
    @Column
    private Double total;
    @OneToOne(cascade = CascadeType.PERSIST)
    private HotelReservationData hotelReservationData;
}

