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
public class CountryRequest {

    @NotBlank
    @Size(min = 3,max=40,message = "Country name must be between 3 and 40 characters")
    private String name;
}
