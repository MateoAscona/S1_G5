package com.Sprint1.Sprint1.dto.request;

import com.Sprint1.Sprint1.model.PersonasObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HotelReservationData {

    @JsonFormat(pattern = "yyyy/MM/dd", shape = JsonFormat.Shape.STRING)
    private LocalDate fechaDesde;

    @JsonFormat(pattern = "yyyy/MM/dd", shape = JsonFormat.Shape.STRING)
    private LocalDate fechaHasta;
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
    private List<PersonasObject> personas;
    @Valid
    private MetodoPagoDto metodoPago;

}

