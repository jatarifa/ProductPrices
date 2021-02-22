package es.inditex.prices.interfaces.rest;

import es.inditex.prices.interfaces.facade.ProductPriceServiceFacade;
import es.inditex.prices.interfaces.facade.dto.PriceDTO;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "Get Product Price for a date")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Price found"),
        @ApiResponse(code = 404, message = "Price not found"),
        @ApiResponse(code = 400, message = "Invalid message format"),
    })
    public PriceDTO getProductPrice(@ApiParam(name = "brandId", type = "Long", value = "Brand identifier", example = "1", required = true)
                                        @PathVariable final Long brandId,
                                    @ApiParam(name = "productId", type = "Long", value = "Product identifier", example = "35455", required = true)
                                        @PathVariable final Long productId,
                                    @ApiParam(name = "date", type = "Timestamp", value = "ISO 8601 date to get prices", example = "2020-06-14T10:00:00", required = true)
                                        @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        log.info("REST call with Brand: {}, Product: {}, Date: {}", brandId, productId, date);
        return productPriceServiceFacade.getProductPriceForDate(brandId, productId, date);
    }
}