package com.Sprint1.Sprint1.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MetodoPagoDto {
    @NotBlank
    private String tipo;
    @Size(min = 6)
    private String numero;

    @NotNull
    private Integer cuotas;

}
