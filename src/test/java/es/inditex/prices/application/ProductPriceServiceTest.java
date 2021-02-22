package es.inditex.prices.application;

import es.inditex.prices.domain.model.ProductPrice;
import es.inditex.prices.domain.model.ProductPriceFactory;
import es.inditex.prices.domain.model.ProductPriceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductPriceServiceTest {
    private final ProductPrice mockPrice1 = ProductPriceFactory.createProductPrice(1L, 1L, 1L, 0L, BigDecimal.TEN, "EUR",
            LocalDateTime.of(2020, 1, 1, 10, 0, 0),
            LocalDateTime.of(2020, 4, 1, 10, 0, 0));
    private final ProductPrice mockPrice2 = ProductPriceFactory.createProductPrice(2L, 1L, 1L, 1L, BigDecimal.ONE, "EUR",
            LocalDateTime.of(2020, 2, 1, 10, 0, 0),
            LocalDateTime.of(2020, 3, 1, 10, 0, 0));
    @Autowired
    private ProductPriceService service;
    @MockBean
    private ProductPriceRepository repository;

    @Test
    void whenGetEffectivePriceAndOverlappingDates_thenShouldReturnPriorityPrice() {
        // arrange
        when(repository.findAllProductPricesForDate(anyLong(), anyLong(), any(LocalDateTime.class)))
                .thenReturn(Arrays.asList(mockPrice1, mockPrice2));
        // act
        ProductPrice price = service.getEffectiveProductPriceForDate(1L, 1L, LocalDateTime.of(2020, 3, 10, 10, 0, 0));
        // assert
        assertNotNull(price);
        assertEquals(price, mockPrice2);
    }

    @Test
    void whenGetEffectivePriceAndNoOverlappingDates_thenShouldReturnPriorityPrice() {
        // arrange
        when(repository.findAllProductPricesForDate(anyLong(), anyLong(), any(LocalDateTime.class)))
                .thenReturn(Arrays.asList(mockPrice1));
        // act
        ProductPrice price = service.getEffectiveProductPriceForDate(1L, 1L, LocalDateTime.of(2020, 3, 10, 10, 0, 0));
        // assert
        assertNotNull(price);
        assertEquals(price, mockPrice1);
    }

    @Test
    void whenGetEffectivePriceAndNoPriceExists_thenShouldReturnNull() {
        // arrange
        when(repository.findAllProductPricesForDate(anyLong(), anyLong(), any(LocalDateTime.class)))
                .thenReturn(Collections.emptyList());
        // act
        ProductPrice price = service.getEffectiveProductPriceForDate(1L, 1L, LocalDateTime.of(2020, 3, 10, 10, 0, 0));
        // assert
        assertNull(price);
    }
}
