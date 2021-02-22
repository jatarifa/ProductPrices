package es.inditex.prices.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProductPriceNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public ProductPriceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}