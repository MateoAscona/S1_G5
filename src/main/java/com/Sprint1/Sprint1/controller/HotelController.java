package com.Sprint1.Sprint1.controller;

import com.Sprint1.Sprint1.model.HotelObject;
import com.Sprint1.Sprint1.service.HotelService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class HotelController {
    @Autowired
    HotelService hotelService;


    @GetMapping("/api/v1/hotels")
    public List<HotelObject> listadoHoteles(){
    return hotelService.listarHoteles();
    }

    @GetMapping("/api/v1/hotels/buscar")
    public List<HotelObject> buscarHotelPorFecha(@RequestParam String fechaPartida,
                                                   @RequestParam String fechaRegreso,
                                                   @RequestParam String destino) throws ParseException {

        return hotelService.listarHotelesPorFechaDestino(fechaPartida, fechaRegreso, destino);
    }
}