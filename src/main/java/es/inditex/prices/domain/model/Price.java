package es.inditex.prices.domain.model;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
public class Price {
    @Getter
    private final BigDecimal value;
    @Getter
    private final String currency;

    Price(BigDecimal value, String currency) {
        this.value = value;
        this.currency = currency;
    }
}
