package share.shop.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageSetProductRequest {
    @NotBlank
    @Min(0)
    private long productId;

    @NotBlank
    private int priority;
}
