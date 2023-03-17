package com.Sprint1.Sprint1.dto.request;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
@Builder
public class HotelRequestDto {
    @NotBlank
    @Size(min = 3, max = 15, message = "El nombre debe tener entre 3 y 15 caracteres")
    private String nombreUsuario;
    @Valid
    private HotelReservaDto hotelReserva;
}
