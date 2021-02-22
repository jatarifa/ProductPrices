package es.inditex.prices.interfaces.facade.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode
public class PriceDTO {
    @Getter
    private final Long brand;
    @Getter
    private final Long product;
    @Getter
    private final Long priceList;
    @JsonFormat(pattern = "yyyy/MM/dd'T'HH:mm:ss")
    @Getter
    private final LocalDateTime from;
    @JsonFormat(pattern = "yyyy/MM/dd'T'HH:mm:ss")
    @Getter
    private final LocalDateTime to;
    @Getter
    private final BigDecimal price;
    @Getter
    private final String currency;

    public PriceDTO(Long brand, Long product, Long priceList, LocalDateTime from, LocalDateTime to, BigDecimal price, String currency) {
        this.brand = brand;
        this.product = product;
        this.priceList = priceList;
        this.from = from;
        this.to = to;
        this.price = price;
        this.currency = currency;
    }
}
