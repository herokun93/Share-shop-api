package share.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import share.shop.exceptions.ResourceNotFoundException;
import share.shop.models.Country;
;
import share.shop.payloads.request.CountryRequest;
import share.shop.payloads.response.ApiResponse;
import share.shop.payloads.response.CountryResponse;
import share.shop.payloads.response.PagedResponse;
import share.shop.services.CountryService;
import share.shop.utils.AppConstants;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping(value = "/countries")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> postCountry(@Valid @RequestBody CountryRequest countryRequest) {

        String name = countryRequest.getName();;

        Optional<Country> countryGet = countryService.findByName(name);
        if(countryGet.isPresent())
            return new ResponseEntity<>(new ApiResponse(false, "Country name already in use!"),
                    HttpStatus.BAD_REQUEST);

        Country countryNew = Country.builder().name(countryRequest.getName()).build();
        Country country = countryService.save(countryNew);

        if(Objects.isNull(country))
            return new ResponseEntity<>(new ApiResponse(false, "Cannot save country name"),
                    HttpStatus.BAD_REQUEST);

        CountryResponse countryResponse = new CountryResponse();
        return new ResponseEntity(countryResponse.countryConvert(country), HttpStatus.OK);
    }

    @PutMapping(value = "/countries/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> putCountry(
            @Valid  @PathVariable("id") @Min(0) Long countryId,
            @Valid @RequestBody CountryRequest countryRequest) {

        String name = countryRequest.getName();


        Country country = countryService.findById(countryId).orElseThrow(()-> {
            throw new ResourceNotFoundException("Country","id",countryId);});

        Optional<Country> countryGet =countryService.findByName(name);

        if(!countryGet.isEmpty())  return new ResponseEntity("Name is exist", HttpStatus.BAD_REQUEST);

        country.setName(name);
        country = countryService.save(country);

        if(Objects.isNull(country))  return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity(country, HttpStatus.OK);
    }

    @GetMapping(value="/countries")
    public PagedResponse getAllCountries(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return countryService.getAllCountries(page,size);
    }

    @GetMapping(value="/countries/{id}/product")
    public PagedResponse getAllProductOfCountries(
            @PathVariable("id") @Min(0) int countryId,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return countryService.getAllProductOfCountries(countryId,page,size);
    }
}
