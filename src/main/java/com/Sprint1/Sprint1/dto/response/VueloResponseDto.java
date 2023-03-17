package com.Sprint1.Sprint1.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VueloResponseDto {
    private String userName;
    private Double total;
    private VueloReservaResponseDto vueloReservaResponseDto;
}
