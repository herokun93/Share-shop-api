package share.shop.dto;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * A DTO for the {@link share.shop.models.Category} entity
 */
@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto implements Serializable {
    private  Instant createdAt;
    private  Instant updatedAt;
    private  Long createdBy;
    private  Long updatedBy;
    private  Long id;
    private  String name;
    private  boolean enable;
    private  String imageUrl;
    private  String search;
    private  List<SubCategoryDto> subCategories;
}