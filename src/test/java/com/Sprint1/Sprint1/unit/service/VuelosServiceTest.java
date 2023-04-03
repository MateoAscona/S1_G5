package com.Sprint1.Sprint1.unit.service;

import com.Sprint1.Sprint1.dto.request.VueloReservaRequestDto;
import com.Sprint1.Sprint1.dto.response.VueloResponseDto;
import com.Sprint1.Sprint1.exception.VueloNoEncontradoException;
import com.Sprint1.Sprint1.model.VuelosObject;
import com.Sprint1.Sprint1.model.VuelosReservation;
import com.Sprint1.Sprint1.repository.IVuelosRepository;
import com.Sprint1.Sprint1.repository.IVuelosReservationRepository;
import com.Sprint1.Sprint1.repository.VuelosRepository;
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
import java.util.List;
@ExtendWith(MockitoExtension.class)
public class VuelosServiceTest {

    @Mock
    IVuelosRepository vuelosRepository;

    @Mock
    IVuelosReservationRepository vuelosReservationRepository;

    @InjectMocks
    VuelosService vuelosService;

    @Test
    public void buscarVueloPorFechaTest () throws ParseException {
        // arrange
        List<VuelosObject> expected = List.of(VueloFactory.getVuelo());

        LocalDate fechaPartida = LocalDate.parse("2022-02-10");
        LocalDate fechaRegreso = LocalDate.parse("2022-02-15");
        String destino = "Puerto Iguazú";

        // art
        Mockito.when(vuelosRepository.findByFechasYDestino(fechaPartida, fechaRegreso, destino))
                .thenReturn(expected);
        var result = vuelosService.listarVuelosPorFechaDestino(fechaPartida, fechaRegreso, destino);

        // acert
        Assertions.assertEquals(expected, result);
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
    public void vueloReservaImplTest(){
        //arrange
        VueloResponseDto expected = VueloResponseFactoryDTO.getVueloResponse();
        VueloReservaRequestDto vuelo = VueloRequestFactoryDTO.getVueloReserva();
        VuelosReservation reserva = VueloReservationFactory.getVueloReservation();

        //act
        Mockito.when(vuelosRepository.findAll()).thenReturn(List.of(VueloFactory.getVuelo()));
        Mockito.when(vuelosReservationRepository.save(reserva)).thenReturn(reserva);
        var result = vuelosService.reservarVueloImpl(vuelo);

        //assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void vueloReservaImplExceptionTest(){
        //arrange
        VueloReservaRequestDto vuelo = VueloRequestFactoryDTO.getVueloReserva();
        vuelo.getVueloReservationData().setDestino("a");

        //act and assert
        Assertions.assertThrows(VueloNoEncontradoException.class,
                ()-> vuelosService.reservarVueloImpl(vuelo));
    }
}

