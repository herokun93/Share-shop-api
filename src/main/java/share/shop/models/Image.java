package share.shop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import share.shop.models.audit.DateAudit;
import share.shop.models.audit.UserDateAudit;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String urlSmall;
    private String urlMedium;
    private int priority;


    @ManyToOne
    @JoinColumn(name="product_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Product product;


}
