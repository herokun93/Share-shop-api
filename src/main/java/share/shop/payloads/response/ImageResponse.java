package share.shop.payloads.response;

import lombok.*;
import org.modelmapper.ModelMapper;
import share.shop.models.Image;
import share.shop.utils.ImageToUrl;

import java.util.ArrayList;
import java.util.List;

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
//    private Long shopId;
//    private Long productId;

    public ImageResponse imageResponseConvert(Image image){
        ModelMapper modelMapper = new ModelMapper();

        image.setUrlMedium(ImageToUrl.toUrl(image.getUrlMedium()));
        image.setUrlSmall(ImageToUrl.toUrl(image.getUrlSmall()));

        ImageResponse imageResponse = modelMapper.map(image,ImageResponse.class);
        return  imageResponse;
    }
    public List<ImageResponse> imagesResponseConvert(List<Image> images){

        List<ImageResponse> imageResponseList = new ArrayList<>();

        images.forEach(i->{
            imageResponseList.add(imageResponseConvert(i));
        });
        return  imageResponseList;
    }
}
