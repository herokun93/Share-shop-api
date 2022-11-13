package share.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import share.shop.models.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {

    Image save(Image image);

    Image findByDirectorEndsWith(String director);

}
