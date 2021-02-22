package es.inditex.prices.interfaces.facade.dto;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

public class PriceDTO {
    @Getter private Long brand;
    @Getter private Long product;
    @Getter private Long priceList;
    @JsonFormat(pattern = "yyyy/MM/dd'T'HH:mm:ss")
    @Getter private LocalDateTime from;
    @JsonFormat(pattern = "yyyy/MM/dd'T'HH:mm:ss")
    @Getter private LocalDateTime to;
    @Getter private BigDecimal price;
    @Getter private String currency;
    
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
