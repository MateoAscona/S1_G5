package com.Sprint1.Sprint1.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VueloReservationData {
    @JsonFormat(pattern = "yyyy/MM/dd", shape = JsonFormat.Shape.STRING)
    private LocalDate fechaDesde;
    @JsonFormat(pattern = "yyyy/MM/dd", shape = JsonFormat.Shape.STRING)
    private LocalDate fechaHasta;
    @Size(min = 3, max = 15, message = "El destino debe tener entre 3 y 15 caracteres")
    private String origen;
    @Size(min = 3, max = 15, message = "El destino debe tener entre 3 y 15 caracteres")
    private String destino;
    @NotBlank(message = "Ingrese un codigo de vuelo")
    private String codigoVuelo;
    @Min(value = 1, message = "Ingrese al menos 1 asiento")
    @Max(value = 3, message = "No puede comprar mas de 3 asientos a la vez")
    private Integer cantidadAsientos;
    @NotBlank(message = "Especifique el tipo de asientos")
    private String claseAsientos;
    @Valid
    private List<PersonasDto> personas;
    @Valid
    private MetodoPagoDto metodoPago;
}