package share.shop.dto;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link share.shop.models.Tag} entity
 */
@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDto implements Serializable {
    private  Instant createdAt;
    private  Instant updatedAt;
    private  Long createdBy;
    private  Long updatedBy;
    private  Long id;
    private  String name;
    private  boolean enable;
}