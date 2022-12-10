package share.shop.payloads.response;

import lombok.*;
import org.modelmapper.ModelMapper;
import share.shop.models.Tag;
import share.shop.payloads.audit.DateAuditResponse;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagResponse extends DateAuditResponse {
    private Long id;
    private String name;
    private boolean enable;

    public TagResponse tagConvert(Tag tag){
        ModelMapper modelMapper = new ModelMapper();
        TagResponse tagResponse = modelMapper.map(tag, TagResponse.class);
        return  tagResponse;
    }
}
