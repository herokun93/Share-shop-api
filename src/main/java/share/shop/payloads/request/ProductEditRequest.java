package share.shop.payloads.request;

import lombok.*;

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
public class ProductEditRequest {
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
    private String sale;

    @NotBlank
    private String countryId;

    @NotBlank
    private String subCategoryId;

    @NotBlank
    private String productId;

    @NotBlank
    private String shopId;


    @NotBlank
    private String price;
    @NotBlank
    private String salePrice;
}
