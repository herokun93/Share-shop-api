package share.shop.dto;

import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link share.shop.models.Country} entity
 */
@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryViewDto implements Serializable {
    private Long id;
    private String name;
}