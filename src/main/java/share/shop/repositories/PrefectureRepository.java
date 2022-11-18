package share.shop.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import share.shop.models.Prefecture;

@Repository
public interface PrefectureRepository extends JpaRepository<Prefecture,Long> {
}
