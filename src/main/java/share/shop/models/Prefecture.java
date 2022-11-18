package share.shop.models;

import lombok.*;
import share.shop.models.audit.DateAudit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
public class Prefecture extends DateAudit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank()
    private String name;

    public Prefecture(String name) {
        this.name = name;
    }
}
