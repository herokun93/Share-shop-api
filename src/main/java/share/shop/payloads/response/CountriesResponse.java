package share.shop.payloads.response;

import lombok.*;
import share.shop.models.Country;

import java.util.ArrayList;
import java.util.List;

public class CountriesResponse {
    private List<CountryResponse>  countriesResponse;

    public CountriesResponse(List<Country> countries) {
        countriesResponse = new ArrayList<>();
       countries.forEach(c->{
           CountryResponse countryResponse = new CountryResponse().countryConvert(c);
           countriesResponse.add(countryResponse);
       });
    }

    public List<CountryResponse> getCountriesResponse() {
        return countriesResponse;
    }
}
