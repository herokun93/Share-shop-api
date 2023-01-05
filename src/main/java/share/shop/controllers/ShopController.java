package share.shop.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import share.shop.exceptions.ResourceNotFoundException;
import share.shop.models.*;
import share.shop.payloads.request.*;
import share.shop.payloads.response.ImageResponse;
import share.shop.payloads.response.PagedResponse;
import share.shop.payloads.response.ShopInfoResponse;
import share.shop.securities.UserLogged;
import share.shop.services.*;
import share.shop.utils.AppConstants;
import share.shop.utils.FileUploadUtil;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class ShopController {

    @Autowired
    private UserService userService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private CountryService countryService;

    @ResponseBody
    @PostMapping(value = "/shops")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity postShop(@RequestBody @Valid ShopRegisterRequest shopRegisterRequest) {

        String name = shopRegisterRequest.getName();
        String number = shopRegisterRequest.getNumber();
        String email = shopRegisterRequest.getEmail();
        String address = shopRegisterRequest.getAddress();
        String userEmail = shopRegisterRequest.getUserEmail();
        boolean active = shopRegisterRequest.isActive();

        User user = userService.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));

        Optional<Shop> shopGet = shopService.findByEmailAndName(email, name);
        if (!shopGet.isEmpty()) return ResponseEntity.badRequest().build();


        Shop shop = new Shop();
        shop.setName(name.trim());
        shop.setNumber(number);
        shop.setEmail(email);
        shop.setAddress(address);
        shop.setUser(user);
        shop.setActive(active);

        if (Objects.isNull(shop)) return ResponseEntity.badRequest().build();

        shop = shopService.save(shop);

        if (Objects.isNull(shop)) return ResponseEntity.badRequest().build();

        Role roleEntity = roleService.findByName(RoleName.ROLE_PARTNER).orElseThrow(() ->
                new ResourceNotFoundException("Role", "name", RoleName.ROLE_PARTNER));

        if (user.getRoles().contains(RoleName.ROLE_PARTNER)) {
            return ResponseEntity.ok(null);
        } else {
            user.getRoles().add(roleEntity);
            userService.saveAndFlush(user);
        }

        return ResponseEntity.ok(null);
    }

    @ResponseBody
    @PutMapping(value = "/shops")
    @PreAuthorize("hasRole('PARTNER')")
    public ResponseEntity putShop(@RequestBody @Valid ShopInfoRequest shopInfoRequest) {


        String name = shopInfoRequest.getName();
        String number = shopInfoRequest.getNumber();
        String address = shopInfoRequest.getAddress();
        String emailShop = shopInfoRequest.getEmailShop();

        UserLogged userLogged = new UserLogged();
        String emailLogged = userLogged.getEmail();

        User user = userService.findByEmail(emailLogged).orElseThrow(() -> new ResourceNotFoundException("User", "Email", ""));


        List<Shop> shopList = shopService.findByEmailOrName(emailShop, name);
        if (shopList.size() > 0) return ResponseEntity.notFound().build();


        Shop shop = user.getShop();
        shop.setName(name.trim());
        shop.setNumber(number);
        shop.setEmail(emailShop);
        shop.setAddress(address);


        if (Objects.isNull(shop)) return ResponseEntity.badRequest().build();

        shop = shopService.saveAndFlush(shop);

        if (Objects.isNull(shop)) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(null);
    }

    @PreAuthorize("hasRole('PARTNER')")
    @GetMapping(value = "/shops/p/products")
    public PagedResponse getProductsOfShop(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        UserLogged userLogged = new UserLogged();
        String email = userLogged.getEmail();

        User user = userService.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "Email", ""));
        Long shopId  = user.getShop().getId();

        return productService.getAllProductsForShop(shopId, page, size);
    }

