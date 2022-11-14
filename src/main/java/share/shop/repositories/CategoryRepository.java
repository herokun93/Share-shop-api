package share.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import share.shop.models.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
        Category save(Category category);
        Optional<Category> findById(Long id);
        Optional<Category> findByName(String name);
        boolean existsByName(String name);
}
