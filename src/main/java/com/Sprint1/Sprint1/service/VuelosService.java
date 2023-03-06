package com.Sprint1.Sprint1.service;

import com.Sprint1.Sprint1.model.HotelObject;
import com.Sprint1.Sprint1.model.VuelosObject;
import com.Sprint1.Sprint1.repository.VuelosRepository;
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

    public List<VuelosObject> listarVuelos() {
        return vuelosRepository.listaDeVuelos();
    }


    public List<VuelosObject> listarVuelosPorFechaDestino(String fechaPartida, String fechaRegreso, String destino)
            throws ParseException {
        List<VuelosObject> vuelosBuscados = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate fechaPartidaFormateada = LocalDate.parse(fechaPartida, formatter).plusDays(1);
        LocalDate fechaRegresoFormateada = LocalDate.parse(fechaRegreso, formatter).minusDays(1);

        for (VuelosObject vuelos : vuelosRepository.listaDeVuelos()) {
            if (fechaPartidaFormateada.isAfter(vuelos.getFechaIda()) &&
                    fechaRegresoFormateada.isBefore(vuelos.getFechaVuelta()) &&
                    destino.equals(vuelos.getDestino())) {
                vuelosBuscados.add(vuelos);
            }
        }
        return vuelosBuscados;
    }

}
