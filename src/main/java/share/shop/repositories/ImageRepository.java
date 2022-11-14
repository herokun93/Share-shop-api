package share.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import share.shop.models.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {

    Image save(Image image);

    @Query(value = "SELECT * FROM image as im WHERE  im.url_small like %:name% OR im.url_medium like %:name%",nativeQuery = true)
    Image getImageByName(String name);

}
