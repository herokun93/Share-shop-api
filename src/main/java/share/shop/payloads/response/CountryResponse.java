package share.shop.payloads.response;

import lombok.*;
import org.modelmapper.ModelMapper;
import share.shop.models.Country;
import share.shop.payloads.audit.DateAuditResponse;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryResponse extends DateAuditResponse {
    private Long id;
    private String name;

    public CountryResponse countryConvert(Country country){
        ModelMapper modelMapper = new ModelMapper();
        CountryResponse countryResponse = modelMapper.map(country, CountryResponse.class);
        return  countryResponse;
    }
}
