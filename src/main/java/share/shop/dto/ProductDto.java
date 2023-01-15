package share.shop.dto;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link share.shop.models.Product} entity
 */
@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable {
    private  Instant createdAt;
    private  Instant updatedAt;
    private  Long createdBy;
    private  Long updatedBy;
    private  Long id;
    private  String name;
    private  boolean hot;
    private  int rating;
    private  String description;
    private  String descriptionSort;
    private  String tiktok;
    private  boolean enable;
    private  int mode;
    private  LocalDateTime until;
    private  boolean sale;
    private  long price;
    private  long salePrice;
    private  String slug;
}