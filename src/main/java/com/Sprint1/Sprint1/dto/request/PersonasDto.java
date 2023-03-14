package com.Sprint1.Sprint1.dto.request;

import lombok.*;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonasDto {
    @NotBlank
    @Min(8)
    private String dni;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String fechaNacimiento;
    @Email()
    private String email;
}
