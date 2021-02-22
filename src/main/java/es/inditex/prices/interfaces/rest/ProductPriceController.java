package es.inditex.prices.interfaces.rest;

import es.inditex.prices.interfaces.facade.ProductPriceServiceFacade;
import es.inditex.prices.interfaces.facade.dto.PriceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
public class ProductPriceController {
    private static final Logger log = LoggerFactory.getLogger(ProductPriceController.class);

    private final ProductPriceServiceFacade productPriceServiceFacade;

    public ProductPriceController(ProductPriceServiceFacade productPriceServiceFacade) {
        this.productPriceServiceFacade = productPriceServiceFacade;
    }

    @GetMapping("/{brandId}/{productId}")
    public PriceDTO getProductPrice(@PathVariable final Long brandId,
                                    @PathVariable final Long productId,
                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        if (date == null)
            date = LocalDateTime.now();

        log.info("REST call with Brand: {}, Product: {}, Date: {}", brandId, productId, date);
        return productPriceServiceFacade.getProductPriceForDate(brandId, productId, date);
    }
}