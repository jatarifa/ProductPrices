package es.inditex.prices.domain.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

@ToString
public class Validity {
    @Getter private LocalDateTime from;
    @Getter private LocalDateTime to;

    Validity(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }
}
