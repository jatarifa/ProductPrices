package es.inditex.prices.interfaces.facade;

import es.inditex.prices.interfaces.facade.dto.PriceDTO;

import java.time.LocalDateTime;

public interface ProductPriceServiceFacade {
    PriceDTO getProductPriceForDate(Long brand, Long product, LocalDateTime date);
}
