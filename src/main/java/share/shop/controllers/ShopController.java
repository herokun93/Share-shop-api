package share.shop.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import share.shop.exceptions.ResourceNotFoundException;
import share.shop.models.Role;
import share.shop.models.RoleName;
import share.shop.models.Shop;
import share.shop.models.User;
import share.shop.payloads.PagedResponse;
import share.shop.payloads.ShopInfoRequest;
import share.shop.payloads.ShopRegisterRequest;
import share.shop.securities.UserLogged;
import share.shop.services.ProductService;
import share.shop.services.RoleService;
import share.shop.services.ShopService;
import share.shop.services.UserService;
import share.shop.utils.AppConstants;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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
}

