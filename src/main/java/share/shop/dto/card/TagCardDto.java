package share.shop.dto.card;

import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link share.shop.models.Tag} entity
 */
@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagCardDto implements Serializable {
    private Long id;
    private String name;
    private boolean enable;
}