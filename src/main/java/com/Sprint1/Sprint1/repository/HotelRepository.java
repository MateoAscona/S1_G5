package com.Sprint1.Sprint1.repository;

import com.Sprint1.Sprint1.dto.request.HotelBookingDto;
import com.Sprint1.Sprint1.dto.request.HotelMetodoPagoDto;
import com.Sprint1.Sprint1.dto.request.HotelPersonasDto;
import com.Sprint1.Sprint1.dto.request.HotelRequestDto;
import com.Sprint1.Sprint1.model.HotelObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Repository
public class HotelRepository {

    HotelObject hotelObject = new HotelObject();

    List<HotelObject> hotelesCargados;

    public HotelRepository(){
        hotelesCargados = loadDataBase();
    }

    public List<HotelObject> listaDeHoteles(){
        return hotelesCargados;
    }

    private List<HotelObject> loadDataBase() {
        List<HotelObject> hoteles = null;

        File file;
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .registerModule(new JavaTimeModule());
        TypeReference<List<HotelObject>> typeRef = new TypeReference<>() {};

        try {
            file = ResourceUtils.getFile("classpath:JSON/hotelJSON.json");
            hoteles = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hoteles;
    }

    public HotelBookingDto hotelReservado(HotelRequestDto hotelRequestDto) {
        HotelBookingDto bookingDto = new HotelBookingDto();
        HotelMetodoPagoDto metodoPagoDto = new HotelMetodoPagoDto();

        bookingDto.setFechaDesde(hotelRequestDto.getHotelBookingDto().getFechaDesde());
        bookingDto.setFechaHasta(hotelRequestDto.getHotelBookingDto().getFechaHasta());
        bookingDto.setDestino(hotelRequestDto.getHotelBookingDto().getDestino());
        bookingDto.setCodigoHotel(hotelRequestDto.getHotelBookingDto().getCodigoHotel());
        bookingDto.setCantidadPersonas(hotelRequestDto.getHotelBookingDto().getCantidadPersonas());
        bookingDto.setTipoHabitacion(hotelRequestDto.getHotelBookingDto().getTipoHabitacion());

        metodoPagoDto.setTipo(hotelRequestDto.getHotelBookingDto().getMetodoPago().getTipo());
        metodoPagoDto.setNumero(hotelRequestDto.getHotelBookingDto().getMetodoPago().getNumero());
        metodoPagoDto.setCuotas(hotelRequestDto.getHotelBookingDto().getMetodoPago().getCuotas());

        List<HotelPersonasDto> personasDtosList = new ArrayList<>();

        personasDtosList.addAll(hotelRequestDto.getHotelBookingDto().getPersonas());

        bookingDto.setPersonas(personasDtosList);
        bookingDto.setMetodoPago(metodoPagoDto);

        return bookingDto;
    }
}
