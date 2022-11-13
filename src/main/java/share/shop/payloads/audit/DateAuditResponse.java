package share.shop.payloads.audit;


import lombok.*;

import java.time.Instant;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateAuditResponse {
    private Instant createdAt;
    private Instant updatedAt;
}
