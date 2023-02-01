package es.inditex.prices.interfaces.facade;

import es.inditex.prices.application.ProductPriceService;
import es.inditex.prices.domain.model.ProductPrice;
import es.inditex.prices.domain.model.ProductPriceFactory;
import es.inditex.prices.interfaces.facade.dto.PriceDTO;
import es.inditex.prices.interfaces.rest.ProductPriceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductPriceServiceFacadeTest {
    @Autowired
    private ProductPriceServiceFacade service;
    @MockBean
    private ProductPriceService productPriceService;

    @Test
    void whenGetProductPriceExists_thenShouldReturnPriceDTO() {
        // arrange
        LocalDateTime time = LocalDateTime.now();
        ProductPrice mockPrice = ProductPriceFactory.createProductPrice(1L, 1L, 1L, 0L, BigDecimal.TEN, "EUR", time, time);
        PriceDTO expectedResultPrice = new PriceDTO(1L, 1L, 1L, time, time, BigDecimal.TEN, "EUR");
        when(productPriceService.getEffectiveProductPriceForDate(anyLong(), anyLong(), any(LocalDateTime.class)))
                .thenReturn(mockPrice);
        // act
        PriceDTO price = service.getProductPriceForDate(1L, 1L, LocalDateTime.now());
        // assert
        assertNotNull(price);
        assertEquals(price, expectedResultPrice);
    }

    @Test
    void whenGetProductPriceNotExists_thenShouldThrowException() {
        // arrange
        when(productPriceService.getEffectiveProductPriceForDate(anyLong(), anyLong(), any(LocalDateTime.class)))
                .thenReturn(null);
        // act and assert
        assertThrows(ProductPriceNotFoundException.class, () -> {
            service.getProductPriceForDate(1L, 1L, LocalDateTime.now());
        });
    }
}