package es.inditex.prices.interfaces.rest;

import es.inditex.prices.interfaces.facade.dto.PriceDTO;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
public class ProductPriceControllerTest {
    @Autowired
    private ProductPriceController controller;

    static Stream<Arguments> testParameterProvisioner() {
        return Stream.of(
                arguments(1, 35455, LocalDateTime.of(2020, Month.JUNE, 14, 10, 0, 0), 1),
                arguments(1, 35455, LocalDateTime.of(2020, Month.JUNE, 14, 16, 0, 0), 2),
                arguments(1, 35455, LocalDateTime.of(2020, Month.JUNE, 14, 21, 0, 0), 1),
                arguments(1, 35455, LocalDateTime.of(2020, Month.JUNE, 15, 10, 0, 0), 3),
                arguments(1, 35455, LocalDateTime.of(2020, Month.JUNE, 16, 21, 0, 0), 4)
        );
    }

    @ParameterizedTest
    @MethodSource("testParameterProvisioner")
    public void whenGetProductPrice_thenShouldReturnExpectedProductPrice(long brand, long product, LocalDateTime date, long expectedPriceList) {
        PriceDTO price = controller.getProductPrice(brand, product, date);
        assertEquals(price.getPriceList(), expectedPriceList);
    }
}