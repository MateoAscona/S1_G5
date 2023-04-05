package com.Sprint1.Sprint1.utils;


import com.Sprint1.Sprint1.exception.HotelNoEncontradoException;
import com.Sprint1.Sprint1.exception.VueloNoEncontradoException;
import com.Sprint1.Sprint1.model.HotelObject;
import com.Sprint1.Sprint1.model.VuelosObject;
import com.Sprint1.Sprint1.repository.HotelRepository;
import com.Sprint1.Sprint1.repository.VuelosRepository;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;


@Data
public class UtilMethods {

    HotelRepository hotelRepository = new HotelRepository();
    VuelosRepository vuelosRepository = new VuelosRepository();

    public void existeDestino(String destino){
        List<HotelObject> acumulador = new ArrayList<>();

        for (HotelObject hotel : hotelRepository.listaDeHoteles()) {
            if (hotel.getLugarCiudad().equals(destino)){
                acumulador.add(hotel);
            }
        }
        if (acumulador.isEmpty()){
            throw new HotelNoEncontradoException();
        }
    }
    public void existeDestinoDeVuelo(String destino){
        List<VuelosObject> acumulador = new ArrayList<>();
        for (VuelosObject vuelo : vuelosRepository.listaDeVuelos()) {
            if (vuelo.getDestino().equals(destino)){
                acumulador.add(vuelo);
            }
        }
        if (acumulador.isEmpty()){
            throw new VueloNoEncontradoException();
        }
    }

    public void relacionPersonasHabitaciones(String tipoHabitacion, Integer cantidadPersonas){
        switch(tipoHabitacion){
            case "Single":
                if(cantidadPersonas != 1){
                    throw new IllegalArgumentException("El tipo de habitación Single solo permite una persona");
                }
                break;
            case "Doble":
                if(cantidadPersonas > 2){
                    throw new IllegalArgumentException("El tipo de habitación Doble solo permite 1 o 2 personas");
                }
                break;
            case "Triple":
                if(cantidadPersonas > 3){
                    throw new IllegalArgumentException("El tipo de habitación Triple solo permite de 1 a 3 personas");
                }
                break;
            default:
                throw new IllegalArgumentException("Tipo de habitación inválido");
        }
    }


}
