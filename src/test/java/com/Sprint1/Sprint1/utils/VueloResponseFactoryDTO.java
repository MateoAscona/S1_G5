package com.Sprint1.Sprint1.utils;

import com.Sprint1.Sprint1.dto.response.*;

import java.util.List;

import static com.Sprint1.Sprint1.utils.HotelRequestFactoryDTO.getPersona;

public class VueloResponseFactoryDTO {
    public static VueloResponseDto getVueloResponse() {

        return VueloResponseDto.builder()
                .userName("Cristian")
                .total(6500.0)
                .vueloReservaResponseDto(getReservaResponse())
                .build();

    }

    public static VueloReservaResponseDto getReservaResponse() {
        return VueloReservaResponseDto.builder()
                .fechaDesde("2022/02/10")
                .fechaHasta("2022/02/15")
                .origen("Buenos Aires")
                .destino("Puerto Iguazú")
                .codigoVuelo("BAPI-1235")
                .cantidadAsientos(1)
                .claseAsiento("Single")
                .personas(List.of(getPersona()))
                .estado(StatusCodeDto.builder()
                        .code(200)
                        .mensaje("Funca")
                        .build())
                .build();

    }
}
