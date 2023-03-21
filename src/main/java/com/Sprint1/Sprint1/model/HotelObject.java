package com.Sprint1.Sprint1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class HotelObject {
    private String codigoHotel;
    private String nombre;
    private String lugarCiudad;
    private String tipoDeHabitacion;
    private Double precioPorNoche;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate disponibleDesde;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate disponibleHasta;
    private boolean reservado;

}
