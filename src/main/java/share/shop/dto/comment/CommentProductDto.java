package share.shop.dto.comment;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link share.shop.models.Comment} entity
 */
@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentProductDto implements Serializable {
    private Instant createdAt;
    private Instant updatedAt;
    private String userName;
    private Long id;
    private String comment;
}