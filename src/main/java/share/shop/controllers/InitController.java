package share.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import share.shop.payloads.request.init.PartnerRequest;
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

    @GetMapping(value = "/init/home2")
    public ResponseEntity getProductCard2(){

       // productService.getProductCardByShopId();
        productService.getProductDetailsById();




        return ResponseEntity.ok(initService.home());
    }

    @GetMapping(value = "/init/xxx")
    public ResponseEntity xxx(){

        // productService.getProductCardByShopId();
        productService.getProductDetailsById();




        return ResponseEntity.ok(productService.getProductDetailsById());
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
