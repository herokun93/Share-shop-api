package share.shop.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import share.shop.models.audit.DateAudit;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
@Setter
@Getter
public class User extends DateAudit {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;


    @NotBlank
    private String username;

    @NotBlank
    @Size(max = 100)
    private String password;

    private Instant birthday;
    private String mobile;
    private String address;
    private String avatar;


    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    private Instant createExpiry;
    private String createToken;
    private Instant resetExpiry;
    private String resetToken;






    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prefecture_id", referencedColumnName = "id")
    private Prefecture prefecture;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {

    }

    public User(String email,String username, String password) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public void newUser(String email, String username, String password){
        this.email = email;
        this.username = username;
        this.password = password;
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
        this.isEnabled = false;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="shop_id")
    private Shop shop;


    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Collection<Comment> comments;




}
