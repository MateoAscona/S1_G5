package com.Sprint1.Sprint1.integration;

import com.Sprint1.Sprint1.dto.request.HotelRequestDto;
import com.Sprint1.Sprint1.dto.request.VueloRequestDto;
import com.Sprint1.Sprint1.dto.response.HotelResponseDto;
import com.Sprint1.Sprint1.dto.response.VueloResponseDto;
import com.Sprint1.Sprint1.model.HotelObject;
import com.Sprint1.Sprint1.model.VuelosObject;
import com.Sprint1.Sprint1.utils.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
public class VuelosControllerIntegrationTest {
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
    public void buscarVuelosTest() throws Exception {

        // Arrange
        String fechaPartida = "2022/02/10";
        String fechaRegreso = "2022/02/15";
        String destino = "Puerto Iguazú";

        // Param necesario

        // La devolucion
        List<VuelosObject> vuelo = List.of(VueloFactory.getVuelo());

        // Request
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/api/v1/flights")
                        .param("fechaPartida", fechaPartida)
                        .param("fechaRegreso", fechaRegreso)
                        .param("destino", destino);

        // ResultMatchers

        ResultMatcher bodyExpected = MockMvcResultMatchers.content().json(writer.writeValueAsString(vuelo));
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
    public void buscarVueloExceptionTest() throws Exception {

        // Arrange
        String fechaPartida = "2022/02/10";
        String fechaRegreso = "2022/02/15";
        String destino = "Rafaela";

        // Request
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/api/v1/flights")
                        .param("fechaPartida", fechaPartida)
                        .param("fechaRegreso", fechaRegreso)
                        .param("destino", destino);

        // ResultMatchers
        ResultMatcher statusExpected = MockMvcResultMatchers.status().isBadRequest();

        // Act & Assert con mockmvc

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(statusExpected);
    }

    @Test
    public void reservarVueloTest() throws Exception {

        // Arrange
        VueloRequestDto requestDto = VueloRequestFactoryDTO.getVueloReserva();
        // Param necesario

        // La devolucion
        VueloResponseDto response = VueloResponseFactoryDTO.getVueloResponse();

        // Request
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.post("/api/v1/flight-reservation")
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
    public void reservarVuelosExceptionTest() throws Exception {

        // Arrange
       VueloRequestDto requestDto = VueloRequestFactoryDTO.getVueloReserva();
        requestDto.getVueloReserva().setDestino("Rafaela");
        // Param necesario

        // La devolucion

        // Request
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.post("/api/v1/flight-reservation")
                        .content(
                                writer.writeValueAsString(requestDto)
                        )
                        .contentType(MediaType.APPLICATION_JSON);

        // ResultMatchers

        ResultMatcher statusExpected = MockMvcResultMatchers.status().isBadRequest();

        // Act & Assert con mockmvc

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(statusExpected);
    }
}
