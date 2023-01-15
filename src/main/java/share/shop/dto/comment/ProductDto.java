package share.shop.dto.comment;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

/**
 * A DTO for the {@link share.shop.models.Product} entity
 */
@Data
public class ProductDto implements Serializable {
    private final Long id;
    private final Collection<CommentProductDto> comments;
}