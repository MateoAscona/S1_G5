package com.Sprint1.Sprint1.dto.response;

import com.Sprint1.Sprint1.dto.request.HotelMetodoPagoDto;
import com.Sprint1.Sprint1.dto.request.HotelPersonasDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HotelBookingResponseDto {
    private String fechaDesde;
    private String fechaHasta;
    private String destino;
    private String codigoHotel;
    private Integer cantidadPersonas;
    private String tipoHabitacion;

    private List<HotelPersonasDto> personas;

    private HotelStatusCodeDto estado;
}
