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
public class ShopRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String number;

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String address;
    @NotBlank
    private String userEmail;
    @NotBlank
    private boolean active;
}
