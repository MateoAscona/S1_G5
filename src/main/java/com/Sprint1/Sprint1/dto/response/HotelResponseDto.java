package com.Sprint1.Sprint1.dto.response;

import com.Sprint1.Sprint1.dto.request.HotelBookingDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HotelResponseDto {
    private String userName;
    private Double total;
    private HotelBookingResponseDto hotelBookingDto;
}
