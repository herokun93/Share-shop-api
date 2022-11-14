package share.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.shop.models.Image;
import share.shop.repositories.ImageRepository;

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

    public void deleteById(long id){
        imageRepository.deleteById(id);
    }
}
