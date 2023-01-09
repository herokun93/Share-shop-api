package share.shop.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import share.shop.payloads.response.PagedResponse;
import share.shop.services.ProductService;
import share.shop.utils.AppConstants;

@RestController
@RequestMapping("/api")
@Slf4j
public class ProductModeController {

    @Autowired
    private ProductService productService;

    @GetMapping(value="/productModes/pr")
    public PagedResponse getModeProducts(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "mode", defaultValue = AppConstants.DEFAULT_PRODUCT_FEATURED_SIZE) long mode) {
        return productService.getAllProductsByProductModeAndEnable(mode,page,size,true);
    }
}
