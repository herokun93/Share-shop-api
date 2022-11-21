package share.shop.payloads;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdatePassRequest {

    @NotBlank
    @Size(min = 8,max=16,message = "Pass must be between 8 and 16 characters")
    private String passwordNew;

    @NotBlank
    @Size(min = 8,max=16,message = "Pass must be between 8 and 16 characters")
    private String passwordOld;
}
