package com.Sprint1.Sprint1.controller;

import com.Sprint1.Sprint1.dto.request.HotelRequestDto;
import com.Sprint1.Sprint1.dto.response.HotelResponseDto;
import com.Sprint1.Sprint1.exception.FechasEquivocasException;
import com.Sprint1.Sprint1.model.HotelObject;
import com.Sprint1.Sprint1.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @PostMapping("/api/v1/booking")
    public HotelResponseDto reservaHotel(@RequestBody @Valid HotelRequestDto hotelRequestDto) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate fechaPartidaFormateada = LocalDate.parse(hotelRequestDto.getHotelReserva().getFechaDesde(), formatter);
        LocalDate fechaRegresoFormateada = LocalDate.parse(hotelRequestDto.getHotelReserva().getFechaHasta(), formatter);

        if (fechaPartidaFormateada.isBefore(fechaRegresoFormateada)) {
            return hotelService.hotelReservaImpl(hotelRequestDto);
        } else {
            throw new FechasEquivocasException();
        }
    }
}