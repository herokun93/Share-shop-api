package share.shop.payloads;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopInfoRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String number;

    @NotBlank
    @Email
    private String emailShop;
    @NotBlank
    private String address;
}
