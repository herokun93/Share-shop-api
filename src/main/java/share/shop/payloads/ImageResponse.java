package share.shop.payloads;

import lombok.*;
import org.modelmapper.ModelMapper;
import share.shop.models.Image;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse {
    private Long id;
    private String urlSmall;
    private String urlMedium;
    private int priority;

    public ImageResponse imageResponseConvert(Image image){
        ModelMapper modelMapper = new ModelMapper();
        ImageResponse imageResponse = modelMapper.map(image,ImageResponse.class);
        return  imageResponse;
    }
}
