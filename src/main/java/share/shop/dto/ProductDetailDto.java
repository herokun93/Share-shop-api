package share.shop.dto;

import lombok.*;
import share.shop.dto.card.ImageCardDto;
import share.shop.dto.card.PriceCardDto;
import share.shop.dto.card.TagCardDto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDto implements Serializable {
    private  Long id;
    private  String name;
    private  boolean hot;
    private  int rating;
    private  String description;
    private  String descriptionSort;
    private  String tiktok;
    private  boolean enable;
    private LocalDateTime until;
    private String subCategoryName;
    private Long subCategoryId;
    private Collection<TagCardDto> tags;
    private  Collection<ImageCardDto> images;
    private  Collection<PriceCardDto> prices;
}
