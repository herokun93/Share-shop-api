package share.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.shop.models.Product;
import share.shop.repositories.ProductRepository;

@Service
@RequiredArgsConstructor
public class TelegramService {

    private final ProductRepository productRepository;

    public Product getProduct(Long id){return productRepository.findById(id).get();};
}
