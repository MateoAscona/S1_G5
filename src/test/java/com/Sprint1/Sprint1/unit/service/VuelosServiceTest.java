package com.Sprint1.Sprint1.unit.service;

import com.Sprint1.Sprint1.dto.request.VueloRequestDto;
import com.Sprint1.Sprint1.dto.response.VueloResponseDto;
import com.Sprint1.Sprint1.exception.VueloNoEncontradoException;
import com.Sprint1.Sprint1.model.VuelosObject;
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
    VuelosRepository vuelosRepository;

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
        Mockito.when(vuelosRepository.listaDeVuelos()).thenReturn(expected);
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
        VueloRequestDto vuelo = VueloRequestFactoryDTO.getVueloReserva();
        //act
        Mockito.when(vuelosRepository.getVuelosCargados()).thenReturn(List.of(VueloFactory.getVuelo()));
        var result = vuelosService.reservarVueloImpl(vuelo);

        //assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void vueloReservaImplExceptionTest(){
        //arrange
        VueloRequestDto vuelo = VueloRequestFactoryDTO.getVueloReserva();
        vuelo.getVueloReserva().setDestino("a");

        //act and assert
        Assertions.assertThrows(VueloNoEncontradoException.class,
                ()-> vuelosService.reservarVueloImpl(vuelo));
    }
}

