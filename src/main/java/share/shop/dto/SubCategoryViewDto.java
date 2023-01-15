package share.shop.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryViewDto implements Serializable {
    private  Long id;
    private  String name;
}
