package com.Sprint1.Sprint1.dto.request;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
public class HotelRequestDto {
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String nombreUsuario;
    private @Valid HotelReservaDto hotelReserva;
}
