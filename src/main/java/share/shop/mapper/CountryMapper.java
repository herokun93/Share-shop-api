package share.shop.mapper;

import org.modelmapper.ModelMapper;
import share.shop.dto.CountryViewDto;
import share.shop.models.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryMapper {
    public static CountryViewDto convert(Country  country){

        ModelMapper modelMapper = new ModelMapper();

        CountryViewDto countryViewDto = modelMapper.map(country,CountryViewDto.class);
        return countryViewDto;
    }

    public static List<CountryViewDto> listCountry(List<Country> countryList){
        List<CountryViewDto> countryViewDtoList = new ArrayList<>();

        countryList.forEach(country -> {
            countryViewDtoList.add(convert(country));
        });
        return  countryViewDtoList;
    }
}
