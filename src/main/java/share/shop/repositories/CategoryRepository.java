package share.shop.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import share.shop.models.Category;
import share.shop.models.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
        Category save(Category category);

//        @Query(value = "DELETE FROM user_roles WHERE user_id = ?1 AND role_id =?2 ",nativeQuery = true)
//        @Modifying
//        @Transactional

        @Query(value = "SELECT c FROM Category c LEFT JOIN FETCH c.subCategories",nativeQuery = true)
        @Modifying
        @Transactional
        List<Category> test();

        Optional<Category> findById(Long id);
        Optional<Category> findByName(String name);
        List<Category> findAllByEnable(boolean enable);
        List<Category> findAllByEnable(Sort sort, boolean enable);

        boolean existsByName(String name);

}
