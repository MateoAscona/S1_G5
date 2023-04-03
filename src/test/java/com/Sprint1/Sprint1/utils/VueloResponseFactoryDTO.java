package com.Sprint1.Sprint1.utils;

import com.Sprint1.Sprint1.dto.request.VueloReservationData;
import com.Sprint1.Sprint1.dto.response.*;
import com.Sprint1.Sprint1.model.StatusCodeObject;
import com.Sprint1.Sprint1.model.VuelosReservation;
import com.Sprint1.Sprint1.model.VuelosReservationData;

import java.time.LocalDate;
import java.util.List;

import static com.Sprint1.Sprint1.utils.HotelRequestFactoryDTO.getPersona;

public class VueloResponseFactoryDTO {
    public static VueloResponseDto getVueloResponse() {

        return VueloResponseDto.builder()
                .userName("Cristian")
                .total(6500.0)
                .vuelosReservationData(getReservaResponse())
                .build();

    }

    public static VuelosReservationData getReservaResponse() {
        return VuelosReservationData.builder()
                .fechaDesde(LocalDate.of(2022, 02, 10))
                .fechaHasta(LocalDate.of(2022, 02, 15))
                .origen("Buenos Aires")
                .destino("Puerto Iguazú")
                .codigoVuelo("BAPI-1235")
                .cantidadAsientos(1)
                .claseAsiento("Doble")
                .personas(List.of(getPersona()))
                .estado(StatusCodeObject.builder()
                        .code(200)
                        .mensaje("Ok")
                        .build())
                .build();

    }
}
