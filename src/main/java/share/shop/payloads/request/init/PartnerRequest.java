package share.shop.payloads.request.init;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartnerRequest {
    @NotBlank
    private Long shopId;
}
