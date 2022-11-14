package share.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import share.shop.exceptions.ResourceNotFoundException;
import share.shop.models.*;
import share.shop.payloads.PagedResponse;
import share.shop.payloads.ProductCard;
import share.shop.payloads.ProductDetails;
import share.shop.payloads.ProductRequest;
import share.shop.services.*;
import share.shop.utils.AppConstants;
import share.shop.utils.FileUploadUtil;
import share.shop.utils.ImageToUrl;

import javax.validation.constraints.Min;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;


    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/products/{id}")
    public ResponseEntity getProduct(@PathVariable("id") @Min(0) long id){
        Optional<Product> product = productService.findById(id);
        ProductDetails productDetails = new ProductDetails();
        if(!product.isPresent()){
            new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity(productDetails.productDetailsConvert(product.get()), HttpStatus.OK);
    }

    @GetMapping("/products/{id}/card")
    public ResponseEntity getProductCard(@PathVariable("id") @Min(0) long id){
        Optional<Product> product = productService.findById(id);
        ProductCard productCard = new ProductCard();
        if(!product.isPresent()){
            new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity(productCard.productCardConvert(product.get()), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value="/products",consumes = {"multipart/form-data"})
    @PreAuthorize("hasAnyRole('ADMIN','PARTNER')")
    public ResponseEntity postProduct(@ModelAttribute ProductRequest productRequest){
        String  name = productRequest.getName();
        boolean hot = productRequest.isHot();
        int rating = productRequest.getRating();
        String description = productRequest.getDescription();
        String descriptionSort = productRequest.getDescriptionSort();
        String tiktok = productRequest.getTiktok();
        boolean enable = productRequest.isEnable();
        long countryId = productRequest.getCountryId();
        long subCategoryId = productRequest.getSubCategoryId();




        SubCategory subCategoryGet = subCategoryService.findById(subCategoryId)
                .orElseThrow(()->new ResourceNotFoundException("Product","subCategoryId",subCategoryId));

        Country countryGet = countryService.findById(countryId)
                .orElseThrow(()->new ResourceNotFoundException("Product","countryId",countryId));


        List<String> fileList = new ArrayList<>();
        MultipartFile[] files = productRequest.getFiles();


        String smallImageExtension;
        String mediumImageExtension;
        String smallImage;
        String mediumImage;
        String url ;
        if(files.length==2){

            smallImageExtension = StringUtils.getFilenameExtension(files[0].getOriginalFilename());
            mediumImageExtension = StringUtils.getFilenameExtension(files[1].getOriginalFilename());
            smallImage =  StringUtils.cleanPath(files[0].getOriginalFilename());
            mediumImage =  StringUtils.cleanPath(files[1].getOriginalFilename());

        }else{
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }

        Product productNew = Product.builder()
                .name(name)
                .hot(hot)
                .rating(rating)
                .description(description)
                .tiktok(tiktok)
                .enable(enable)
                .country(countryGet)
                .subCategory(subCategoryGet)
                .descriptionSort(descriptionSort)
                .build();


        if(Objects.isNull(productNew))  return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        productService.saveAndFlush(productNew);


        Image image = new Image();


        try {
            url = FileUploadUtil.saveFile(smallImageExtension, files[0],Long.toString(productNew.getId()));
            image.setUrlMedium(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            url = FileUploadUtil.saveFile(mediumImageExtension, files[1],Long.toString(productNew.getId()));
            image.setUrlSmall(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        image.setProduct(productNew);
        image.setPriority(1);
        imageService.save(image);

        fileList.add(ImageToUrl.toUrl(smallImage));
        fileList.add(ImageToUrl.toUrl(mediumImage));

        return new ResponseEntity(null, HttpStatus.OK);
    }

    @GetMapping(value="/products")
    public PagedResponse getAllProducts(
                                      @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                      @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return productService.getProducts(page,size);
    }

    @GetMapping(value="/products/created/{id}")
    public PagedResponse getAllProductsForCreated(
            @PathVariable("id") @Min(0) long createdId,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return productService.getAllProductsForCreated(createdId,page,size);
    }
}
