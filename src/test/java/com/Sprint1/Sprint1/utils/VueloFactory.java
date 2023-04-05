package com.Sprint1.Sprint1.utils;

import com.Sprint1.Sprint1.exception.VueloNoEncontradoException;
import com.Sprint1.Sprint1.model.VuelosObject;

import java.time.LocalDate;

public class VueloFactory {
    public static VuelosObject getVuelo() {

        return VuelosObject.builder()
                .nroVuelo("BAPI-1235")
                .origen("Buenos Aires")
                .destino("Puerto Iguazú")
                .tipoAsiento("Economy")
                .precioPorPersona(6500.00)
                .fechaIda(LocalDate.of(2022, 2, 10))
                .fechaVuelta(LocalDate.of(2022, 2, 15))
                .build();


    }
    public static VuelosObject getVueloWithId() {

        return VuelosObject.builder()
                .id(1)
                .nroVuelo("BAPI-1235")
                .origen("Buenos Aires")
                .destino("Puerto Iguazú")
                .tipoAsiento("Economy")
                .precioPorPersona(6500.00)
                .fechaIda(LocalDate.of(2022, 2, 10))
                .fechaVuelta(LocalDate.of(2022, 2, 15))
                .build();

    }

    public static VuelosObject getVueloBusiness() {

        return VuelosObject.builder()
                .id(1)
                .nroVuelo("PIBA-1420")
                .origen("Puerto Iguazú")
                .destino("Bogotá")
                .tipoAsiento("Business")
                .precioPorPersona(43200.00)
                .fechaIda(LocalDate.of(2022, 2, 10))
                .fechaVuelta(LocalDate.of(2022, 2, 20))
                .build();


    }

}
