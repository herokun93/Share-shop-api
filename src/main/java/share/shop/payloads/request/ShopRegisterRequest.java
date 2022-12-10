package share.shop.payloads.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopRegisterRequest {

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
    @Email
    private String userEmail;


    private boolean active;
}
