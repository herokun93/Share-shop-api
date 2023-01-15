package share.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import share.shop.models.Product;
import share.shop.models.Tag;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {


    Tag save(Tag tag);

    Optional<Tag> findById(Long id);

    Optional<Tag> findByName(String name);

    boolean existsByName(String name);

    @Query(value = "INSERT INTO product_tags(products_id, tags_id) VALUES (?2, ?1)",nativeQuery = true)
    @Modifying
    @Transactional
    void addTagForProduct(Long tagId,Long productId);
}
