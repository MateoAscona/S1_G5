package com.Sprint1.Sprint1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelDTO {

    private Integer id;
    private String codigoHotel;
    private String nombre;
    private String lugarCiudad;
    private String tipoDeHabitacion;
    private Double precioPorNoche;
    private LocalDate disponibleDesde;
    private LocalDate disponibleHasta;
    private boolean reservado;
}
