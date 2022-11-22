package share.shop.payloads;

import lombok.*;
import share.shop.models.RoleName;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleUserRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String role;
}
