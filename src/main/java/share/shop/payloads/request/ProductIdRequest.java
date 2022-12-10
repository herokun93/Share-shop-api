package share.shop.payloads.request;

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
