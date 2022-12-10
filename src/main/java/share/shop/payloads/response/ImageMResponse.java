package share.shop.payloads.response;

import org.modelmapper.ModelMapper;
import share.shop.models.Image;
import share.shop.utils.ImageToUrl;

import java.util.ArrayList;
import java.util.List;

public class ImageMResponse {
    private Long id;
    private String urlSmall;
    private String urlMedium;
    private int priority;
    private Long shopId;
    private Long productId;

    public ImageMResponse imageResponseConvert(Image image){
        ModelMapper modelMapper = new ModelMapper();

        image.setUrlMedium(ImageToUrl.toUrl(image.getUrlMedium()));
        image.setUrlSmall(ImageToUrl.toUrl(image.getUrlSmall()));

        ImageMResponse imageMResponse = modelMapper.map(image,ImageMResponse.class);
        return  imageMResponse;
    }
    public List<ImageMResponse> imagesMResponseConvert(List<Image> images){

        List<ImageMResponse> imageMResponses = new ArrayList<>();

        images.forEach(i->{
            imageMResponses.add(imageResponseConvert(i));
        });
        return  imageMResponses;
    }
}
