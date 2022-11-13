package share.shop.payloads;

import lombok.*;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryRequest {
    private String name;
    private Long categoryId;
}
