package com.Sprint1.Sprint1.controller;

import com.Sprint1.Sprint1.dto.request.HotelRequestDto;
import com.Sprint1.Sprint1.dto.response.HotelResponseDto;
import com.Sprint1.Sprint1.model.HotelObject;
import com.Sprint1.Sprint1.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController

public class HotelController {
    @Autowired
    HotelService hotelService;


    @GetMapping("/api/v1/hotels")

    public List<HotelObject> buscarHotelPorFecha(@RequestParam(required = false) String fechaPartida,
                                                   @RequestParam(required = false) String fechaRegreso,
                                                   @RequestParam(required = false) String destino) throws ParseException {

        return hotelService.listarHotelesPorFechaDestino(fechaPartida, fechaRegreso, destino);


    }

    @PostMapping("/api/v1/booking")
    public HotelResponseDto reservaHotel(@RequestBody @Valid HotelRequestDto hotelRequestDto)  {

        return hotelService.hotelReservaImpl(hotelRequestDto);
    }
}