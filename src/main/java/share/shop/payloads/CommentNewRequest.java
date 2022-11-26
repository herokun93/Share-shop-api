package share.shop.payloads;

import lombok.*;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentNewRequest {
    private String comment;
    private Long productId;
}