//    @PreAuthorize("hasRole('PARTNER')")
//    @GetMapping(value = "/shops/p/products/{id}")
//    public PagedResponse getAProductOfShop(
//            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
//            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
//        UserLogged userLogged = new UserLogged();
//        String email = userLogged.getEmail();
//
//        User user = userService.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "Email", ""));
//        Long shopId  = user.getShop().getId();
//
//        return productService.getAllProductsForShop(shopId, page, size);
//    }


    @GetMapping(value = "/shops/{id}/products")
    public PagedResponse getProductsByShopById(
            @Valid @PathVariable("id") Long shopId,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {

        return productService.getAllProductsForShop(shopId, page, size);
    }
    @GetMapping(value = "/shops/{name}/listProducts")
    public PagedResponse getProductsByShopName(
            @Valid @PathVariable("name") String shopName,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {

        Shop shop = shopService.findByName(shopName).orElseThrow(()->
                new ResourceNotFoundException("Shop","name",shopName));

        return productService.getAllProductsForShop(shop.getId(), page, size);
    }

    @PreAuthorize("hasRole('PARTNER')")
    @PostMapping(value = "/shops/images",consumes = {"multipart/form-data"})
    public ResponseEntity postImageByShopName( ShopImageRequest shopImageRequest) {



        try {
            UserLogged userLogged = new UserLogged();
            String email = userLogged.getEmail();
            User user = userService.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "Email", ""));
            Shop shop = shopService.findByUserId(user.getId()).orElseThrow(()->new ResourceNotFoundException("Shop","id",user.getId()));

                Image newImage = Image.builder()
                        .shop(shop)
                        .build();

            log.info("Upload image");

                String small = FileUploadUtil.saveFile("/shops/"+shop.getId().toString(),shopImageRequest.getFile(),shopImageRequest.getFile().getName());
                String medium = FileUploadUtil.saveFile("/shops/"+shop.getId().toString(),shopImageRequest.getFile(),shopImageRequest.getFile().getName());

                //String small = FileUploadUtil.resizeStart("/shops/"+shop.getId().toString(),shopImageRequest.getFiles()[i],300,300);
                //String medium = FileUploadUtil.resizeStart("/shops/"+shop.getId().toString(),shopImageRequest.getFiles()[i],900,900);
                if(small !=null && medium!=null)
                {
                    newImage.setUrlSmall(small);
                    newImage.setUrlMedium(medium);

                    newImage = imageService.save(newImage);
                    if(Objects.isNull(newImage)) return   ResponseEntity.badRequest().build();

                    return new ResponseEntity(new ImageResponse().imageResponseConvert(newImage), HttpStatus.OK);

                }

            return   ResponseEntity.badRequest().build();




//            for(int i = 0; i< shopImageRequest.getFiles().length; i++){
//
//                try {
//                    String fileExtension = StringUtils.getFilenameExtension(files[i].getOriginalFilename());
//                    String url = FileUploadUtil.saveFile(fileExtension,files[i],"/shops/"+shop.getId().toString());
//                    String resizePath = FileUploadUtil.resizeStart("/shops/"+shop.getId().toString(),shopImageRequest.getFiles()[1],400);
//                    System.out.println("resizePath: "+resizePath );
//                    fileList.add(ImageToUrl.toUrl(url));
//                    newImage.setUrlSmall(url);
//
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                i = i+1;
//                try {
//                    String fileExtension = StringUtils.getFilenameExtension(files[i].getOriginalFilename());
//
//                    String url = FileUploadUtil.saveFile(fileExtension, files[i],"/shops/"+shop.getId().toString());
//                    fileList.add(ImageToUrl.toUrl(url));
//                    newImage.setUrlMedium(url);
//                    imageList.add(newImage);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//            if(fileList.size()>0){
//                for(Image img :imageList){
//                    imageService.save(img);
//                }
//
//                ImageResponse imageResponse = new ImageResponse();
//                return new ResponseEntity(imageResponse.imageResponseConvert(newImage), HttpStatus.OK);
//            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }


    }


    @PreAuthorize("hasRole('PARTNER')")
    @DeleteMapping(value = "/shops/images/{id}")
    public ResponseEntity deleteImage(@Valid @PathVariable("id") Long imageId)
    {


        UserLogged userLogged = new UserLogged();
        String email = userLogged.getEmail();
        User user = userService.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "Email", ""));
        Shop shop = shopService.findByUserId(user.getId()).orElseThrow(()->new ResourceNotFoundException("Shop","id",user.getId()));
        long shopId = shop.getId();

        Image imageGet = imageService.findByShopIdAndId(shopId,imageId).orElseThrow(()->
                new ResourceNotFoundException("Image","Id",imageId));


       imageService.deleteById(imageId);

        return ResponseEntity.ok(null);

    }
    @PreAuthorize("hasRole('PARTNER')")
    @PutMapping(value = "/shops/images/{id}")
    public ResponseEntity putImageByShopName(
            @Valid @PathVariable("id") Long imageId,
            @RequestBody ImageSetProductRequest imageSetProductRequest)
    {
        Long productId = imageSetProductRequest.getProductId();
        int priority = imageSetProductRequest.getPriority();

        UserLogged userLogged = new UserLogged();
        String email = userLogged.getEmail();
        User user = userService.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "Email", ""));

        Shop shop = shopService.findByUserId(user.getId()).orElseThrow(()->new ResourceNotFoundException("Shop","id",user.getId()));

        Product product = productService.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product","id",productId));

        long shopId = shop.getId();

        Image imageGet = imageService.findByShopIdAndId(shopId,imageId).orElseThrow(()->
                new ResourceNotFoundException("Image","Id",imageId));

        imageGet.setProduct(product);
        imageGet.setPriority(priority);

        imageService.saveAndFlush(imageGet);

        return ResponseEntity.ok(new ImageResponse().imageResponseConvert(imageGet));

    }

    @PreAuthorize("hasRole('PARTNER')")
    @GetMapping(value = "/shops/images")
    public PagedResponse getImageByShopName(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size)
    {
        UserLogged userLogged = new UserLogged();
        String email = userLogged.getEmail();
        User user = userService.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "Email", ""));

        Shop shop = shopService.findByUserId(user.getId()).orElseThrow(()->new ResourceNotFoundException("Shop","id",user.getId()));

        long idShop = shop.getId();

        return imageService.getAllImagesOfShop(idShop,page,size);

    }

    @GetMapping(value = "/shops/{id}/information")
    public ResponseEntity getShopInfoById(@Valid @PathVariable("id") Long shopId)
    {
        Shop shop = shopService.findById(shopId).orElseThrow(
                ()->new ResourceNotFoundException("Shop","id",shopId));
        ShopInfoResponse shopInfoResponse = new ShopInfoResponse();
        return  ResponseEntity.ok(shopInfoResponse.shopInfoResponse(shop));
    }

    @GetMapping(value = "/shops/{name}/details")
    public ResponseEntity getShopInfoByName(@Valid @PathVariable("name") String shopName)
    {
        Shop shop = shopService.findByName(shopName).orElseThrow(
                ()->new ResourceNotFoundException("Shop","name",shopName));
        return  ResponseEntity.ok(new ShopInfoResponse().shopInfoResponse(shop));
    }

    @PreAuthorize("hasAnyRole('ADMIN','PARTNER')")
    @PostMapping(value= "/shops/product",consumes = {"multipart/form-data"})
    public ResponseEntity addProduct(@ModelAttribute ProductAndIRequest productAndIRequest) throws IOException {

        UserLogged userLogged = new UserLogged();
        String email = userLogged.getEmail();

        String  name = productAndIRequest.getName();
        String description = productAndIRequest.getDescription();
        String descriptionSort = productAndIRequest.getDescriptionSort();
        String tiktok = productAndIRequest.getTiktok();
        long countryId = Long.parseLong(productAndIRequest.getCountryId());
        long subCategoryId = Long.parseLong(productAndIRequest.getSubCategoryId());
        long price = Long.parseLong(productAndIRequest.getPrice());
        long salePrice = Long.parseLong(productAndIRequest.getSalePrice());

        SubCategory subCategoryGet = subCategoryService.findById(subCategoryId)
                .orElseThrow(()->new ResourceNotFoundException("Product","subCategoryId",subCategoryId));

        Country countryGet = countryService.findById(countryId)
                .orElseThrow(()->new ResourceNotFoundException("Product","countryId",countryId));

        User user = userService.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User","email",email));

        Shop shopGet = shopService.findById(user.getShop().getId()).orElseThrow(()->new ResourceNotFoundException("Shop","id",user.getShop().getId()));

        boolean sale = false;
        if(productAndIRequest.getSale().contains("true")){
            sale = true;
        }
        Product productNew = Product.builder()
                .name(name)
                .description(description)
                .tiktok(tiktok)
                .country(countryGet)
                .subCategory(subCategoryGet)
                .descriptionSort(descriptionSort)
                .shop(shopGet)
                .sale(sale)
                .price(price)
                .salePrice(salePrice)
                .build();





        if(Objects.isNull(productNew))  return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        Product iProduct = productService.save(productNew);


        List<Image> images = new ArrayList<>();
        for(int i = 0; i< productAndIRequest.getFiles().length; i++){
            Image newImage = Image.builder()
                    .shop(shopGet)
                    .build();

            MultipartFile multipartFile = productAndIRequest.getFiles()[i];

//            String small = FileUploadUtil.resizeStart("/shops/"+shopGet.getId().toString(),productAndIRequest.getFiles()[i],300,300);
//            String medium = FileUploadUtil.resizeStart("/shops/"+shopGet.getId().toString(),productAndIRequest.getFiles()[i],900,900);
            String small = FileUploadUtil.saveFile("/shops/"+shopGet.getId().toString(),multipartFile,multipartFile.getName());
            String medium = FileUploadUtil.saveFile("/shops/"+shopGet.getId().toString(),multipartFile,multipartFile.getName());
            if(small !=null && medium!=null){
                newImage.setUrlSmall(small);
                newImage.setUrlMedium(medium);
                newImage.setProduct(iProduct);
                images.add(newImage);
            }
        }

        images = imageService.saveAll(images);
        if(images.size()==0) return   ResponseEntity.badRequest().build();


        return new ResponseEntity(null, HttpStatus.OK);
    }
}

