package es.inditex.prices.interfaces.facade.internal;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import es.inditex.prices.application.ProductPriceService;
import es.inditex.prices.domain.model.ProductPrice;
import es.inditex.prices.interfaces.facade.ProductPriceServiceFacade;
import es.inditex.prices.interfaces.facade.internal.assembler.PriceDTOAssembler;
import es.inditex.prices.interfaces.facade.dto.PriceDTO;
import es.inditex.prices.interfaces.rest.ProductPriceNotFoundException;

@Service
public class ProductPriceServiceFacadeImpl implements ProductPriceServiceFacade {
    private ProductPriceService productPriceService;
 
    public ProductPriceServiceFacadeImpl(ProductPriceService productPriceService) {
        this.productPriceService = productPriceService;
    }

    @Override
    public PriceDTO getPriceForBrandAndProductAndDate(Long brand, Long product, LocalDateTime date) throws ProductPriceNotFoundException {
        ProductPrice price = productPriceService.getByIdAndBrandForDate(product, brand, date);
        if (price == null)
            throw new ProductPriceNotFoundException("Price not found");

        final PriceDTOAssembler assembler = new PriceDTOAssembler();
        return assembler.toDTO(price);
    }
}