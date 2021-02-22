package es.inditex.prices.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductPriceRepository {
    List<ProductPrice> findAllPricesByIdAndBrandForDate(Long id, Long brand, LocalDateTime date);
}
