package es.inditex.prices.domain.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.ToString;

@ToString
public class Price {
    @Getter private BigDecimal value;
    @Getter private String currency;

    Price(BigDecimal value, String currency) {
        this.value = value;
        this.currency = currency;
    }
}
