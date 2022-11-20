package share.shop.payloads;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

}
