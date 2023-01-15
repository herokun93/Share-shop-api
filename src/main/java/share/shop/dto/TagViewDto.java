package share.shop.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagViewDto implements Serializable {
    private  Long id;
    private  String name;
    private  boolean enable;
}
