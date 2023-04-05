package com.Sprint1.Sprint1.utils;

import com.Sprint1.Sprint1.model.HotelObject;

import java.time.LocalDate;

public class HotelFactory {
    public static HotelObject getHotel() {

        return HotelObject.builder()
                .codigoHotel("CH-0002")
                .nombre("Cataratas Hotel")
                .lugarCiudad("Puerto Iguazú")
                .tipoDeHabitacion("Doble")
                .precioPorNoche(6300.00)
                .disponibleDesde(LocalDate.of(2022, 2, 10))
                .disponibleHasta(LocalDate.of(2022, 3, 20))
                .reservado(false)
                .build();

    }
    public static HotelObject getHotelWithId() {

        return HotelObject.builder()
                .id(1)
                .codigoHotel("CH-0002")
                .nombre("Cataratas Hotel")
                .lugarCiudad("Puerto Iguazú")
                .tipoDeHabitacion("Doble")
                .precioPorNoche(6300.00)
                .disponibleDesde(LocalDate.of(2022, 2, 10))
                .disponibleHasta(LocalDate.of(2022, 3, 20))
                .reservado(false)
                .build();

    }

    public static HotelObject getHotelWithId2() {

        return HotelObject.builder()
                .id(2)
                .codigoHotel("CH-0003")
                .nombre("Cataratas Hotel 2")
                .lugarCiudad("Puerto Iguazú")
                .tipoDeHabitacion("Triple")
                .precioPorNoche(8200.00)
                .disponibleDesde(LocalDate.of(2022, 2, 10))
                .disponibleHasta(LocalDate.of(2022, 3, 19))
                .reservado(false)
                .build();

    }

}
