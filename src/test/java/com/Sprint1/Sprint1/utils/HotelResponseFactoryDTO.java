package com.Sprint1.Sprint1.utils;

import com.Sprint1.Sprint1.dto.response.HotelReservaResponseDto;
import com.Sprint1.Sprint1.dto.response.HotelResponseDto;
import com.Sprint1.Sprint1.dto.response.StatusCodeDto;


import java.util.List;

import static com.Sprint1.Sprint1.utils.HotelRequestFactoryDTO.getPersona;

public class HotelResponseFactoryDTO {

    public static HotelResponseDto getHotelResponse() {

        return HotelResponseDto.builder()
                .nombreUsuario("Cristian")
                .total(6300.0)
                .hotelReservaResponse(getReservaResponse())
                .build();

    }

    public static HotelReservaResponseDto getReservaResponse() {
        return HotelReservaResponseDto.builder()
                .fechaDesde("2022/02/10")
                .fechaHasta("2022/03/20")
                .destino("Puerto Iguazú")
                .codigoHotel("CH-0002")
                .cantidadPersonas(1)
                .tipoHabitacion("Single")
                .personas(List.of(getPersona()))
                .estado(StatusCodeDto.builder()
                        .code(200)
                        .mensaje("Funciona correctamente")
                        .build())
                .build();

    }

}
