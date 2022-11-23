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
import share.shop.payloads.*;
import share.shop.securities.UserLogged;
import share.shop.services.*;
import share.shop.utils.AppConstants;
import share.shop.utils.FileUploadUtil;
import share.shop.utils.ImageToUrl;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
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
        shop.setName(name);
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
        shop.setName(name);
        shop.setNumber(number);
        shop.setEmail(emailShop);
        shop.setAddress(address);


        if (Objects.isNull(shop)) return ResponseEntity.badRequest().build();

        shop = shopService.saveAndFlush(shop);

        if (Objects.isNull(shop)) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(null);
    }


    @GetMapping(value = "/shops/{id}/products")
    public PagedResponse getProductsShop(
            @Valid @PathVariable("id") Long shopId,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {

        return productService.getAllProductsForShop(shopId, page, size);
    }
    @GetMapping(value = "/shops/{name}/products")
    public PagedResponse getProductsShopName(
            @Valid @PathVariable("name") String shopName,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {

        Shop shop = shopService.findByName(shopName).orElseThrow(()->
                new ResourceNotFoundException("Shop","name",shopName));

        return productService.getAllProductsForShop(shop.getId(), page, size);
    }

    @PreAuthorize("hasRole('PARTNER')")
    @PostMapping(value = "/shops/images",consumes = {"multipart/form-data"})
    public ResponseEntity postImageByShopName(@ModelAttribute ShopImageRequest shopImageRequest) {

        List<String> fileList = new ArrayList<>();

        try {
            UserLogged userLogged = new UserLogged();
            String email = userLogged.getEmail();
            User user = userService.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "Email", ""));

            Shop shop = shopService.findByUserId(user.getId()).orElseThrow(()->new ResourceNotFoundException("Shop","id",user.getId()));

            System.out.println("Shop name " + shop.getId());

            MultipartFile[] files = shopImageRequest.getFiles();
            if(files.length%2==1) return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

            List<Image> imageList = new ArrayList<>();

            Image newImage = Image.builder()
                    .shop(shop)
                    .build();


            for(int i = 0; i< shopImageRequest.getFiles().length; i++){

                try {
                    String fileExtension = StringUtils.getFilenameExtension(files[i].getOriginalFilename());
                    String url = FileUploadUtil.saveFile(fileExtension,files[i],"/shops/"+shop.getId().toString());
                    fileList.add(ImageToUrl.toUrl(url));
                    newImage.setUrlSmall(url);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                i = i+1;
                try {
                    String fileExtension = StringUtils.getFilenameExtension(files[i].getOriginalFilename());

                    String url = FileUploadUtil.saveFile(fileExtension, files[i],"/shops/"+shop.getId().toString());
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

                ImageResponse imageResponse = new ImageResponse();

                //return  ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ErrorCode.CREATE_SUCCESS,fileList));
                return new ResponseEntity(imageResponse.imageResponseConvert(newImage), HttpStatus.OK);
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();

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
}

