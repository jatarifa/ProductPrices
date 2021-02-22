package es.inditex.prices.application.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import es.inditex.prices.domain.model.ProductPrice;
import es.inditex.prices.domain.model.ProductPriceRepository;
import es.inditex.prices.application.ProductPriceService;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {
    private static final Logger log = LoggerFactory.getLogger(ProductPriceServiceImpl.class);

    private final ProductPriceRepository productPriceRepository;

    public ProductPriceServiceImpl(final ProductPriceRepository productPriceRepository) {
        this.productPriceRepository = productPriceRepository;
    }

    @Override
    public ProductPrice getByIdAndBrandForDate(Long id, Long brand, LocalDateTime date) {
        List<ProductPrice> prices = productPriceRepository.findAllPricesByIdAndBrandForDate(id, brand, date);
        log.info("DB returned prices: {}", prices);

        if (prices == null || prices.isEmpty())
            return null;

        return selectProductPriceWithHighestPriority(prices);
    }

    private ProductPrice selectProductPriceWithHighestPriority(List<ProductPrice> prices) {
        ProductPrice tmpProductPrice = null;
        for (ProductPrice productPrice: prices) {
            if (tmpProductPrice == null || tmpProductPrice.getPriority() <= productPrice.getPriority())
                tmpProductPrice = productPrice;
        }
        log.info("Selected price: {}", tmpProductPrice);

        return tmpProductPrice;
    }
}
