package share.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.shop.models.Shop;
import share.shop.repositories.ShopRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    public Optional<Shop> findById(Long id){return shopRepository.findById(id);};
    public Optional<Shop> findByUserId(Long id){return shopRepository.findByUserId(id);};
    public Optional<Shop> findByName(String name){return shopRepository.findByName(name);};
    public Shop save(Shop shop){return  shopRepository.save(shop);}
    public Shop saveAndFlush(Shop shop){return  shopRepository.saveAndFlush(shop);}
    public List<Shop> findByEmailOrName(String email, String name){return  shopRepository.findByEmailOrName(email,name);}
    public Optional<Shop>findByEmailAndName(String email, String name){return  shopRepository.findByEmailAndName(email,name);}


}
