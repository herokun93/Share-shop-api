package share.shop.payloads.request;

import lombok.*;
import org.modelmapper.ModelMapper;
import share.shop.models.User;

import java.time.Instant;


@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    private Long id;
    private String email;
    private Instant birthday;
    private String mobile;
    private String address;
    private String avatar;
    private Long prefectureId;

    public UserProfile userProfileConvert(User user){
        ModelMapper modelMapper = new ModelMapper();
        UserProfile userProfile = modelMapper.map(user,UserProfile.class);
        return  userProfile;
    }

}
