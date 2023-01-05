package share.shop.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.shop.models.ProductMode;
import share.shop.repositories.ProductModeRepository;

import java.util.List;

@Service
public class ProductModeService {

    @Autowired
    private ProductModeRepository productModeRepository;

    public List<ProductMode> findAdd(){
        return productModeRepository.findAll();
    }
}
