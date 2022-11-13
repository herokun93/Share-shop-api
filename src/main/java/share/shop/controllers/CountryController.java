package share.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import share.shop.models.Country;
import share.shop.payloads.CountryRequest;;
import share.shop.payloads.CountryResponse;
import share.shop.services.CountryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping("/countries")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> postCountry(@Valid @RequestBody CountryRequest countryRequest) {
        Country countryNew = Country.builder().name(countryRequest.getName()).build();
        Country country = countryService.save(countryNew);
        CountryResponse countryResponse = new CountryResponse();


        return new ResponseEntity(countryResponse.countryConvert(country), HttpStatus.OK);
    }
}
