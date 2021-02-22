package es.inditex.prices.interfaces.rest;

import es.inditex.prices.interfaces.facade.ProductPriceServiceFacade;
import es.inditex.prices.interfaces.facade.dto.PriceDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductPriceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductPriceServiceFacade service;

    @Test
    void whenRequestParametersOk_thenShouldReturnCorrectResponse() throws Exception {
        // arrange
        LocalDateTime time = LocalDateTime.of(2020, 1, 1, 1, 0, 0);
        PriceDTO price = new PriceDTO(1L, 1L, 1L, time, time, BigDecimal.TEN, "EUR");
        when(service.getProductPriceForDate(anyLong(), anyLong(), any(LocalDateTime.class)))
                .thenReturn(price);
        // act and assert
        mockMvc.perform(get("/prices/1/1").queryParam("date", "2020-06-15T21:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "{\"brand\":1,\"product\":1,\"priceList\":1,\"from\":\"2020-01-01T01:00:00\",\"to\":\"2020-01-01T01:00:00\",\"price\":10,\"currency\":\"EUR\"}")))
                .andDo(print());
    }

    @Test
    void whenRequestPriceNotFound_thenShouldReturnNotFound() throws Exception {
        // arrange
        when(service.getProductPriceForDate(anyLong(), anyLong(), any(LocalDateTime.class)))
                .thenThrow(ProductPriceNotFoundException.class);
        // act and assert
        mockMvc.perform(get("/prices/1/1").queryParam("date", "2020-06-15T21:00:00"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void whenRequestParametersNOk_thenShouldReturnError() throws Exception {
        // arrange
        LocalDateTime time = LocalDateTime.of(2020, 1, 1, 1, 0, 0);
        PriceDTO price = new PriceDTO(1L, 1L, 1L, time, time, BigDecimal.TEN, "EUR");
        when(service.getProductPriceForDate(anyLong(), anyLong(), any(LocalDateTime.class)))
                .thenReturn(price);
        // act and assert
        mockMvc.perform(get("/prices/1/1").queryParam("date", "wrong date"))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
}