package com.Sprint1.Sprint1.controller;

import com.Sprint1.Sprint1.dto.request.VueloDTO;
import com.Sprint1.Sprint1.dto.request.VueloReservaRequestDto;
import com.Sprint1.Sprint1.dto.response.VueloResponseDto;
import com.Sprint1.Sprint1.exception.FechasEquivocasException;
import com.Sprint1.Sprint1.model.VuelosObject;
import com.Sprint1.Sprint1.service.VuelosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class VuelosController {
    @Autowired
    VuelosService vuelosService;

    @GetMapping("/api/v1/flights")
    public List<VuelosObject> buscarVueloPorFecha(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate fechaPartida,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate fechaRegreso,
            @RequestParam(required = false) String destino) throws ParseException {
        if (fechaPartida == null && fechaRegreso == null) {
            return vuelosService.listarVuelosPorFechaDestino(fechaPartida, fechaRegreso, destino);
        } else if (fechaPartida != null && fechaRegreso != null && fechaPartida.isBefore(fechaRegreso)) {
            return vuelosService.listarVuelosPorFechaDestino(fechaPartida, fechaRegreso, destino);
        } else {
            throw new FechasEquivocasException();
        }
    }

    @PostMapping("/api/v1/flight-reservation")
    public VueloResponseDto reservarVuelo(@RequestBody @Valid VueloReservaRequestDto vueloReservaRequestDto) {

        if (vueloReservaRequestDto.getVueloReservationData().getFechaDesde().isBefore(vueloReservaRequestDto.getVueloReservationData().getFechaHasta())) {
            return vuelosService.reservarVueloImpl(vueloReservaRequestDto);
        } else {
            throw new FechasEquivocasException();
        }
    }

    @PostMapping("/api/v1/flights/new")
    public VueloDTO crearVuelo(@RequestBody VueloDTO nuevoVuelo) {
        return vuelosService.crearVuelo(nuevoVuelo);
    }

}
