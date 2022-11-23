package share.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import share.shop.models.Product;
import share.shop.payloads.PagedResponse;
import share.shop.payloads.ProductCard;
import share.shop.repositories.ProductRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }
    public Product save(Product product){return productRepository.save(product);};

    public Product saveAndFlush(Product product){return productRepository.saveAndFlush(product);};

    public PagedResponse getProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Product> products = productRepository.findAll(pageable);

        if(products.getNumberOfElements()==0){
            return new PagedResponse<>(Collections.emptyList(), products.getNumber(), products.getSize(),
                    products.getTotalElements(), products.getTotalPages(), products.isLast());
        }



        List<ProductCard> productCardListPage = products.map(product -> {
            ProductCard productCard = new ProductCard();
            return  productCard.productCardConvert(product);
        }).getContent();

        return new PagedResponse<>(productCardListPage, products.getNumber(), products.getSize(), products.getTotalElements(),
                products.getTotalPages(), products.isLast());
    }

    public PagedResponse getAllProductsForCreated(Long createdId,int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Product> products = productRepository.findAllByCreatedBy(createdId,pageable);

        if(products.getNumberOfElements()==0){
            return new PagedResponse<>(Collections.emptyList(), products.getNumber(), products.getSize(),
                    products.getTotalElements(), products.getTotalPages(), products.isLast());
        }



        List<ProductCard> productCardListPage = products.map(product -> {
            ProductCard productCard = new ProductCard();
            return  productCard.productCardConvert(product);
        }).getContent();

        return new PagedResponse<>(productCardListPage, products.getNumber(), products.getSize(), products.getTotalElements(),
                products.getTotalPages(), products.isLast());
    }

    public PagedResponse getAllProductsForShop(Long shopId,int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAllByShopId(shopId,pageable);

        if(products.getNumberOfElements()==0){
            return new PagedResponse<>(Collections.emptyList(), products.getNumber(), products.getSize(),
                    products.getTotalElements(), products.getTotalPages(), products.isLast());
        }



        List<ProductCard> productCardListPage = products.map(product -> {
            ProductCard productCard = new ProductCard();
            return  productCard.productCardConvert(product);
        }).getContent();

        return new PagedResponse<>(productCardListPage, products.getNumber(), products.getSize(), products.getTotalElements(),
                products.getTotalPages(), products.isLast());
    }


}
