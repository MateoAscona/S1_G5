package com.Sprint1.Sprint1.controller;

import com.Sprint1.Sprint1.dto.request.VueloRequestDto;
import com.Sprint1.Sprint1.dto.request.VueloReservaDto;
import com.Sprint1.Sprint1.dto.response.VueloResponseDto;
import com.Sprint1.Sprint1.model.HotelObject;
import com.Sprint1.Sprint1.model.VuelosObject;
import com.Sprint1.Sprint1.service.VuelosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class VuelosController {
    @Autowired
    VuelosService vuelosService;
    @GetMapping("/api/v1/flights")
    public List<VuelosObject> listarVuelos(){
        return vuelosService.listarVuelos();
    }

    @GetMapping("/api/v1/flights/buscar")
    public List<VuelosObject> buscarHotelPorFecha(@RequestParam String fechaPartida,
                                                 @RequestParam String fechaRegreso,
                                                 @RequestParam String destino) throws ParseException{

        return vuelosService.listarVuelosPorFechaDestino(fechaPartida, fechaRegreso, destino);
    }

    @PostMapping("/api/v1/flight-reservation")
    public VueloResponseDto reservarVuelo(@RequestBody VueloRequestDto vueloRequestDto){
        return vuelosService.reservarVueloImpl(vueloRequestDto);
    }

}
