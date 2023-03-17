package com.Sprint1.Sprint1.unit.service;

import com.Sprint1.Sprint1.model.HotelObject;
import com.Sprint1.Sprint1.repository.HotelRepository;
import com.Sprint1.Sprint1.service.HotelService;
import com.Sprint1.Sprint1.utils.HotelFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class HotelSerivceTest {


    @Mock
    HotelRepository hotelRepository;

    @InjectMocks
    HotelService hotelService;


    @Test
    public void listarHotelesPorFechaDestinoTest () {
        // arrange
        List<HotelObject> expected = List.of(HotelFactory.getHotel());
        LocalDate fechaPartida = LocalDate.parse("2022-02-10");
        LocalDate fechaRegreso = LocalDate.parse("2022-03-20");
        String destino = "Puerto Iguazú";

        // art
        Mockito.when(hotelRepository.listaDeHoteles()).thenReturn(expected);
        var result = hotelService.listarHotelesPorFechaDestino(fechaPartida, fechaRegreso, destino);

        // acert
        Assertions.assertEquals(expected, result);
    }
}
