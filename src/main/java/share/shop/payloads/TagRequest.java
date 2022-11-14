package share.shop.payloads;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagRequest {

    @Size(min = 3,max=30,message = "Tag title must be between 3 and 30 characters")
    @NotBlank(message = "Tag title shouldn't be null")
    private String name;
}
