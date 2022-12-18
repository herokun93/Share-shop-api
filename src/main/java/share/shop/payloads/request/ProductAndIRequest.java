package share.shop.payloads.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAndIRequest {
    @Size(min = 8,max=200,message = "Product name must be between 8 and 200 characters")
    @NotBlank(message = "Product name shouldn't be null")
    private String name;


    @Size(min = 8,max=1000,message = "Product description must be between 8 and 200 characters")
    @NotBlank(message = "Product description shouldn't be null")
    private String description;

    @Size(min = 8,max=200,message = "Product descriptionSort must be between 8 and 200 characters")
    @NotBlank(message = "Product descriptionSort shouldn't be null")
    private String descriptionSort;

    @NotBlank(message = "Tiktok  shouldn't be null")
    private String tiktok;


    @NotBlank
    private Long countryId;

    @NotBlank
    private Long subCategoryId;

    @NotBlank
    private boolean sale;
    @NotBlank
    private long price;
    @NotBlank
    private long salePrice;

    @NotBlank
    private MultipartFile[] files;
}
