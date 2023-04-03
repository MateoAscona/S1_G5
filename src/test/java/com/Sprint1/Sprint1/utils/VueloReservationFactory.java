package com.Sprint1.Sprint1.utils;

import com.Sprint1.Sprint1.dto.response.VueloResponseDto;
import com.Sprint1.Sprint1.model.VuelosReservation;

import static com.Sprint1.Sprint1.utils.VueloResponseFactoryDTO.getReservaResponse;

public class VueloReservationFactory {

    public static VuelosReservation getVueloReservation() {

        return VuelosReservation.builder()
                .userName("Cristian")
                .total(6500.0)
                .vuelosReservationData(getReservaResponse())
                .build();

    }
}
