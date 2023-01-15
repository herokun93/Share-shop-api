package share.shop.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import share.shop.mapper.ProductMapper;
import share.shop.models.Product;
import share.shop.repositories.CountryRepository;
import share.shop.services.CountryService;
import share.shop.services.InitService;
import share.shop.services.ProductModeService;
import share.shop.services.ProductService;

@RestController
@RequestMapping("/api")
public class InitController {

    @Autowired
    private InitService initService;

    @Autowired
    private ProductModeService productModeService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/init/home2")
    public ResponseEntity getProductCard2(){

       // productService.getProductCardByShopId();
        //productService.getProductDetailsById(1);




        return ResponseEntity.ok(initService.home());
    }

    @GetMapping(value = "/init/xxx")
    public ResponseEntity xxx(){

        // productService.getProductCardByShopId();





        return ResponseEntity.ok(productService.getProductDetailsById(1));
    }

    @GetMapping(value = "/init/shop")
    public ResponseEntity getProductCardByShopId(){

        // productService.getProductCardByShopId();

//        long a = 1;
//        System.out.println(productService.findById(a).get().toString());





        return ResponseEntity.ok(productService.getProductCardByShopId(1,0,2));
    }

    @GetMapping(value = "/init/country")
    public ResponseEntity country(){
        long conuntryId =1;

        return ResponseEntity.ok(countryService.findByIdTest(conuntryId));
    }

    @GetMapping(value = "/init/product")
    public ResponseEntity product(){
        long productId =1;

        Product product = productService.findProductById(productId);
        System.out.println("get tag");

//        ProductCardDto productCardDto = modelMapper.map(product,ProductCardDto.class);
//        productCardDto.setImages(product.getImages().stream()
//                .map(images -> modelMapper.map(images,ImageDto.class)).collect(Collectors.toList()));
//
//        productCardDto.setTags(product.getTags().stream()
//                .map(tags -> modelMapper.map(tags, TagDto.class)).collect(Collectors.toList()));
//
//        productCardDto.setPrices(product.getPrices().stream()
//                .map(prices -> modelMapper.map(prices, PriceDto.class)).collect(Collectors.toList()));


       // new ProductCardResponse().productCardConvert(product

        return ResponseEntity.ok(ProductMapper.toDetail(product));
    }




    @GetMapping(value = "/init/home")
    public ResponseEntity getProductCard(){




//        Optional<Product> product = productService.findById(id);
//        ProductCard productCard = new ProductCard();
//        if(!product.isPresent()){
//            new ResponseEntity(null, HttpStatus.BAD_REQUEST);
//        }
        return ResponseEntity.ok(initService.home());
    }
//    @PreAuthorize("hasAnyRole('PARTNER','ADMIN')")
//    @GetMapping(value = "/init/newProduct")
//    public ResponseEntity newProduct(@RequestBody PartnerRequest partnerRequest){
//        return ResponseEntity.ok(initService.newProduct());
//    }

    @PreAuthorize("hasAnyRole('PARTNER','ADMIN')")
    @GetMapping(value = "/init/newProduct")
    public ResponseEntity newProduct(){
        return ResponseEntity.ok(initService.newProduct());
    }
}
