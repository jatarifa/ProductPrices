package es.inditex.prices.interfaces.facade.internal.assembler;

import es.inditex.prices.domain.model.ProductPrice;
import es.inditex.prices.interfaces.facade.dto.PriceDTO;

public class PriceDTOAssembler {
    public PriceDTO toDTO(ProductPrice price) {
        return new PriceDTO(price.getBrandId(), price.getProductId(), price.getPriceList(),
                price.getValidity().getFrom(), price.getValidity().getTo(),
                price.getPrice().getValue(), price.getPrice().getCurrency());
    }
}
