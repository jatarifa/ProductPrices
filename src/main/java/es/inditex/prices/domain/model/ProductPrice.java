package es.inditex.prices.domain.model;

import lombok.Getter;
import lombok.ToString;

@ToString
public class ProductPrice {
    @Getter
    private final Long priceList;
    @Getter
    private final Long brandId;
    @Getter
    private final Long productId;
    @Getter
    private final Long priority;
    @Getter
    private final Price price;
    @Getter
    private final Validity validity;

    ProductPrice(Long priceList, Long brandId, Long productId, Long priority, Price price, Validity validity) {
        this.priceList = priceList;
        this.brandId = brandId;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.validity = validity;
    }
}
 