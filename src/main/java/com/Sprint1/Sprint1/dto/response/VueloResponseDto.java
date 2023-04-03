package com.Sprint1.Sprint1.dto.response;

import com.Sprint1.Sprint1.dto.request.VueloReservationData;
import com.Sprint1.Sprint1.model.VuelosReservationData;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VueloResponseDto {
    private Integer id;
    private String userName;
    private Double total;
    private VuelosReservationData vuelosReservationData;
}
