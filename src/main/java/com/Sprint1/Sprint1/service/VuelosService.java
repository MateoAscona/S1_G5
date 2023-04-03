package com.Sprint1.Sprint1.service;

import com.Sprint1.Sprint1.dto.request.VueloDTO;
import com.Sprint1.Sprint1.dto.request.VueloReservaRequestDto;
import com.Sprint1.Sprint1.dto.response.*;
import com.Sprint1.Sprint1.exception.SinParametrosException;
import com.Sprint1.Sprint1.exception.VueloNoEncontradoException;
import com.Sprint1.Sprint1.model.*;
import com.Sprint1.Sprint1.repository.IVuelosRepository;
import com.Sprint1.Sprint1.repository.IVuelosReservationRepository;
import com.Sprint1.Sprint1.repository.VuelosRepository;
import com.Sprint1.Sprint1.utils.UtilMethods;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VuelosService {
    @Autowired
    VuelosRepository vuelosRepository;

    @Autowired
    IVuelosRepository iVuelosRepository;

    @Autowired
    IVuelosReservationRepository iVuelosReservationRepository;

    ModelMapper mapper = new ModelMapper();

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

    public VueloResponseDto reservarVueloImpl(VueloReservaRequestDto vueloReservaRequestDto){

        VueloResponseDto respuestaFinal = new VueloResponseDto();
        Double precio = 0.0;

        utilMethods.existeDestinoDeVuelo(vueloReservaRequestDto.getVueloReservationData().getDestino());

        respuestaFinal.setUserName(vueloReservaRequestDto.getNombreUsuario());

        for (VuelosObject vuelos : iVuelosRepository.findAll()) {

            if(vuelos.getNroVuelo().equals(vueloReservaRequestDto.getVueloReservationData().getCodigoVuelo())){
                precio = vuelos.getPrecioPorPersona();
            }
        }
        respuestaFinal.setTotal(vueloReservaRequestDto.getVueloReservationData().getCantidadAsientos() * precio);

        VueloReservationData reserva2 = mapper.map(vueloReservaRequestDto.getVueloReservationData(), VueloReservationData.class);

        respuestaFinal.setVueloReservationData(reserva2);
        var entity = mapper.map(respuestaFinal, VuelosReservation.class);
        iVuelosReservationRepository.save(entity);

        return mapper.map(entity, VueloResponseDto.class);
    }

    public VueloDTO crearVuelo(VueloDTO nuevoVuelo) {
        var entity = mapper.map(nuevoVuelo, VuelosObject.class);

        iVuelosRepository.save(entity);

        return mapper.map(entity, VueloDTO.class);
    }

}
