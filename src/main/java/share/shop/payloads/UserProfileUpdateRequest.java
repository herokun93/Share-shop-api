package share.shop.payloads;

import lombok.*;

import java.time.Instant;


@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileUpdateRequest {
    private Instant birthday;
    private String mobile;
    private String address;
    private String avatar;
    private Long prefectureId;
}
