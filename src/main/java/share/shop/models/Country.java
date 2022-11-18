package share.shop.models;

import lombok.*;
import share.shop.models.audit.DateAudit;
import share.shop.models.audit.UserDateAudit;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean enable;

    public Country(String name, boolean enable) {
        this.name = name;
        this.enable = enable;
    }

    @OneToMany(mappedBy = "country")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<Product> products;
}
