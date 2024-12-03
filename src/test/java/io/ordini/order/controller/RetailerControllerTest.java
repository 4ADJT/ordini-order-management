package io.ordini.order.controller;

import io.ordini.order.domain.model.RetailerModel;
import io.ordini.order.service.RetailerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RetailerController.class)
class RetailerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RetailerService retailerService;

    private RetailerModel sampleRetailer;

    @BeforeEach
    void setup() {
        sampleRetailer = new RetailerModel();
        sampleRetailer.setId(UUID.randomUUID());
        sampleRetailer.setRetailer_name("Retailer One");
        sampleRetailer.setLat("-23.55052");
        sampleRetailer.setLon("-46.633308");
    }

    @Test
    void testGetRetailer() throws Exception {
        Mockito.when(retailerService.getRetailer()).thenReturn(List.of(sampleRetailer));

        mockMvc.perform(get("/retailer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(sampleRetailer.getId().toString()))
                .andExpect(jsonPath("$[0].retailer_name").value(sampleRetailer.getRetailer_name()));
    }

    @Test
    void testGetRetailerById() throws Exception {
        UUID retailerId = sampleRetailer.getId();
        Mockito.when(retailerService.getRetailerById(retailerId)).thenReturn(sampleRetailer);

        mockMvc.perform(get("/retailer/{id}", retailerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(retailerId.toString()))
                .andExpect(jsonPath("$.retailer_name").value(sampleRetailer.getRetailer_name()));
    }

    @Test
    void testCreateRetailer() throws Exception {
        Mockito.when(retailerService.createRetailer(any(RetailerModel.class))).thenReturn(sampleRetailer);

        String retailerJson = """
                {
                    "retailer_name": "Retailer One",
                    "lat": "-23.55052",
                    "lon": "-46.633308"
                }
                """;

        mockMvc.perform(post("/retailer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(retailerJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(sampleRetailer.getId().toString()))
                .andExpect(jsonPath("$.retailer_name").value(sampleRetailer.getRetailer_name()));
    }

    @Test
    void testUpdateRetailer() throws Exception {
        UUID retailerId = sampleRetailer.getId();
        Mockito.when(retailerService.updateRetailer(eq(retailerId), any(RetailerModel.class))).thenReturn(sampleRetailer);

        String updateJson = """
                {
                    "retailer_name": "Updated Retailer",
                    "lat": "-23.55052",
                    "lon": "-46.633308"
                }
                """;

        mockMvc.perform(put("/retailer/{id}", retailerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(retailerId.toString()))
                .andExpect(jsonPath("$.retailer_name").value(sampleRetailer.getRetailer_name()));
    }

    @Test
    void testDeleteRetailer() throws Exception {
        UUID retailerId = sampleRetailer.getId();
        Mockito.doNothing().when(retailerService).deleteRetailer(retailerId);

        mockMvc.perform(delete("/retailer/{id}", retailerId))
                .andExpect(status().isOk());
    }
}
