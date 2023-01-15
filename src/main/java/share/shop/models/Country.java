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
@NamedEntityGraph(name="country",
        attributeNodes = {
                @NamedAttributeNode("name"),
                @NamedAttributeNode(value = "products", subgraph = "country.products"),
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "country.products",
                        attributeNodes = {
                                @NamedAttributeNode(value="tags")
                        }
                )
        }
)
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
    private Collection<Product> products;
}
