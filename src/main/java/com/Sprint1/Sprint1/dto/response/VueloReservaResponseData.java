package com.Sprint1.Sprint1.dto.response;

import com.Sprint1.Sprint1.dto.request.PersonasDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VueloReservaResponseData {
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private String origen;
    private String destino;
    private String codigoVuelo;
    private Integer cantidadAsientos;
    private String claseAsiento;

    private List<PersonasDto> personas;

    private StatusCodeDto estado;
}
