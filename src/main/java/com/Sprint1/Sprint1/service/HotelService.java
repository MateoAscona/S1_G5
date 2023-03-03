package com.Sprint1.Sprint1.service;

import com.Sprint1.Sprint1.model.HotelObject;
import com.Sprint1.Sprint1.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class HotelService {

    @Autowired
    HotelRepository hotelRepository;

    public List<HotelObject> listarHoteles() {
        return hotelRepository.listaDeHoteles();
    }

    public String listarHotelesPorFechaDestino(String fechaPartida, String fechaRegreso, String Destino)
            throws ParseException {

        String contador = "";
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
        Date fechaPartidaFormateada = formatoFecha.parse(fechaPartida);
        Date fechaRegresoFormateada = formatoFecha.parse(fechaRegreso);

        for (HotelObject hotel: hotelRepository.listaDeHoteles()) {
            if (fechaPartidaFormateada.after(hotel.getDisponibleDesde()) &&
                    fechaRegresoFormateada.before(hotel.getDisponibleHasta())) {
                contador = contador + "a";
            } else {
                contador = contador + "b";
            }
        }
        return contador;
    }

}
