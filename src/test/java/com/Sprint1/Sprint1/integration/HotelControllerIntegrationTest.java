package com.Sprint1.Sprint1.integration;

import com.Sprint1.Sprint1.dto.ExceptionDto;
import com.Sprint1.Sprint1.dto.request.HotelReservaRequestDto;
import com.Sprint1.Sprint1.dto.response.HotelResponseDto;
import com.Sprint1.Sprint1.model.HotelObject;
import com.Sprint1.Sprint1.utils.HotelFactory;
import com.Sprint1.Sprint1.utils.HotelRequestFactoryDTO;
import com.Sprint1.Sprint1.utils.HotelResponseFactoryDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class HotelControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    ObjectWriter writer;

    @BeforeEach
    public void setupBeforeAll(){
        writer = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writer();
    }
    @Test
    public void buscarHotelTest() throws Exception {

        // Arrange
        String fechaPartida = "2022-02-10";
        String fechaRegreso = "2022-03-20";
        String destino = "Puerto Iguazú";

        // Param necesario

        // La devolucion
        List<HotelObject> hotel = List.of(HotelFactory.getHotel());

        // Request
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/api/v1/hotels")
                        .param("fechaPartida", fechaPartida)
                        .param("fechaRegreso", fechaRegreso)
                        .param("destino", destino);

        // ResultMatchers

        ResultMatcher bodyExpected = MockMvcResultMatchers.content().json(writer.writeValueAsString(hotel));
        ResultMatcher statusExpected = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentTypeExpected = MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON);

        // Act & Assert con mockmvc

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(bodyExpected)
                .andExpect(statusExpected)
                .andExpect(contentTypeExpected);
    }

    @Test
    public void buscarHotelExceptionTest() throws Exception {

        // Arrange
        String fechaPartida = "2022/02/10";
        String fechaRegreso = "2022/03/20";
        String destino = "Rafaela";

        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("No se encontró ningún hotel.");

        // Request
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/api/v1/hotels")
                        .param("fechaPartida", fechaPartida)
                        .param("fechaRegreso", fechaRegreso)
                        .param("destino", destino);

        // ResultMatchers
        ResultMatcher bodyExpected = MockMvcResultMatchers.content().json(writer.writeValueAsString(exceptionDto));
        ResultMatcher statusExpected = MockMvcResultMatchers.status().isBadRequest();
        ResultMatcher contentTypeExpected = MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON);

        // Act & Assert con mockmvc

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(statusExpected)
                .andExpect(bodyExpected)
                .andExpect(contentTypeExpected);
    }

    @Test
    public void reservarHotelTest() throws Exception {

        // Arrange
        HotelReservaRequestDto requestDto = HotelRequestFactoryDTO.getHotelReserva();
        // Param necesario

        // La devolucion
        HotelResponseDto response = HotelResponseFactoryDTO.getHotelResponse();

        // Request
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.post("/api/v1/booking")
                        .content(
                                writer.writeValueAsString(requestDto)
                        )
                        .contentType(MediaType.APPLICATION_JSON);

        // ResultMatchers

        ResultMatcher bodyExpected = MockMvcResultMatchers.content().json(writer.writeValueAsString(response));
        ResultMatcher statusExpected = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentTypeExpected = MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON);

        // Act & Assert con mockmvc

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(bodyExpected)
                .andExpect(statusExpected)
                .andExpect(contentTypeExpected);
    }

    @Test
    public void reservarHotelExceptionTest() throws Exception {

        // Arrange
        HotelReservaRequestDto requestDto = HotelRequestFactoryDTO.getHotelReserva();
        requestDto.getHotelReservationData().setDestino("Rafaela");

        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("No se encontró ningún hotel.");
        // Param necesario

        // La devolucion

        // Request
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.post("/api/v1/booking")
                        .content(
                                writer.writeValueAsString(requestDto)
                        )
                        .contentType(MediaType.APPLICATION_JSON);

        // ResultMatchers

        ResultMatcher bodyExpected = MockMvcResultMatchers.content().json(writer.writeValueAsString(exceptionDto));
        ResultMatcher statusExpected = MockMvcResultMatchers.status().isBadRequest();
        ResultMatcher contentTypeExpected = MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON);


        // Act & Assert con mockmvc

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(statusExpected)
                .andExpect(bodyExpected)
                .andExpect(contentTypeExpected);
    }

}
