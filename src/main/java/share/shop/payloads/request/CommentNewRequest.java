package share.shop.payloads.request;

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
