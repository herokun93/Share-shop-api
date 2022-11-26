package share.shop.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import share.shop.exceptions.ResourceNotFoundException;
import share.shop.models.*;
import share.shop.payloads.*;
import share.shop.securities.UserLogged;
import share.shop.services.*;
import share.shop.utils.AppConstants;
import share.shop.utils.FileUploadUtil;
import share.shop.utils.ImageToUrl;

import javax.validation.constraints.Min;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api")
@Slf4j
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

    @Autowired
    private ShopService shopService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/products/{id}")
    public ResponseEntity getProduct(@PathVariable("id") @Min(0) long id){
        Optional<Product> product = productService.findById(id);
        ProductDetails productDetails = new ProductDetails();
        if(!product.isPresent()){
            new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity(productDetails.productDetailsConvert(product.get()), HttpStatus.OK);
    }

    @GetMapping(value = "/products/{id}/card")
    public ResponseEntity getProductCard(@PathVariable("id") @Min(0) long id){
        Optional<Product> product = productService.findById(id);
        ProductCard productCard = new ProductCard();
        if(!product.isPresent()){
            new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity(productCard.productCardConvert(product.get()), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value="/products")
    @PreAuthorize("hasRole('PARTNER')")
    public ResponseEntity postProduct(@RequestBody ProductRequest productRequest){

        UserLogged userLogged = new UserLogged();
        String email = userLogged.getEmail();


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

        User user = userService.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User","email",email));

        Shop shopGet = shopService.findById(user.getShop().getId()).orElseThrow(()->new ResourceNotFoundException("Shop","id",user.getShop().getId()));

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
                .shop(shopGet)
                .build();


        if(Objects.isNull(productNew))  return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        productService.saveAndFlush(productNew);

        return new ResponseEntity(null, HttpStatus.OK);

    }

    @ResponseBody
    @PutMapping(value="/products")
    @PreAuthorize("hasRole('PARTNER')")
    public ResponseEntity putProduct(@RequestBody ProductEditRequest productEditRequest){

        log.info("Update product");

        String  name = productEditRequest.getName();
        boolean hot = productEditRequest.isHot();
        int rating = productEditRequest.getRating();
        String description = productEditRequest.getDescription();
        String descriptionSort = productEditRequest.getDescriptionSort();
        String tiktok = productEditRequest.getTiktok();
        boolean enable = productEditRequest.isEnable();
        long countryId = productEditRequest.getCountryId();
        long subCategoryId = productEditRequest.getSubCategoryId();
        long productId = productEditRequest.getProductId();

        UserLogged userLogged = new UserLogged();
        String email = userLogged.getEmail();
        User user = userService.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "Email", ""));

        Shop shopGet = shopService.findByUserId(user.getId()).orElseThrow(()->new ResourceNotFoundException("Shop","id",user.getId()));

        Product product = productService.findByShopIdAndId(shopGet.getId(),productId).orElseThrow(()->new ResourceNotFoundException("Product","id",productId));


        SubCategory subCategoryGet = subCategoryService.findById(subCategoryId)
                .orElseThrow(()->new ResourceNotFoundException("Product","subCategoryId",subCategoryId));

        Country countryGet = countryService.findById(countryId)
                .orElseThrow(()->new ResourceNotFoundException("Product","countryId",countryId));


        product.setName(name);
        product.setHot(hot);
        product.setRating(rating);
        product.setDescription(description);
        product.setDescriptionSort(descriptionSort);
        product.setTiktok(tiktok);
        product.setEnable(enable);
        product.setCountry(countryGet);
        product.setSubCategory(subCategoryGet);


        if(Objects.isNull(product))  return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        productService.saveAndFlush(product);

        return new ResponseEntity(null, HttpStatus.OK);

    }

    @GetMapping(value="/products")
    public PagedResponse getAllProducts(
                                      @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                      @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return productService.getProducts(page,size);
    }



    @GetMapping(value="/products/{id}/images")
    public PagedResponse getAllImagesOfProduct(
            @PathVariable("id") @Min(0) int productId,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return imageService.getAllImagesOfProduct(productId,page,size);
    }

    @GetMapping(value="/products/{id}/comments")
    public PagedResponse getAllCommentByProductId(
            @PathVariable("id") @Min(0) long productId,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return commentService.getAllCommentsByProductId(productId,page,size);
    }
}
