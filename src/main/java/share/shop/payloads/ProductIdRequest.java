package share.shop.payloads;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductIdRequest {
    @NotBlank
    private Long id;
}
