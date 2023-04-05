package com.Sprint1.Sprint1.integration;

import com.Sprint1.Sprint1.dto.request.HotelReservaRequestDto;
import com.Sprint1.Sprint1.dto.request.PaqueteRequestDTO;
import com.Sprint1.Sprint1.dto.response.HotelResponseDto;
import com.Sprint1.Sprint1.dto.response.PaqueteResponseDTO;
import com.Sprint1.Sprint1.model.HotelObject;
import com.Sprint1.Sprint1.utils.HotelFactory;
import com.Sprint1.Sprint1.utils.HotelRequestFactoryDTO;
import com.Sprint1.Sprint1.utils.HotelResponseFactoryDTO;
import com.Sprint1.Sprint1.utils.VueloFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class AgenciaControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    ObjectWriter writer;

    @BeforeEach
    public void setupBeforeAll(){
        writer = new ObjectMapper()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .registerModule(new JavaTimeModule())
                .writer();
    }

    @Test
    public void reservarHotelTest() throws Exception {

        PaqueteRequestDTO requestDto = PaqueteRequestDTO.builder()
                .fechaPartida(LocalDate.of(2022,02,10))
                .fechaRegreso(LocalDate.of(2022,02,15))
                .destino("Puerto Iguazú")
                .build();

        PaqueteResponseDTO response = PaqueteResponseDTO.builder()
                .destino("Puerto Iguazú")
                .fechaPartida(LocalDate.of(2022,02,10))
                .fechaRegreso(LocalDate.of(2022,02,15))
                .hotelDisponible(List.of(HotelFactory.getHotelWithId(), HotelFactory.getHotelWithId2()))
                .vueloDisponible(List.of(VueloFactory.getVueloWithId()))
                .build();
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.post("/api/v1/pack")
                        .content(
                                writer.writeValueAsString(requestDto)
                        )
                        .contentType(MediaType.APPLICATION_JSON);

        ResultMatcher bodyExpected = MockMvcResultMatchers.content().json(writer.writeValueAsString(response));
        ResultMatcher statusExpected = MockMvcResultMatchers.status().isOk();
        ResultMatcher contentTypeExpected = MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(bodyExpected)
                .andExpect(statusExpected)
                .andExpect(contentTypeExpected);
    }
}
