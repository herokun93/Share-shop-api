package share.shop.dto.card;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link share.shop.models.Price} entity
 */
@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceCardDto implements Serializable {
    private String name;
    private long price;
    private long salePrice;
    private Instant startAt;
    private Instant finishAt;
    private boolean sale;
}