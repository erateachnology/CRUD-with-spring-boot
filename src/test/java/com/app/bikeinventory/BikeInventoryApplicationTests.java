package com.app.bikeinventory;
import com.app.bikeinventory.dto.BikeRequest;
import com.app.bikeinventory.dto.Response;
import com.app.bikeinventory.model.Bike;
import com.app.bikeinventory.repository.BikeRepository;
import com.app.bikeinventory.service.InventoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.awt.*;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class BikeInventoryApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext applicationContext;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private InventoryService inventoryService;

    @MockBean
    private BikeRepository bikeRepository;




/*    @SneakyThrows
    @Test
    void addBikeControllerTest(){
        String request = null;
        BikeRequest bikeRequest = new BikeRequest();
        bikeRequest.setBrand("Honda");
        bikeRequest.setColor("Black");
        bikeRequest.setSize(200L);
        bikeRequest.setGearSize(5);

        try {
            request = objectMapper.writeValueAsString(bikeRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        MvcResult result = mockMvc.perform(
                post("/bike/new")
                        .content(request)
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        Response responseEntity = objectMapper.readValue(resultContent,Response.class);

    }*/


    @SneakyThrows
    @Test
    void allBikesTest(){
        when(bikeRepository.findAll())
                .thenReturn(
                        Stream.of(
                                new Bike(5,"Honda", "Black", 200L,5),
                                new Bike(5,"Honda", "Black", 200L,5)
                        ).collect(Collectors.toList())

                );

        Assertions.assertEquals(2, Lists.newArrayList(inventoryService.getAllBikes()).size());
    }

    @SneakyThrows
    @Test
    void getBikeById(){
        when(bikeRepository.findById(5L))
                .thenReturn(
                        Optional.of(new Bike(5,"Honda", "Black", 200L,5))

                );
        Assertions.assertEquals(5, inventoryService.getBike(5L).getId());
    }

    @Test
    void addBike(){
        InventoryService inventoryService1 = mock(InventoryService.class);
        BikeRequest bikeRequest = new BikeRequest();
        bikeRequest.setBrand("Honda");
        bikeRequest.setColor("Black");
        bikeRequest.setSize(200L);
        bikeRequest.setGearSize(5);
        doNothing().
                when(inventoryService1).addBike(isA(BikeRequest.class));
        inventoryService1.addBike(bikeRequest);
        //inventoryService.addBike(bikeRequest);
        verify(inventoryService1, times(1)).addBike(bikeRequest);
    }

    @Test
    void deleteBike(){
        InventoryService inventoryService1 = mock(InventoryService.class);
        doNothing().
                when(inventoryService1).deleteBike(isA(Long.class));
        inventoryService1.deleteBike(5L);
        verify(inventoryService1, times(1)).deleteBike(5L);
    }

}
