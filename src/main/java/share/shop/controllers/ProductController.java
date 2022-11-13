package share.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import share.shop.models.Product;
import share.shop.payloads.ProductInformation;
import share.shop.services.ProductService;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{id}")
    public ResponseEntity getProduct(@PathVariable("id") @Min(0) long id){
        Optional<Product> product = productService.findById(id);
        ProductInformation productInformation = new ProductInformation();
        if(!product.isPresent()){
            new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity(productInformation.productInformationConvert(product.get()), HttpStatus.OK);
    }
}
