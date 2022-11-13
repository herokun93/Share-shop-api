package share.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import share.shop.models.Image;
import share.shop.models.Product;
import share.shop.payloads.ImagesRequest;
import share.shop.services.ImageService;
import share.shop.services.ProductService;
import share.shop.utils.FileUploadUtil;
import share.shop.utils.ImageToUrl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ProductService productService;

    @PostMapping(value= "/images",consumes = {"multipart/form-data"})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity uploadImages(@ModelAttribute ImagesRequest imagesRequest) {

        List<String> fileList = new ArrayList<>();

        try {
            long productId = imagesRequest.getProductId();
            Optional<Product> product = productService.findById(productId);

            MultipartFile[] files = imagesRequest.getFiles();
            List<Image> imageList = new ArrayList<>();


            for(int i=0;i<imagesRequest.getFiles().length;i++){

                Image newImage = Image.builder()
                        .priority(0)
                        .product(product.get())
                        .build();

                try {
                    String fileExtension = StringUtils.getFilenameExtension(files[i].getOriginalFilename());
                    // String fileName = StringUtils.cleanPath(files[i].getOriginalFilename());
                    String url = FileUploadUtil.saveFile(fileExtension, files[i],Long.toString(productId));
                    fileList.add(ImageToUrl.toUrl(url));
                    newImage.setUrlSmall(url);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                i = i+1;
                try {
                    String fileExtension = StringUtils.getFilenameExtension(files[i].getOriginalFilename());
                    //String fileName = StringUtils.cleanPath(files[i].getOriginalFilename());

                    String url = FileUploadUtil.saveFile(fileExtension, files[i],Long.toString(productId));
                    fileList.add(ImageToUrl.toUrl(url));
                    newImage.setUrlMedium(url);
                    imageList.add(newImage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }

            if(fileList.size()>0){
                for(Image img :imageList){
                    imageService.save(img);
                }
                //return  ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ErrorCode.CREATE_SUCCESS,fileList));
                return new ResponseEntity(fileList, HttpStatus.OK);
            }

        } catch (Exception e) {}
        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

//    @GetMapping(value = "/images/getName/{name}", produces = MediaType.IMAGE_PNG_VALUE)
//    public @ResponseBody byte[] getFileByName(@PathVariable("name") String name) throws IOException {
//        //  InputStream in = getClass().getResourceAsStream("/image/xyz.png");
//
//        IImage iImage = imageService.getImageByName(name);
//        Path uploadPath;
//        if(iImage.getUrlMedium().indexOf(name)>-1){
//            uploadPath = Paths.get(iImage.getUrlMedium()) ;
//        }else uploadPath = Paths.get(iImage.getUrlSmall()) ;
//
//        InputStream out = Files.newInputStream(uploadPath);
//        return out.readAllBytes();
//    }
}