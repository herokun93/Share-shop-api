package share.shop.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import share.shop.models.Image;
import share.shop.models.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findById(Long id);
    Optional<Product>findByShopIdAndId(long shopId,long productId);
    Product save(Product product);
    Product saveAndFlush(Product product);

    Page<Product> findAllBySubCategoryId(long id, Pageable pageable);
    Page<Product> findAllBySlug(String slug, Pageable pageable);
    Page<Product> findAllByCountryId(Long id, Pageable pageable);
    Page<Product> findAllByCreatedBy(long id, Pageable pageable);
    Page<Product> findAllByShopId(long id, Pageable pageable);
    Page<Product> findAllByMode(int id, Pageable pageable);
    Page<Product> findAllByModeAndEnable(int id, Pageable pageable,boolean enable);
    List<Product> findByModeLessThanAndEnable(int mode,boolean enable);



}
