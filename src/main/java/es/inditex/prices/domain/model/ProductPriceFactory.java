package es.inditex.prices.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductPriceFactory {
    public static ProductPrice createProductPrice(Long id, Long brand_id, Long product_id, Long priority, 
                                                  BigDecimal price, String currency, 
                                                  LocalDateTime start_date, LocalDateTime end_date) {
        Price priceVO = new Price(price, currency);
        Validity validityVO = new Validity(start_date, end_date);
        return new ProductPrice(id, brand_id, product_id, priority, priceVO, validityVO);
    }
}
