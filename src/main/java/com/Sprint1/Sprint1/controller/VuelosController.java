package com.Sprint1.Sprint1.controller;

import com.Sprint1.Sprint1.model.HotelObject;
import com.Sprint1.Sprint1.model.VuelosObject;
import com.Sprint1.Sprint1.service.VuelosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
