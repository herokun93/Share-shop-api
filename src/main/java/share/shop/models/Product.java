package share.shop.models;

import lombok.*;
import share.shop.models.audit.UserDateAudit;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean hot;
    private int rating;
    private String description;
    private String descriptionSort;
    private String tiktok;
    private boolean enable;

    public Product(String name, boolean hot, int rating, String description, String descriptionSort, String tiktok, boolean enable) {
        this.name = name;
        this.hot = hot;
        this.rating = rating;
        this.description = description;
        this.descriptionSort = descriptionSort;
        this.tiktok = tiktok;
        this.enable = enable;
    }

    //
//    @ManyToOne(fetch = FetchType.LAZY,optional = false)
//    @JoinColumn(name="user_id")
//    private User user;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="country_id")
    private Country country;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="subCategory_id")
    private SubCategory subCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="shop_id")
    private Shop shop;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    private Collection<Tag> tags;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    private Collection<Image> images;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    private Collection<Comment> comments;

}
