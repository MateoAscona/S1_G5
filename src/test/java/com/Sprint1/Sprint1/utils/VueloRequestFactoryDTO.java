package com.Sprint1.Sprint1.utils;

import com.Sprint1.Sprint1.dto.request.*;

import java.util.List;

public class VueloRequestFactoryDTO {
    public static VueloRequestDto getVueloReserva() {

        return VueloRequestDto.builder()
                .nombreUsuario("Cristian")
                .vueloReserva(getReservaVueloDatos())
                .build();

    }
    public static VueloReservaDto getReservaVueloDatos(){
        return VueloReservaDto.builder()
                .fechaDesde("2022/02/10")
                .fechaHasta("2022/02/15")
                .origen("Buenos Aires")
                .destino("Puerto Iguazú")
                .codigoVuelo("BAPI-1235")
                .cantidadAsientos(1)
                .claseAsientos("Single")
                .personas(List.of(getPersona()))
                .metodoPago(getPagoDto())
                .build();

    }

    public static PersonasDto getPersona(){
        return PersonasDto.builder()
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
