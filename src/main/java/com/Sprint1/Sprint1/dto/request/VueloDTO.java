package com.Sprint1.Sprint1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VueloDTO {

    private Integer id;
    private String nroVuelo;
    private String origen;
    private String destino;
    private String tipoAsiento;
    private Double precioPorPersona;
    private LocalDate fechaIda;
    private LocalDate fechaVuelta;
}