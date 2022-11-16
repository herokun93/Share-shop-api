package share.shop.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import share.shop.models.Country;
import share.shop.models.Product;
import share.shop.models.SubCategory;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
    Country save(Country country);

    @Override
    Optional<Country> findById(Long aLong);

    Optional<Country> findByName(String name);


}
