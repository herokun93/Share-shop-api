package share.shop.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import share.shop.models.Category;
import share.shop.models.SubCategory;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
        Category save(Category category);
        Optional<Category> findById(Long id);
        Optional<Category> findByName(String name);
        List<Category> findAllByEnable(boolean enable);
        List<Category> findAllByEnable(Sort sort, boolean enable);
        boolean existsByName(String name);

}
