package share.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.shop.models.Shop;
import share.shop.repositories.ShopRepository;

import java.util.Optional;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    Optional<Shop> findById(Long id){return shopRepository.findById(id);};
    Optional<Shop> findByUserId(Long id){return shopRepository.findByUserId(id);};
    Optional<Shop> findByName(String name){return shopRepository.findByName(name);};
}
