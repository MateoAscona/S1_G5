package com.Sprint1.Sprint1.service;

import com.Sprint1.Sprint1.dto.request.PaqueteRequestDTO;
import com.Sprint1.Sprint1.dto.response.PaqueteResponseDTO;
import com.Sprint1.Sprint1.repository.IHotelRepository;
import com.Sprint1.Sprint1.repository.IVuelosRepository;
import com.Sprint1.Sprint1.utils.UtilMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AgenciaService {
    @Autowired
    IHotelRepository iHotelRepository;
    @Autowired
    IVuelosRepository iVuelosRepository;

    UtilMethods utilMethods = new UtilMethods();
    public PaqueteResponseDTO buscarVueloHotel(PaqueteRequestDTO busqueda) {

        utilMethods.existeDestino(busqueda.getDestino());

        PaqueteResponseDTO response = new PaqueteResponseDTO();

        response.setDestino(busqueda.getDestino());
        response.setFechaPartida(busqueda.getFechaPartida());
        response.setFechaRegreso(busqueda.getFechaRegreso());

        response.setHotelDisponible(iHotelRepository.findByFechasYDestino(
                busqueda.getFechaPartida(),
                busqueda.getFechaRegreso(),
                busqueda.getDestino()));

        response.setVueloDisponible(iVuelosRepository.findByFechasYDestino(
                busqueda.getFechaPartida(),
                busqueda.getFechaRegreso(),
                busqueda.getDestino()));

        return response;

    }
}
