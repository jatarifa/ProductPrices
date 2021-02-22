package es.inditex.prices.interfaces.facade;

import java.time.LocalDateTime;

import es.inditex.prices.interfaces.facade.dto.PriceDTO;
import es.inditex.prices.interfaces.rest.ProductPriceNotFoundException;

public interface ProductPriceServiceFacade {
    PriceDTO getPriceForBrandAndProductAndDate(Long brand, Long product, LocalDateTime date) throws ProductPriceNotFoundException;
}
