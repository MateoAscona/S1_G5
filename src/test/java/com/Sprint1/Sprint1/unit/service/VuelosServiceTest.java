package com.Sprint1.Sprint1.unit.service;

import com.Sprint1.Sprint1.dto.request.VueloReservaRequestDto;
import com.Sprint1.Sprint1.dto.response.VueloResponseDto;
import com.Sprint1.Sprint1.exception.VueloNoEncontradoException;

import com.Sprint1.Sprint1.model.VuelosObject;
import com.Sprint1.Sprint1.model.VuelosReservation;
import com.Sprint1.Sprint1.repository.IVuelosRepository;
import com.Sprint1.Sprint1.repository.IVuelosReservationRepository;
import com.Sprint1.Sprint1.service.VuelosService;
import com.Sprint1.Sprint1.utils.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VuelosServiceTest {

    @Mock
    IVuelosRepository vuelosRepository;

    @Mock
    IVuelosReservationRepository vuelosReservationRepository;

    @InjectMocks
    VuelosService vuelosService;

    @Test
    public void buscarVueloPorFechaTest() throws ParseException {
        // arrange
        List<VuelosObject> expected = List.of(VueloFactory.getVuelo());

        LocalDate fechaPartida = LocalDate.parse("2022-02-10");
        LocalDate fechaRegreso = LocalDate.parse("2022-02-15");
        String destino = "Puerto Iguazú";

        // art
        when(vuelosRepository.findByFechasYDestino(fechaPartida, fechaRegreso, destino))
                .thenReturn(expected);
        var result = vuelosService.listarVuelosPorFechaDestino(fechaPartida, fechaRegreso, destino);

        // acert
        assertEquals(expected, result);
    }

    @Test
    public void buscarVueloPorFechaExceptionTest() {
        // arrange
        List<VuelosObject> expected = List.of(VueloFactory.getVuelo());

        LocalDate fechaPartida = LocalDate.parse("2022-02-10");
        LocalDate fechaRegreso = LocalDate.parse("2022-02-15");
        String destino = "Rafaela";

        // act  and acert
        Assertions.assertThrows(VueloNoEncontradoException.class,
                () -> vuelosService.listarVuelosPorFechaDestino(fechaPartida, fechaRegreso, destino));
    }

    @Test
    public void vueloReservaImplTest() {
        //arrange
        VueloResponseDto expected = VueloResponseFactoryDTO.getVueloResponse();
        VueloReservaRequestDto vuelo = VueloRequestFactoryDTO.getVueloReserva();
        VuelosReservation reserva = VueloReservationFactory.getVueloReservation();

        //act
        when(vuelosRepository.findAll()).thenReturn(List.of(VueloFactory.getVuelo()));
        when(vuelosReservationRepository.save(reserva)).thenReturn(reserva);
        var result = vuelosService.reservarVueloImpl(vuelo);

        //assert
        assertEquals(expected, result);
    }

    @Test
    public void vueloReservaImplExceptionTest() {
        //arrange
        VueloReservaRequestDto vuelo = VueloRequestFactoryDTO.getVueloReserva();
        vuelo.getVueloReservationData().setDestino("a");

        //act and assert
        Assertions.assertThrows(VueloNoEncontradoException.class,
                () -> vuelosService.reservarVueloImpl(vuelo));
    }


    @Test
    public void obtenerVuelosBusinessTest() {
        //arrange
        List<VuelosObject> expected = List.of(VueloFactory.getVueloBusiness());
        String tipoAsiento = "ECONOMY";

        // act  and acert
        Assertions.assertThrows(VueloNoEncontradoException.class,
                () -> vuelosService.obtenerVuelosBusiness());

    }
}

    //devuelve una lista de vuelos de tipo Business cuando se proporcionan datos de entrada válidos
  /*  @Test
    public void obtenerVuelosBusinessConDatosValidosTest() {

        // arrange:Crear un mock object del repositorio que devuelve una lista de vuelos de tipo Business

        List<VuelosObject> expectedvuelosBusiness = List.of(VueloFactory.getVueloBusiness());
        //act
        Mockito.when(IVuelosRepository.obtenerVuelosBusiness().thenReturn(expectedvuelosBusiness));
        // Llamar al método y verificar que devuelve la lista de vuelos de tipo Business
        var result = vuelosService.obtenerVuelosBusiness();
        //assert:
        Assertions.assertEquals(expectedvuelosBusiness, result);

    }
}*/




