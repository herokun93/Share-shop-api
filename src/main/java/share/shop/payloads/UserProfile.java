package share.shop.payloads;

import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.modelmapper.ModelMapper;
import share.shop.models.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private Long prefectureId;

    public UserProfile userProfileConvert(User user){
        ModelMapper modelMapper = new ModelMapper();
        UserProfile userProfile = modelMapper.map(user,UserProfile.class);
        return  userProfile;
    }

}
