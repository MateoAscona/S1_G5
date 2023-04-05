package com.Sprint1.Sprint1.unit.service;

import com.Sprint1.Sprint1.dto.request.HotelReservaRequestDto;
import com.Sprint1.Sprint1.dto.response.HotelResponseDto;
import com.Sprint1.Sprint1.exception.HotelNoEncontradoException;
import com.Sprint1.Sprint1.model.HotelObject;
import com.Sprint1.Sprint1.model.HotelReservation;
import com.Sprint1.Sprint1.repository.HotelRepository;
import com.Sprint1.Sprint1.repository.IHotelRepository;
import com.Sprint1.Sprint1.repository.IHotelReservationRepository;
import com.Sprint1.Sprint1.service.HotelService;
import com.Sprint1.Sprint1.utils.HotelFactory;
import com.Sprint1.Sprint1.utils.HotelRequestFactoryDTO;
import com.Sprint1.Sprint1.utils.HotelReservationFactory;
import com.Sprint1.Sprint1.utils.HotelResponseFactoryDTO;
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
public class HotelServiceTest {


    @Mock
    IHotelRepository hotelRepository;

    @Mock
    IHotelReservationRepository hotelReservationRepository;

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
        Mockito.when(hotelRepository.findByFechasYDestino(fechaPartida, fechaRegreso, destino))
                .thenReturn(expected);
        var result = hotelService.listarHotelesPorFechaDestino(fechaPartida, fechaRegreso, destino);

        // acert
        Assertions.assertEquals(expected, result);
    }
    @Test
    public void listarHotelesPorFechaDestinoExceptionTest () {
        // arrange
        List<HotelObject> expected = List.of(HotelFactory.getHotel());
        LocalDate fechaPartida = LocalDate.parse("2022-02-10");
        LocalDate fechaRegreso = LocalDate.parse("2022-03-20");
        String destino = "Rafaela";


        // act  and acert
        Assertions.assertThrows(HotelNoEncontradoException.class,
                () -> hotelService.listarHotelesPorFechaDestino(fechaPartida, fechaRegreso, destino) );
    }

    @Test

    public void hotelReservaImplTest(){
        //arrange
        HotelResponseDto expected = HotelResponseFactoryDTO.getHotelResponseFinal();
        HotelReservaRequestDto hotel = HotelRequestFactoryDTO.getHotelReserva();
        HotelReservation reserva = HotelReservationFactory.getHotelReservation();

        //act
        Mockito.when(hotelRepository.findAll()).thenReturn(List.of(HotelFactory.getHotel()));
        Mockito.when(hotelReservationRepository.save(reserva)).thenReturn(reserva);
        var result = hotelService.hotelReservaImpl(hotel);
        //assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void hotelReservaImplExceptionTest(){
        //arrange
        HotelReservaRequestDto hotel = HotelRequestFactoryDTO.getHotelReserva();
        hotel.getHotelReservationData().setDestino("Rafaela");

        //act and assert
        Assertions.assertThrows(HotelNoEncontradoException.class,
                ()-> hotelService.hotelReservaImpl(hotel));

    }











    @Test
    public void findAllByReservadoFalseOrderByPrecioPorNocheAscTest() {
        // arrange
        List<HotelObject> expected = List.of(HotelFactory.getHotel());

        String destino = "Puerto Iguazú";

        // art
        Mockito.when(hotelRepository.findCheapestHotels(destino))
                .thenReturn(expected);
        var result = hotelService.findAllByReservadoFalseOrderByPrecioPorNocheAsc(destino);

        // acert
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void findAllByReservadoFalseOrderByPrecioPorNocheAscExceptionTest() {
        // arrange
        List<HotelObject> expected = List.of(HotelFactory.getHotel());

        String destino = "Rafaela";


        // act  and acert
        Assertions.assertThrows(HotelNoEncontradoException.class,
                () -> hotelService.findAllByReservadoFalseOrderByPrecioPorNocheAsc(destino) );
    }
}
