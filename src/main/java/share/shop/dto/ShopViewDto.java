package share.shop.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopViewDto implements Serializable {
    private long id;
    private String name;
    private String number;
    private String emailShop;
    private String address;
}
