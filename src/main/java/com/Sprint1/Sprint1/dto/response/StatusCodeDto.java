package com.Sprint1.Sprint1.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StatusCodeDto {
    private Integer code;

    private String mensaje;
}
