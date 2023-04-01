package com.Sprint1.Sprint1.dto.response;

import com.Sprint1.Sprint1.model.HotelReservationData;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HotelResponseDto {
    private Integer id;
    private String nombreUsuario;
    private Double total;
    private HotelReservationData hotelReservationData;
}
