package com.Sprint1.Sprint1.utils;

import com.Sprint1.Sprint1.dto.request.HotelReservaRequestDto;
import com.Sprint1.Sprint1.dto.request.HotelReservationData;
import com.Sprint1.Sprint1.dto.request.MetodoPagoDto;
import com.Sprint1.Sprint1.dto.request.PersonasDto;
import com.Sprint1.Sprint1.model.PersonasObject;

import java.time.LocalDate;
import java.util.List;

public class HotelRequestFactoryDTO {

public static HotelReservaRequestDto getHotelReserva() {

    return HotelReservaRequestDto.builder()
            .nombreUsuario("Cristian")
            .hotelReservationData(getReservaHotelDatos())
            .build();

}
    public static HotelReservationData getReservaHotelDatos(){
        return HotelReservationData.builder()
                .fechaDesde(LocalDate.of(2022, 02, 10))
                .fechaHasta(LocalDate.of(2022, 03, 20))
                .destino("Puerto Iguazú")
                .codigoHotel("CH-0002")
                .cantidadPersonas(1)
                .tipoHabitacion("Single")
                .personas(List.of(getPersona()))
                .metodoPago(getPagoDto())
                .build();

    }

    public static PersonasObject getPersona(){
        return PersonasObject.builder()
                .dni("37575676")
                .nombre("Franco")
                .apellido("Ambort")
                .fechaNacimiento("7 Febrero 1994")
                .email("ambortfranco94@gmail.com")
                .build();


    }

    public static PersonasObject getPersonaFinal(){
        return PersonasObject.builder()
                .id(1)
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
