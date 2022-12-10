package share.shop.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import share.shop.exceptions.ResourceNotFoundException;
import share.shop.models.Image;
import share.shop.securities.UserLogged;
import share.shop.services.ImageService;
import share.shop.services.ProductService;
import share.shop.services.ShopService;
import share.shop.services.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
@Slf4j
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShopService shopService;

//    @PostMapping(value= "/images",consumes = {"multipart/form-data"})
//    @PreAuthorize("hasAnyRole('ADMIN','PARTNER')")
//    public ResponseEntity uploadImages(@ModelAttribute ImagesProductRequest imagesProductRequest) {
//
//        List<String> fileList = new ArrayList<>();
//         int priority = imagesProductRequest.getPriority();
//
//        try {
//            long productId = imagesProductRequest.getProductId();
//            Optional<Product> product = productService.findById(productId);
//
//            UserLogged userLogged = new UserLogged();
//            String email = userLogged.getEmail();
//            User user = userService.findByEmail(email).orElseThrow(
//                    () -> new ResourceNotFoundException("User", "Email", ""));
//
//            Shop shop =user.getShop();
//
//
//            MultipartFile[] files = imagesProductRequest.getFiles();
//
//            if(files.length%2==1) return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
//
//            List<Image> imageList = new ArrayList<>();
//
//
//            Image newImage = Image.builder()
//                    .priority(priority)
//                    .product(product.get())
//                    .shop(shop)
//                    .build();
//
//
//            for(int i = 0; i< imagesProductRequest.getFiles().length; i++){
//
//
//
//                try {
//                    String fileExtension = StringUtils.getFilenameExtension(files[i].getOriginalFilename());
//                    // String fileName = StringUtils.cleanPath(files[i].getOriginalFilename());
//                    String url = FileUploadUtil.saveFile(fileExtension, files[i],Long.toString(productId));
//                    fileList.add(ImageToUrl.toUrl(url));
//                    newImage.setUrlSmall(url);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                i = i+1;
//                try {
//                    String fileExtension = StringUtils.getFilenameExtension(files[i].getOriginalFilename());
//                    //String fileName = StringUtils.cleanPath(files[i].getOriginalFilename());
//
//                    String url = FileUploadUtil.saveFile(fileExtension, files[i],Long.toString(productId));
//                    fileList.add(ImageToUrl.toUrl(url));
//                    newImage.setUrlMedium(url);
//                    imageList.add(newImage);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//
//            }
//
//            ImageResponse imageResponse = new ImageResponse();
//
//            if(fileList.size()>0){
//                for(Image img :imageList){
//                    imageService.save(img);
//                }
//                //return  ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ErrorCode.CREATE_SUCCESS,fileList));
//                return new ResponseEntity(imageResponse.imageResponseConvert(newImage), HttpStatus.OK);
//            }
//
//        } catch (Exception e) {}
//        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
//    }

    @GetMapping(value = "/images/{name}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getFileByName(@PathVariable("name") String name) throws IOException {
        //  InputStream in = getClass().getResourceAsStream("/image/xyz.png");
        name =  name;
        Image image = imageService.getImageByName(name);

        Path uploadPath;
        if(image.getUrlMedium().indexOf(name)>-1){
            uploadPath = Paths.get(image.getUrlMedium()) ;
        }else uploadPath = Paths.get(image.getUrlSmall()) ;

        InputStream out = Files.newInputStream(uploadPath);

        return out.readAllBytes();
    }

    @DeleteMapping(value= "/images/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PARTNER')")
    public ResponseEntity deleteImage(@PathVariable("id") Long imageId){


        Image imageGet = imageService.findById(imageId)
                .orElseThrow(()-> new ResourceNotFoundException("Image","id",imageId));

        UserLogged userLogged = new UserLogged();

        shopService.findByUserId(userLogged.getId()).
                orElseThrow(()->new ResourceNotFoundException("Shop","id",userLogged.getId()));

        imageService.deleteById(imageId);
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
