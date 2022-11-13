package share.shop.payloads;

import lombok.*;
import org.modelmapper.ModelMapper;
import share.shop.models.Tag;
import share.shop.models.User;
import share.shop.payloads.audit.DateAuditResponse;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagResponse extends User {
    private Long id;
    private String name;
    private boolean enable;

    public TagResponse tagConvert(Tag tag){
        ModelMapper modelMapper = new ModelMapper();
        TagResponse tagResponse = modelMapper.map(tag, TagResponse.class);
        return  tagResponse;
    }
}
