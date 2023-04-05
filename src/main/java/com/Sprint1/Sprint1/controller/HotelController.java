package com.Sprint1.Sprint1.controller;

import com.Sprint1.Sprint1.dto.MessageDTO;
import com.Sprint1.Sprint1.dto.request.HotelDTO;
import com.Sprint1.Sprint1.dto.request.HotelReservaRequestDto;
import com.Sprint1.Sprint1.dto.response.HotelResponseDto;
import com.Sprint1.Sprint1.exception.FechasEquivocasException;
import com.Sprint1.Sprint1.exception.HotelNoEncontradoException;
import com.Sprint1.Sprint1.model.HotelObject;
import com.Sprint1.Sprint1.repository.IHotelRepository;
import com.Sprint1.Sprint1.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
public class HotelController {

    @Autowired
    HotelService hotelService;

    @GetMapping("/api/v1/hotels")
    public List<HotelObject> buscarHotelPorFecha(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate fechaPartida,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate fechaRegreso,
            @RequestParam(required = false) String destino) {

        if (fechaPartida == null && fechaRegreso == null) {
            return hotelService.listarHotelesPorFechaDestino(fechaPartida, fechaRegreso, destino);
        } else if (fechaPartida != null && fechaRegreso != null && fechaPartida.isBefore(fechaRegreso)) {
            return hotelService.listarHotelesPorFechaDestino(fechaPartida, fechaRegreso, destino);
        } else {
            throw new FechasEquivocasException();
        }
    }
//Por parametros pasar destino,cantidad de personas y valor
//  GET /api/hoteles?ubicacion=Madrid&fecha_entrada=2023-04-10&fecha_salida=2023-04-15&num_personas=2
 @GetMapping("/api/v1/hotels/busqueda")
  public List<HotelObject>buscarHoetelesDisponibles( @RequestParam String destino,@RequestParam Double precioMaximo,@RequestParam (required = true)Integer cantidadPersonas,@RequestParam String tipoDeHabitacion) {

     if (destino == null || precioMaximo == null || tipoDeHabitacion == null ) {
         throw new IllegalArgumentException(" destino, precio ,tipo de habitacion son obligatorios.");
     }
     return hotelService.buscarHotelesDisponibles(precioMaximo, cantidadPersonas, destino, tipoDeHabitacion);

 }



    @GetMapping("/api/v1/hotel-booking/")
    public List<HotelResponseDto> listarReservasHotel() {
        return hotelService.listarReservasHotel();
    }

    @PostMapping("/api/v1/hotels/new")
    public HotelDTO crearHotel(@RequestBody HotelDTO nuevoHotel) {
        return hotelService.nuevoHotel(nuevoHotel);
    }


    @PostMapping("/api/v1/hotel-booking/new")
    public HotelResponseDto reservaHotel(@RequestBody @Valid HotelReservaRequestDto hotelReservaRequestDto) {

        if (hotelReservaRequestDto.getHotelReservationData().getFechaDesde().isBefore(hotelReservaRequestDto.getHotelReservationData().getFechaHasta())) {
            return hotelService.hotelReservaImpl(hotelReservaRequestDto);
        } else {
            throw new FechasEquivocasException();
        }
    }

    @PutMapping("/api/v1/hotels/edit")
    public HotelDTO editarHotel(@RequestBody HotelDTO hotel) {
        return hotelService.actualizarHotel(hotel);
    }

    @PutMapping("/api/v1/hotel-booking/edit")
    public HotelResponseDto actualizarReserva(@RequestBody HotelResponseDto hotelReservaDto) {

        if (hotelReservaDto.getHotelReservationData().getFechaDesde().isBefore(hotelReservaDto.getHotelReservationData().getFechaHasta())) {
            return hotelService.actualizarReservaHotel(hotelReservaDto);
        } else {
            throw new FechasEquivocasException();
        }
    }

    @DeleteMapping("/api/v1/hotels/delete")
    public MessageDTO borrarHotel(@RequestParam Integer id) {
        return hotelService.borrarHotel(id);
    }

    @DeleteMapping("/api/v1/hotel-booking/delete")
    public MessageDTO borrarReservaHotel(@RequestParam Integer id) {
        return hotelService.borrarReservaHotel(id);
    }
}