package com.Sprint1.Sprint1.service;

import com.Sprint1.Sprint1.dto.MessageDTO;
import com.Sprint1.Sprint1.dto.request.HotelDTO;
import com.Sprint1.Sprint1.dto.request.HotelReservaRequestDto;
import com.Sprint1.Sprint1.dto.response.HotelResponseDto;
import com.Sprint1.Sprint1.exception.HotelNoEncontradoException;
import com.Sprint1.Sprint1.exception.SinParametrosException;
import com.Sprint1.Sprint1.model.HotelObject;
import com.Sprint1.Sprint1.model.HotelReservation;
import com.Sprint1.Sprint1.model.HotelReservationData;
import com.Sprint1.Sprint1.model.StatusCodeObject;
import com.Sprint1.Sprint1.repository.HotelRepository;
import com.Sprint1.Sprint1.repository.IHotelRepository;
import com.Sprint1.Sprint1.repository.IHotelReservationRepository;
import com.Sprint1.Sprint1.utils.UtilMethods;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {

    @Autowired
    IHotelRepository iHotelRepository;

    @Autowired
    IHotelReservationRepository iHotelReservationRepository;

    ModelMapper mapper = new ModelMapper();

    UtilMethods utilMethods = new UtilMethods();

    public List<HotelObject> listarHotelesPorFechaDestino(LocalDate fechaPartida, LocalDate fechaRegreso, String destino) {

        if (fechaPartida == null && fechaRegreso == null && destino == null) {
            return iHotelRepository.findAll();
        } else if (fechaPartida == null || fechaRegreso == null || destino == null) {
            throw new SinParametrosException();
        }

        utilMethods.existeDestino(destino);

        List<HotelObject> hotelesBuscados;

        hotelesBuscados = iHotelRepository.findByFechasYDestino(fechaPartida, fechaRegreso, destino);

        if (hotelesBuscados.isEmpty()) {
            throw new HotelNoEncontradoException();
        }
        return hotelesBuscados;
    }

    public List<HotelObject> findAllByReservadoFalseOrderByPrecioPorNocheAsc(String destino) {

        if (destino == null) {
            throw new SinParametrosException();
        }


        utilMethods.existeDestino(destino);

        List<HotelObject> hotelesBuscadosPorPrecioAsc;

        hotelesBuscadosPorPrecioAsc = iHotelRepository.findCheapestHotels(destino);

        if (hotelesBuscadosPorPrecioAsc.isEmpty()) {
            throw new HotelNoEncontradoException();
        }

        return hotelesBuscadosPorPrecioAsc;


    }


    public List<HotelResponseDto> listarReservasHotel() {
        List<HotelResponseDto> lista = new ArrayList<>();

        for (HotelReservation reserva : iHotelReservationRepository.findAll()) {
            lista.add(mapper.map(reserva, HotelResponseDto.class));
        }
        return lista;
    }

    public HotelDTO nuevoHotel(HotelDTO hotelDTO) {

        var entity = mapper.map(hotelDTO, HotelObject.class);

        iHotelRepository.save(entity);

        return mapper.map(entity, HotelDTO.class);
    }

    public HotelResponseDto hotelReservaImpl(HotelReservaRequestDto hotelReservaRequestDto) {

        HotelResponseDto reservaAGuardar = new HotelResponseDto();
        Double precio = 0.0;

        utilMethods.existeDestino(hotelReservaRequestDto.getHotelReservationData().getDestino());

        utilMethods.relacionPersonasHabitaciones(
                hotelReservaRequestDto.getHotelReservationData().getTipoHabitacion(),
                hotelReservaRequestDto.getHotelReservationData().getCantidadPersonas());


        reservaAGuardar.setNombreUsuario(hotelReservaRequestDto.getNombreUsuario());

        for (HotelObject hotel : iHotelRepository.findAll()) {
            if (hotel.getCodigoHotel().equals(hotelReservaRequestDto.getHotelReservationData().getCodigoHotel())) {
                precio = hotel.getPrecioPorNoche();
            }
        }
        reservaAGuardar.setTotal(hotelReservaRequestDto.getHotelReservationData().getCantidadPersonas() * precio);

        HotelReservationData reserva = mapper.map(hotelReservaRequestDto.getHotelReservationData(), HotelReservationData.class);

        reserva.setEstado(StatusCodeObject.builder().code(200).mensaje("Ok").build());

        reservaAGuardar.setHotelReservationData(reserva);

        var entity = mapper.map(reservaAGuardar, HotelReservation.class);

        iHotelReservationRepository.save(entity);

        return mapper.map(entity, HotelResponseDto.class);
    }

    public HotelDTO actualizarHotel(HotelDTO hotel) {

        if (iHotelRepository.existsById(hotel.getId())) {

            var entity = mapper.map(hotel, HotelObject.class);

            iHotelRepository.save(entity);

            return mapper.map(entity, HotelDTO.class);
        } else {
            throw new HotelNoEncontradoException();
        }
    }

    public HotelResponseDto actualizarReservaHotel(HotelResponseDto hotelReservaDto) {

        if (iHotelRepository.existsById(hotelReservaDto.getId())) {

            var entity = mapper.map(hotelReservaDto, HotelReservation.class);

            iHotelReservationRepository.save(entity);

            return mapper.map(entity, HotelResponseDto.class);
        } else {
            throw new HotelNoEncontradoException();
        }
    }

    public MessageDTO borrarHotel(Integer id) {
        if (iHotelRepository.existsById(id)) {

            var entity = mapper.map(iHotelRepository.findById(id), HotelObject.class);

            iHotelRepository.delete(entity);

            return MessageDTO.builder()
                    .message("Se elimino el hotel con id " + id)
                    .action("DELETE")
                    .build();
        } else {
            throw new HotelNoEncontradoException();
        }
    }

    public MessageDTO borrarReservaHotel(Integer id) {
        if (iHotelReservationRepository.existsById(id)) {

            var entity = mapper.map(iHotelReservationRepository.findById(id), HotelReservation.class);

            iHotelReservationRepository.delete(entity);

            return MessageDTO.builder()
                    .message("Se elimino la reserva con id " + id)
                    .action("DELETE")
                    .build();
        } else {
            throw new HotelNoEncontradoException();
        }
    }
}
