package share.shop.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import share.shop.models.audit.UserDateAudit;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shop extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String number;
    private String email;
    private String address;
    private String telegramId;
    private boolean active;

    @OneToMany(mappedBy = "shop",fetch = FetchType.LAZY)
    private Collection<Product> products;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "shop",fetch = FetchType.LAZY)
    private Collection<Image> images;
}
