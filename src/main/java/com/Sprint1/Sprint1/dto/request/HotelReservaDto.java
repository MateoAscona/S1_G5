package com.Sprint1.Sprint1.dto.request;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
public class HotelReservaDto {

    @NotBlank
    private String fechaDesde;
    @NotBlank
    private String fechaHasta;
    @NotBlank
    //@Size(max = 15, min = 5, message = "El nombre del destino debe contener entre 5 y 15 caracteres.")
    private String destino;
    @NotBlank
    @Size(min = 5, max = 15)
    private String codigoHotel;
    @Min(1)
    @Max(5)
    private Integer cantidadPersonas;
    @NotBlank
    private String tipoHabitacion;

    private @Valid List<PersonasDto> personas;

    private @Valid MetodoPagoDto metodoPago;

}
