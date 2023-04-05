package com.Sprint1.Sprint1.utils;

import com.Sprint1.Sprint1.dto.response.HotelReservaResponseDto;
import com.Sprint1.Sprint1.dto.response.HotelResponseDto;
import com.Sprint1.Sprint1.dto.response.StatusCodeDto;
import com.Sprint1.Sprint1.model.HotelReservationData;
import com.Sprint1.Sprint1.model.StatusCodeObject;


import java.time.LocalDate;
import java.util.List;

import static com.Sprint1.Sprint1.utils.HotelRequestFactoryDTO.getPersona;
import static com.Sprint1.Sprint1.utils.HotelRequestFactoryDTO.getPersonaFinal;

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
                .id(1)
                .nombreUsuario("Cristian")
                .total(6300.0)
                .hotelReservationData(getReservationDataFinal())
                .build();

    }

    public static HotelReservationData getReservationData() {
        return HotelReservationData.builder()
                .fechaDesde(LocalDate.of(2022,02,10))
                .fechaHasta(LocalDate.of(2022,03,20))
                .destino("Puerto Iguazú")
                .codigoHotel("CH-0002")
                .cantidadPersonas(1)
                .tipoDeHabitacion("Single")
                .personas(List.of(getPersona()))
                .estado(StatusCodeObject.builder()
                        .code(200)
                        .mensaje("Ok")
                        .build())
                .build();

    }
    public static HotelReservationData getReservationDataFinal() {
        return HotelReservationData.builder()
                .id(1)
                .fechaDesde(LocalDate.of(2022,02,10))
                .fechaHasta(LocalDate.of(2022,03,20))
                .destino("Puerto Iguazú")
                .codigoHotel("CH-0002")
                .cantidadPersonas(1)
                .tipoDeHabitacion("Single")
                .personas(List.of(getPersonaFinal()))
                .estado(StatusCodeObject.builder()
                        .id(1)
                        .code(200)
                        .mensaje("Ok")
                        .build())
                .build();

    }

}
