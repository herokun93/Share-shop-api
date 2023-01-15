package share.shop.dto;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;

/**
 * A DTO for the {@link share.shop.models.SubCategory} entity
 */
@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryDto implements Serializable {
    private  Instant createdAt;
    private  Instant updatedAt;
    private  Long createdBy;
    private  Long updatedBy;
    private  Long id;
    private  String name;
    private  String slug;
    private  boolean enable;
    private  Collection<ProductDto> products;
}