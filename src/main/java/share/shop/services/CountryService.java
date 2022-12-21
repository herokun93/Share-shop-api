package share.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import share.shop.models.Country;
import share.shop.models.Product;
import share.shop.payloads.response.CountryResponse;
import share.shop.payloads.response.PagedResponse;
import share.shop.payloads.response.ProductCardResponse;
import share.shop.repositories.CountryRepository;
import share.shop.repositories.ProductRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ProductRepository productRepository;

    public Country save(Country country){
        return countryRepository.save(country);
    }
    public Optional<Country> findById(Long id){return countryRepository.findById(id);};
    public Optional<Country> findByName(String name){return countryRepository.findByName(name);};
    public List<Country> findAll(){return countryRepository.findAll();};

    public PagedResponse getAllCountries(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Country> countries = countryRepository.findAll(pageable);

        if(countries.getNumberOfElements() ==0){
            return new PagedResponse(Collections.emptyList(),countries.getNumber(),countries.getSize(),
                    countries.getTotalElements(),countries.getTotalPages(),countries.isLast());
        }

        List<CountryResponse> countryResponsePage = countries.map(country -> {
            CountryResponse countryResponse = new CountryResponse();
            return countryResponse.countryConvert(country);
        }).getContent();

        return new PagedResponse<>(countryResponsePage,countries.getNumber(),countries.getSize(),countries.getTotalElements(),
                countries.getTotalPages(),countries.isLast());
    }

    public PagedResponse getAllProductOfCountries(long countryId,int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAllByCountryId(countryId,pageable);

        if(products.getNumberOfElements() ==0){
            return new PagedResponse(Collections.emptyList(),products.getNumber(),products.getSize(),
                    products.getTotalElements(),products.getTotalPages(),products.isLast());
        }

        List<ProductCardResponse> productCardResponseList = products.map(subCategory -> {
            ProductCardResponse productCardResponse = new ProductCardResponse();
            return productCardResponse.productCardConvert(subCategory);
        }).getContent();

        return new PagedResponse<>(productCardResponseList,products.getNumber(),products.getSize(),products.getTotalElements(),
                products.getTotalPages(),products.isLast());
    }
}
