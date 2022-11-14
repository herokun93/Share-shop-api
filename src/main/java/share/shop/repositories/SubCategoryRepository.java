package share.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import share.shop.models.SubCategory;

import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {
    SubCategory save(SubCategory subCategory);
    boolean existsByName(String name);
    Optional<SubCategory> findById(Long id);
}
