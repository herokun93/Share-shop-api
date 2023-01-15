package share.shop.dto;

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
public class PriceDto implements Serializable {
    private  Instant createdAt;
    private  Instant updatedAt;
    private  Long createdBy;
    private  Long updatedBy;
    private  Long id;
    private  String name;
    private  long price;
    private  long salePrice;
    private  Instant startAt;
    private  Instant finishAt;
    private  boolean sale;
}