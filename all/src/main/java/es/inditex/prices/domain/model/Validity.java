package es.inditex.prices.domain.model;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
public class Validity {
    @Getter
    private final LocalDateTime from;
    @Getter
    private final LocalDateTime to;

    Validity(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }
}
