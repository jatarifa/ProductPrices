package es.inditex.prices.application;

import es.inditex.prices.domain.model.ProductPrice;

import java.time.LocalDateTime;

public interface ProductPriceService {
    ProductPrice getEffectiveProductPriceForDate(Long brand, Long product, LocalDateTime date);
}
