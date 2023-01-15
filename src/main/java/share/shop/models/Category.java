package share.shop.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import share.shop.models.audit.DateAudit;
import share.shop.models.audit.UserDateAudit;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean enable;
    private String imageUrl;
    private String search;

    public Category(String name, boolean enable) {
        this.name = name;
        this.enable = enable;
    }

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    private Collection<SubCategory> subCategories;

    @PostLoad
    public void postLoad() {

    }
}
