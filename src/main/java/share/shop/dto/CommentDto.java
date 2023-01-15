package share.shop.dto;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link share.shop.models.Comment} entity
 */
/**
 */
@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto implements Serializable {
    private  Instant createdAt;
    private  Instant updatedAt;
    private  Long id;
    private  String comment;
}