package com.Sprint1.Sprint1.dto.response;

import com.Sprint1.Sprint1.model.HotelObject;
import com.Sprint1.Sprint1.model.VuelosObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaqueteResponseDTO {

    private String destino;

    private LocalDate fechaPartida;

    private LocalDate fechaRegreso;

    private List<HotelObject> hotelDisponible;
    private List<VuelosObject> vueloDisponible;
}
