package com.Sprint1.Sprint1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class HotelObject {
    private String codigoHotel;
    private String nombre;
    private String lugarCiudad;
    private String tipoDeHabitacion;
    private Double precioPorNoche;
    private Date disponibleDesde;
    private Date disponibleHasta;
    private boolean reservado;

}
