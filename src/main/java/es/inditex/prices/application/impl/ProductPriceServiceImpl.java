package es.inditex.prices.application.impl;

import es.inditex.prices.application.ProductPriceService;
import es.inditex.prices.domain.model.ProductPrice;
import es.inditex.prices.domain.model.ProductPriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {
    private static final Logger log = LoggerFactory.getLogger(ProductPriceServiceImpl.class);

    private final ProductPriceRepository productPriceRepository;

    public ProductPriceServiceImpl(final ProductPriceRepository productPriceRepository) {
        this.productPriceRepository = productPriceRepository;
    }

    @Override
    public ProductPrice getEffectiveProductPriceForDate(Long brand, Long product, LocalDateTime date) {
        List<ProductPrice> prices = productPriceRepository.findAllProductPricesForDate(brand, product, date);
        log.info("DB returned prices: {}", prices);

        if (prices == null || prices.isEmpty())
            return null;

        return selectProductPriceWithHighestPriority(prices);
    }

    private ProductPrice selectProductPriceWithHighestPriority(List<ProductPrice> prices) {
        ProductPrice tmpProductPrice = null;
        for (ProductPrice productPrice : prices) {
            if (tmpProductPrice == null || tmpProductPrice.getPriority() <= productPrice.getPriority())
                tmpProductPrice = productPrice;
        }
        log.info("Selected price: {}", tmpProductPrice);

        return tmpProductPrice;
    }
}
