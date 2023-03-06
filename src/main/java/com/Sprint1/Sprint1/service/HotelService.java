package com.Sprint1.Sprint1.service;

import com.Sprint1.Sprint1.dto.request.HotelBookingDto;
import com.Sprint1.Sprint1.dto.request.HotelMetodoPagoDto;
import com.Sprint1.Sprint1.dto.request.HotelPersonasDto;
import com.Sprint1.Sprint1.dto.request.HotelRequestDto;
import com.Sprint1.Sprint1.dto.response.HotelBookingResponseDto;
import com.Sprint1.Sprint1.dto.response.HotelResponseDto;
import com.Sprint1.Sprint1.dto.response.HotelStatusCodeDto;
import com.Sprint1.Sprint1.model.HotelObject;
import com.Sprint1.Sprint1.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HotelService {

    @Autowired
    HotelRepository hotelRepository;

    public List<HotelObject> listarHoteles() {
        return hotelRepository.listaDeHoteles();
    }

    public List<HotelObject> listarHotelesPorFechaDestino(String fechaPartida, String fechaRegreso, String destino)
            throws ParseException {
        List<HotelObject> hotelesBuscados = new ArrayList<>();

        String contador = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate fechaPartidaFormateada = LocalDate.parse(fechaPartida, formatter).plusDays(1);
        LocalDate fechaRegresoFormateada = LocalDate.parse(fechaRegreso, formatter).minusDays(1);

        for (HotelObject hotel : hotelRepository.listaDeHoteles()) {
            if (fechaPartidaFormateada.isAfter(hotel.getDisponibleDesde()) &&
                    fechaRegresoFormateada.isBefore(hotel.getDisponibleHasta())&& destino.equals(hotel.getLugarCiudad())) {
                hotelesBuscados.add(hotel);
            }
        }
        return hotelesBuscados;
    }

    public HotelResponseDto hotelReservaImpl(HotelRequestDto hotelRequestDto) {

    HotelResponseDto respuestaFinal = new HotelResponseDto();

    respuestaFinal.setUserName(hotelRequestDto.getUserName());


    Double precio= 0.0;
        for (HotelObject hotel : hotelRepository.getHotelesCargados()) {

            if(hotel.getCodigoHotel().equals(hotelRequestDto.getHotelBookingDto().getCodigoHotel())){
                precio = hotel.getPrecioPorNoche();
            }
        }
    respuestaFinal.setTotal(hotelRequestDto.getHotelBookingDto().getCantidadPersonas() * precio);

        HotelBookingResponseDto reserva = new HotelBookingResponseDto();
        reserva.setFechaDesde(hotelRequestDto.getHotelBookingDto().getFechaDesde());
        reserva.setFechaHasta(hotelRequestDto.getHotelBookingDto().getFechaHasta());
        reserva.setDestino(hotelRequestDto.getHotelBookingDto().getDestino());
        reserva.setCodigoHotel(hotelRequestDto.getHotelBookingDto().getCodigoHotel());
        reserva.setCantidadPersonas(hotelRequestDto.getHotelBookingDto().getCantidadPersonas());
        reserva.setTipoHabitacion(hotelRequestDto.getHotelBookingDto().getTipoHabitacion());
        reserva.setPersonas(hotelRequestDto.getHotelBookingDto().getPersonas());
        reserva.setEstado(new HotelStatusCodeDto(200,"Funca"));

        respuestaFinal.setHotelBookingDto(reserva);
        return respuestaFinal;
    }

}
