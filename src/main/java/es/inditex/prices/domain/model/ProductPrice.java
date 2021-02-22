package es.inditex.prices.domain.model;

import lombok.Getter;
import lombok.ToString;

@ToString
public class ProductPrice {
    @Getter private Long id;
    @Getter private Long brandId;
    @Getter private Long productId;
    @Getter private Long priority;
    @Getter private Price price;
    @Getter private Validity validity;

    public ProductPrice(Long id, Long brandId, Long productId, Long priority, Price price, Validity validity) {
        this.id = id;
        this.brandId = brandId;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.validity = validity;  
    }
}
 