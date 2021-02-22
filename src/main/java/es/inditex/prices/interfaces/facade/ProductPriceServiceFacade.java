package es.inditex.prices.interfaces.facade;

import java.time.LocalDateTime;

import es.inditex.prices.interfaces.facade.dto.PriceDTO;
import es.inditex.prices.interfaces.rest.ProductPriceNotFoundException;

public interface ProductPriceServiceFacade {
    PriceDTO getProductPriceForDate(Long brand, Long product, LocalDateTime date);
}
