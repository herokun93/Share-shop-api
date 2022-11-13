package share.shop.payloads.audit;

import lombok.*;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDateAuditResponse extends DateAuditResponse{
    private Long createdBy;
    private Long updatedBy;
}
