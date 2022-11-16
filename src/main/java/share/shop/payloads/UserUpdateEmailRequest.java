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
public class UserUpdateEmailRequest {

    @Email
    @NotBlank
    private String email;

}
