package share.shop.dto;

import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link share.shop.models.Category} entity
 */
@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryViewDto implements Serializable {
    private  Long id;
    private  String name;
    private  String imageUrl;
}