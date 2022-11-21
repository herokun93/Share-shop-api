package share.shop.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import share.shop.models.Shop;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop,Long> {
    Optional<Shop> findByUserId(Long id);
    Optional<Shop> findByName(String name);
}
