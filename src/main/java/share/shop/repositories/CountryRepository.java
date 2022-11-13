package share.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import share.shop.models.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
    Country save(Country country);
}
