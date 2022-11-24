package share.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import share.shop.models.Image;
import share.shop.models.Product;
import share.shop.payloads.ImageResponse;
import share.shop.payloads.PagedResponse;
import share.shop.payloads.ProductCard;
import share.shop.repositories.ImageRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image save(Image image){
        return imageRepository.save(image);
    }
    public Image getImageByName(String name){
        return  imageRepository.getImageByName(name);
    };

    public Optional<Image>findById(Long id){
        return imageRepository.findById(id);
    }
    public Optional<Image>findByShopIdAndId(Long shopId,Long imageId){return imageRepository.findByShopIdAndId(shopId,imageId);}


    public void deleteById(long id){
        imageRepository.deleteById(id);
    }
    public Image saveAndFlush(Image image){return imageRepository.saveAndFlush(image);};
    public List<Image> saveAll(List<Image> images){return imageRepository.saveAll(images);}

    public PagedResponse getAllImagesOfProduct(long countryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Image> images = imageRepository.findAllByProductId(countryId,pageable);

        if(images.getNumberOfElements() ==0){
            return new PagedResponse(Collections.emptyList(),images.getNumber(),images.getSize(),
                    images.getTotalElements(),images.getTotalPages(),images.isLast());
        }

        List<ImageResponse> imageResponseList = images.map(image -> {
            ImageResponse imageResponse = new ImageResponse();
            return imageResponse.imageResponseConvert(image);
        }).getContent();

        return new PagedResponse<>(imageResponseList,images.getNumber(),images.getSize(),images.getTotalElements(),
                images.getTotalPages(),images.isLast());
    }

    public PagedResponse getAllImagesOfShop(long shopId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Image> images = imageRepository.findAllByShopId(shopId,pageable);

        if(images.getNumberOfElements() ==0){
            return new PagedResponse(Collections.emptyList(),images.getNumber(),images.getSize(),
                    images.getTotalElements(),images.getTotalPages(),images.isLast());
        }

        List<ImageResponse> imageResponseList = images.map(image -> {
            ImageResponse imageResponse = new ImageResponse();
            return imageResponse.imageResponseConvert(image);
        }).getContent();

        return new PagedResponse<>(imageResponseList,images.getNumber(),images.getSize(),images.getTotalElements(),
                images.getTotalPages(),images.isLast());
    }
}
