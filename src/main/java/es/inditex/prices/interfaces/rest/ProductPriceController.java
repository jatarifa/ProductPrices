package es.inditex.prices.interfaces.rest;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.inditex.prices.interfaces.facade.ProductPriceServiceFacade;
import es.inditex.prices.interfaces.facade.dto.PriceDTO;

@RestController
@RequestMapping("/prices")
public class ProductPriceController {
    private static final Logger log = LoggerFactory.getLogger(ProductPriceController.class);

    private ProductPriceServiceFacade productPriceServiceFacade;

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