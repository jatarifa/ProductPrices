package es.inditex.prices.interfaces.facade.internal.assembler;

import es.inditex.prices.interfaces.facade.dto.PriceDTO;
import es.inditex.prices.domain.model.ProductPrice;

public class PriceDTOAssembler {
    public PriceDTO toDTO(ProductPrice price) {
        return new PriceDTO(price.getBrandId(), price.getProductId(), price.getId(),
                            price.getValidity().getFrom(), price.getValidity().getTo(),
                            price.getPrice().getValue(), price.getPrice().getCurrency());
    }
}
