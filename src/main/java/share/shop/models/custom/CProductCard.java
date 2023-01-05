package share.shop.models.custom;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CProductCard {
    private Long id;
    private String name;
    private boolean hot;
    private int rating;
    private String descriptionSort;
    private String countryName;
    private String subCategoryName;
    private Long subCategoryId;
    private int option;
    private int mode;
    private boolean sale;
    private long price;
    private long salePrice;
    private String slug;
    private String until;
    private String imageUrl;
    private String tag;
}
