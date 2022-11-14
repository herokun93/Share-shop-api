package share.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.shop.models.Product;
import share.shop.repositories.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }
    public Product save(Product product){return productRepository.save(product);};
}
