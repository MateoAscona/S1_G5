package com.Sprint1.Sprint1.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonasDto {
    @Size(min = 7, message = "El DNI debe ser valido (8 caracteres)")
    private String dni;
    @Size(min = 3, max = 15, message = "El nombre debe tener entre 3 y 15 caracteres")
    private String nombre;
    @Size(min = 3, max = 15, message = "El apellido debe tener entre 3 y 15 caracteres")
    private String apellido;
    @NotBlank(message = "Complete la fecha de nacimiento")
    private String fechaNacimiento;
    @Email(message = "Ingrese un email valido")
    private String email;


}
