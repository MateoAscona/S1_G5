package com.Sprint1.Sprint1.dto.request;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MetodoPagoDto {
    @NotBlank(message = "Ingrese el tipo de pago")
    private String tipo;
    @NotBlank(message = "Ingrese en numero de tarjeta")
    private String numero;
    @Min(value = 0, message = "Ingrese la cantidad de cuotas")
    @Max(value = 12, message = "Las cuotas no pueden ser mas de 12")
    private Integer cuotas;

}
