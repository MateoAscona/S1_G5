package com.Sprint1.Sprint1.dto.request;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelReservaDto {
    @NotBlank(message = "Ingrese una fecha de ingreso")
    private String fechaDesde;
    @NotBlank(message = "Ingrese una fecha de regreso")
    private String fechaHasta;
    @Size(min = 3, max = 15, message = "El destino debe tener entre 3 y 15 caracteres")
    private String destino;
    @NotBlank(message = "Ingrese un codigo de hotel")
    private String codigoHotel;
    @Min(value = 1, message = "La cantidad de personas no puede ser menor a 1")
    @Max(value = 3, message = "La cantidad de personas no puede ser mayor a 3")
    private Integer cantidadPersonas;
    @NotBlank(message = "Especifique el tipo de habitacion")
    private String tipoHabitacion;
    @Valid
    private List<PersonasDto> personas;
    @Valid
    private MetodoPagoDto metodoPago;

}

