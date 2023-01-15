package share.shop.dto.card;

import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link share.shop.models.Image} entity
 */
@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageCardDto implements Serializable {
    private String urlSmall;
    private String urlMedium;
    private int priority;
}