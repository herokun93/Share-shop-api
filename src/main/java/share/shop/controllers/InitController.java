package share.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import share.shop.models.Product;
import share.shop.payloads.ProductCard;
import share.shop.services.CategoryService;
import share.shop.services.InitService;
import share.shop.services.ProductService;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class InitController {

    @Autowired
    private InitService initService;


    @GetMapping(value = "/init/home")
    public ResponseEntity getProductCard(){



//        Optional<Product> product = productService.findById(id);
//        ProductCard productCard = new ProductCard();
//        if(!product.isPresent()){
//            new ResponseEntity(null, HttpStatus.BAD_REQUEST);
//        }
        return ResponseEntity.ok(initService.home());
    }
}
