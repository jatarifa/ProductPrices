package es.inditex.prices.application;

import java.time.LocalDateTime;

import es.inditex.prices.domain.model.ProductPrice;

public interface ProductPriceService {
    ProductPrice getByIdAndBrandForDate(Long id, Long brand, LocalDateTime date);
}
