package share.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import share.shop.mapper.CountryMapper;
import share.shop.mapper.ProductCardMapper;
import share.shop.models.Country;
import share.shop.models.Product;
import share.shop.payloads.response.CountryResponse;
import share.shop.payloads.response.PagedResponse;
import share.shop.payloads.response.ProductCardResponse;
import share.shop.repositories.CountryRepository;
import share.shop.repositories.ProductRepository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Country save(Country country){

        return countryRepository.save(country);
    }

    public Optional<Country> findById(Long id){

        EntityGraph entityGraph = entityManager.getEntityGraph("country");

        Map<String, Object> properties = new HashMap<>();

        properties.put("javax.persistence.fetchgraph", entityGraph);

        Country country = entityManager.find(Country.class, id, properties);
        return countryRepository.findById(id);
    };

    public Country findByIdTest(Long id){

        EntityGraph entityGraph = entityManager.getEntityGraph("country.detail");

        Map<String, Object> properties = new HashMap<>();

        properties.put("javax.persistence.fetchgraph", entityGraph);

        Country country = entityManager.find(Country.class, id, properties);
        return country;
    };
    public Optional<Country> findByName(String name){return countryRepository.findByName(name);};
    public List<Country> findAll(){return countryRepository.findAll();};

    public PagedResponse getAllCountries(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);


        Page<Country> countries = countryRepository.findAll(pageable);

        if(countries.getNumberOfElements() ==0){
            return new PagedResponse(Collections.emptyList(),countries.getNumber(),countries.getSize(),
                    countries.getTotalElements(),countries.getTotalPages(),countries.isLast());
        }

        return new PagedResponse<>(CountryMapper.listCountry(countries.stream().toList()),countries.getNumber(),countries.getSize(),countries.getTotalElements(),
                countries.getTotalPages(),countries.isLast());
    }

    public PagedResponse getAllProductOfCountries(long countryId,int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAllByCountryId(countryId,pageable);

        if(products.getNumberOfElements() ==0){
            return new PagedResponse(Collections.emptyList(),products.getNumber(),products.getSize(),
                    products.getTotalElements(),products.getTotalPages(),products.isLast());
        }



        return new PagedResponse<>(ProductCardMapper.listConvert(products.stream().toList()),products.getNumber(),products.getSize(),products.getTotalElements(),
                products.getTotalPages(),products.isLast());
    }
}
