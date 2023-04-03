package com.Sprint1.Sprint1.utils;

import com.Sprint1.Sprint1.dto.response.HotelResponseDto;
import com.Sprint1.Sprint1.model.HotelReservation;

import static com.Sprint1.Sprint1.utils.HotelResponseFactoryDTO.getReservationData;

public class HotelReservationFactory {

    public static HotelReservation getHotelReservation() {

        return HotelReservation.builder()
                .nombreUsuario("Cristian")
                .total(6300.0)
                .hotelReservationData(getReservationData())
                .build();

    }
}
