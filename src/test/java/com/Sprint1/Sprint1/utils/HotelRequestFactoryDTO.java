package com.Sprint1.Sprint1.utils;

import com.Sprint1.Sprint1.dto.request.HotelRequestDto;
import com.Sprint1.Sprint1.dto.request.HotelReservaDto;
import com.Sprint1.Sprint1.dto.request.MetodoPagoDto;
import com.Sprint1.Sprint1.dto.request.PersonasDto;

import java.util.List;

public class HotelRequestFactoryDTO {

public static HotelRequestDto getHotelReserva() {

    return HotelRequestDto.builder()
            .nombreUsuario("Cristian")
            .hotelReserva(getReserva())
            .build();

}
    public static HotelReservaDto getReserva(){
        return HotelReservaDto.builder()
                .fechaDesde("2022/02/10")
                .fechaHasta("2022/03/20")
                .destino("Puerto Iguazú")
                .codigoHotel("CH-0002")
                .cantidadPersonas(1)
                .tipoHabitacion("Single")
                .personas(List.of(getPersona()))
                .metodoPago(getPagoDto())
                .build();

    }

    public static PersonasDto getPersona(){
        return PersonasDto.builder()
                .dni("37575676")
                .nombre("Franco")
                .apellido("Ambort")
                .fechaNacimiento("7 Febrero 1994")
                .email("ambortfranco94@gmail.com")
                .build();


    }
    public static MetodoPagoDto getPagoDto(){
        return MetodoPagoDto.builder()
                .tipo("Credito")
                .numero("3423213")
                .cuotas(12)
                .build();


    }

}
