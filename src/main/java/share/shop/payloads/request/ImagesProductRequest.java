package share.shop.payloads.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImagesProductRequest {

    @NotBlank
    @Min(0)
    private long productId;
    @NotBlank
    private MultipartFile[] files;
    @NotBlank
    private int priority;
}
