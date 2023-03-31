package com.Sprint1.Sprint1.dto.response;

import com.Sprint1.Sprint1.dto.request.PersonasDto;
import com.Sprint1.Sprint1.model.PersonasObject;
import com.Sprint1.Sprint1.model.StatusCodeObject;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HotelReservaResponseDto {
    private String fechaDesde;
    private String fechaHasta;
    private String destino;
    private String codigoHotel;
    private Integer cantidadPersonas;
    private String tipoHabitacion;

    private List<PersonasObject> personas;

    private StatusCodeObject estado;
}
