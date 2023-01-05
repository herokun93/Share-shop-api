package share.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import share.shop.models.ProductMode;

@Repository
public interface ProductModeRepository extends JpaRepository<ProductMode,Long> {
}
