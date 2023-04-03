package com.Sprint1.Sprint1.utils;

import com.Sprint1.Sprint1.dto.response.HotelReservaResponseDto;
import com.Sprint1.Sprint1.dto.response.HotelResponseDto;
import com.Sprint1.Sprint1.dto.response.StatusCodeDto;
import com.Sprint1.Sprint1.model.HotelReservationData;
import com.Sprint1.Sprint1.model.StatusCodeObject;


import java.time.LocalDate;
import java.util.List;

import static com.Sprint1.Sprint1.utils.HotelRequestFactoryDTO.getPersona;

public class HotelResponseFactoryDTO {

    public static HotelResponseDto getHotelResponse() {

        return HotelResponseDto.builder()
                .nombreUsuario("Cristian")
                .total(6300.0)
                .hotelReservationData(getReservationData())
                .build();

    }
    public static HotelResponseDto getHotelResponseFinal() {

        return HotelResponseDto.builder()
                .nombreUsuario("Cristian")
                .total(6300.0)
                .hotelReservationData(getReservationData())
                .build();

    }

    public static HotelReservationData getReservationData() {
        return HotelReservationData.builder()
                .fechaDesde(LocalDate.of(2022,02,10))
                .fechaHasta(LocalDate.of(2022,03,20))
                .destino("Puerto Iguazú")
                .codigoHotel("CH-0002")
                .cantidadPersonas(1)
                .tipoHabitacion("Single")
                .personas(List.of(getPersona()))
                .estado(StatusCodeObject.builder()
                        .code(200)
                        .mensaje("Ok")
                        .build())
                .build();

    }

}
