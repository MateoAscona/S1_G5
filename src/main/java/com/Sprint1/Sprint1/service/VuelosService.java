package com.Sprint1.Sprint1.service;

import com.Sprint1.Sprint1.dto.MessageDTO;
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

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        if (fechaPartida == null && fechaRegreso == null && destino == null) {
            return iVuelosRepository.findAll();
        } else if (fechaPartida == null || fechaRegreso == null || destino == null) {
            throw new SinParametrosException();
        }

        utilMethods.existeDestinoDeVuelo(destino);

        List<VuelosObject> vuelosBuscados = iVuelosRepository
                .findByFechasYDestino(fechaPartida, fechaRegreso, destino);

        if (vuelosBuscados.isEmpty()) {
            throw new VueloNoEncontradoException();
        }

        return vuelosBuscados;
    }

    public List<VueloResponseDto> listarReservas() {
        List<VueloResponseDto> lista = new ArrayList<>();

        for (VuelosReservation reserva : iVuelosReservationRepository.findAll()) {
            lista.add(mapper.map(reserva, VueloResponseDto.class));
        }
        return lista;
    }

    public VueloDTO crearVuelo(VueloDTO nuevoVuelo) {
        var entity = mapper.map(nuevoVuelo, VuelosObject.class);

        iVuelosRepository.save(entity);

        return mapper.map(entity, VueloDTO.class);
    }

    public VueloResponseDto reservarVueloImpl(VueloReservaRequestDto vueloReservaRequestDto) {

        VueloResponseDto reservaAGuardar = new VueloResponseDto();
        Double precio = 0.0;

        utilMethods.existeDestinoDeVuelo(vueloReservaRequestDto.getVueloReservationData().getDestino());

        reservaAGuardar.setUserName(vueloReservaRequestDto.getNombreUsuario());

        for (VuelosObject vuelos : iVuelosRepository.findAll()) {

            if (vuelos.getNroVuelo().equals(vueloReservaRequestDto
                    .getVueloReservationData()
                    .getCodigoVuelo())) {
                precio = vuelos.getPrecioPorPersona();
            }
        }
        reservaAGuardar.setTotal(vueloReservaRequestDto
                .getVueloReservationData()
                .getCantidadAsientos() * precio);

        VuelosReservationData reservaDatos = mapper.map(vueloReservaRequestDto
                .getVueloReservationData(), VuelosReservationData.class);

        reservaDatos.setEstado(StatusCodeObject.builder().code(200).mensaje("Ok").build());

        reservaAGuardar.setVuelosReservationData(reservaDatos);

        var entity = mapper.map(reservaAGuardar, VuelosReservation.class);

        iVuelosReservationRepository.save(entity);

        return mapper.map(entity, VueloResponseDto.class);
    }

    public VueloDTO actualizarVuelo(VueloDTO vuelo) {
        if (iVuelosRepository.existsById(vuelo.getId())) {

            var entity = mapper.map(vuelo, VuelosObject.class);

            iVuelosRepository.save(entity);

            return mapper.map(entity, VueloDTO.class);
        } else {
            throw new VueloNoEncontradoException();
        }
    }

    public VueloResponseDto actualizarReservaVuelo(VueloResponseDto vuelo) {
        if (iVuelosReservationRepository.existsById(vuelo.getId())) {

            var entity = mapper.map(vuelo, VuelosReservation.class);

            iVuelosReservationRepository.save(entity);

            return mapper.map(entity, VueloResponseDto.class);
        } else {
            throw new VueloNoEncontradoException();
        }
    }

    public MessageDTO borrarVuelo(Integer id) {
        if (iVuelosRepository.existsById(id)) {

            var entity = mapper.map(iVuelosRepository.findById(id), VuelosObject.class);

            iVuelosRepository.delete(entity);

            return MessageDTO.builder()
                    .message("Se elimino el vuelo con id " + id)
                    .action("DELETE")
                    .build();
        } else {
            throw new VueloNoEncontradoException();
        }
    }

    public MessageDTO borrarReservaVuelo(Integer id) {
        if (iVuelosReservationRepository.existsById(id)) {

            var entity = mapper.map(iVuelosReservationRepository.findById(id), VuelosReservation.class);

            iVuelosReservationRepository.delete(entity);

            return MessageDTO.builder()
                    .message("Se elimino la reserva con id " + id)
                    .action("DELETE")
                    .build();
        } else {
            throw new VueloNoEncontradoException();
        }
    }

    public List<VuelosObject> obtenerVuelosBusiness() {
        return Optional.ofNullable(iVuelosRepository.obtenerVuelosBusiness())
                .filter(vuelos -> !vuelos.isEmpty())
                .orElseThrow(() -> new VueloNoEncontradoException());
    }

}