package es.inditex.prices.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductPriceFactory {
    public static ProductPrice createProductPrice(Long priceList, Long brandId, Long productId, Long priority, 
                                                  BigDecimal price, String currency, 
                                                  LocalDateTime start_date, LocalDateTime end_date) {
        Price priceVO = new Price(price, currency);
        Validity validityVO = new Validity(start_date, end_date);
        return new ProductPrice(priceList, brandId, productId, priority, priceVO, validityVO);
    }
}
