package com.Sprint1.Sprint1.controller;

import com.Sprint1.Sprint1.dto.request.PaqueteRequestDTO;
import com.Sprint1.Sprint1.dto.response.PaqueteResponseDTO;
import com.Sprint1.Sprint1.exception.FechasEquivocasException;
import com.Sprint1.Sprint1.model.HotelObject;
import com.Sprint1.Sprint1.model.VuelosObject;
import com.Sprint1.Sprint1.service.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AgenciaController {
    @Autowired
    AgenciaService agenciaService;
    @PostMapping("/api/v1/pack")
    public PaqueteResponseDTO comprarPaqueteViaje(@RequestBody PaqueteRequestDTO busqueda){
        if (busqueda.getFechaPartida().isBefore(busqueda.getFechaRegreso())) {
            return agenciaService.buscarVueloHotel(busqueda);
        } else {
            throw new FechasEquivocasException();
        }
    }
}
