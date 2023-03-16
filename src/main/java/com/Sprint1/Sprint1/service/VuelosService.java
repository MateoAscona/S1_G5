package com.Sprint1.Sprint1.service;

import com.Sprint1.Sprint1.dto.request.VueloRequestDto;
import com.Sprint1.Sprint1.dto.response.*;
import com.Sprint1.Sprint1.exception.SinParametrosException;
import com.Sprint1.Sprint1.exception.VueloNoEncontradoException;
import com.Sprint1.Sprint1.model.VuelosObject;
import com.Sprint1.Sprint1.repository.VuelosRepository;
import com.Sprint1.Sprint1.utils.UtilMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class VuelosService {
    @Autowired
    VuelosRepository vuelosRepository;

    UtilMethods utilMethods = new UtilMethods();

    public List<VuelosObject> listarVuelos() {
        return vuelosRepository.listaDeVuelos();
    }

    public List<VuelosObject> listarVuelosPorFechaDestino(
            LocalDate fechaPartida,
            LocalDate fechaRegreso,
            String destino)
            throws ParseException {

        if(fechaPartida == null && fechaRegreso == null && destino == null){
            return vuelosRepository.listaDeVuelos();
        } else if (fechaPartida == null || fechaRegreso == null || destino == null) {
            throw new SinParametrosException();
        }

        utilMethods.existeDestinoDeVuelo(destino);

        List<VuelosObject> vuelosBuscados = new ArrayList<>();

        for (VuelosObject vuelos : vuelosRepository.listaDeVuelos()) {
            if (fechaPartida.plusDays(1).isAfter(vuelos.getFechaIda()) &&
                    fechaRegreso.minusDays(1).isBefore(vuelos.getFechaVuelta()) &&
                    destino.equals(vuelos.getDestino())) {
                vuelosBuscados.add(vuelos);
            }
        }

        if(vuelosBuscados.isEmpty()) {
            throw new VueloNoEncontradoException();
        }

        return vuelosBuscados;
    }

    public VueloResponseDto reservarVueloImpl(VueloRequestDto vueloRequestDto){

        VueloResponseDto respuestaFinal = new VueloResponseDto();
        Double precio = 0.0;

        utilMethods.existeDestinoDeVuelo(vueloRequestDto.getVueloReserva().getDestino());

        respuestaFinal.setUserName(vueloRequestDto.getNombreUsuario());

        for (VuelosObject vuelos : vuelosRepository.getVuelosCargados()) {

            if(vuelos.getNroVuelo().equals(vueloRequestDto.getVueloReserva().getCodigoVuelo())){
                precio = vuelos.getPrecioPorPersona();
            }
        }
        respuestaFinal.setTotal(vueloRequestDto.getVueloReserva().getCantidadAsientos() * precio);

        VueloReservaResponseDto reserva = new VueloReservaResponseDto();

        reserva.setFechaDesde(vueloRequestDto.getVueloReserva().getFechaDesde());
        reserva.setFechaHasta(vueloRequestDto.getVueloReserva().getFechaHasta());
        reserva.setOrigen(vueloRequestDto.getVueloReserva().getOrigen());
        reserva.setDestino(vueloRequestDto.getVueloReserva().getDestino());
        reserva.setCodigoVuelo(vueloRequestDto.getVueloReserva().getCodigoVuelo());
        reserva.setCantidadAsientos(vueloRequestDto.getVueloReserva().getCantidadAsientos());
        reserva.setClaseAsiento(vueloRequestDto.getVueloReserva().getClaseAsientos());
        reserva.setPersonas(vueloRequestDto.getVueloReserva().getPersonas());

        reserva.setEstado(new StatusCodeDto(200,"Funca"));

        respuestaFinal.setVueloReservaResponseDto(reserva);

        return respuestaFinal;
    }
}
