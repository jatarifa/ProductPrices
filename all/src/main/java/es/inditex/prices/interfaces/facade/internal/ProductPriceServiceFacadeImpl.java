package es.inditex.prices.interfaces.facade.internal;

import es.inditex.prices.application.ProductPriceService;
import es.inditex.prices.domain.model.ProductPrice;
import es.inditex.prices.interfaces.facade.ProductPriceServiceFacade;
import es.inditex.prices.interfaces.facade.dto.PriceDTO;
import es.inditex.prices.interfaces.facade.internal.assembler.PriceDTOAssembler;
import es.inditex.prices.interfaces.rest.ProductPriceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductPriceServiceFacadeImpl implements ProductPriceServiceFacade {
    private final ProductPriceService productPriceService;

    public ProductPriceServiceFacadeImpl(ProductPriceService productPriceService) {
        this.productPriceService = productPriceService;
    }

    @Override
    public PriceDTO getProductPriceForDate(Long brand, Long product, LocalDateTime date) {
        ProductPrice price = productPriceService.getEffectiveProductPriceForDate(brand, product, date);
        if (price == null)
            throw new ProductPriceNotFoundException("Price not found");

        final PriceDTOAssembler assembler = new PriceDTOAssembler();
        return assembler.toDTO(price);
    }
}