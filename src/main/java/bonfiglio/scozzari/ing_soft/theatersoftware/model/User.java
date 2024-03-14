package bonfiglio.scozzari.ing_soft.theatersoftware.model;

import bonfiglio.scozzari.ing_soft.theatersoftware.enumaration.UserRoles;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.audit.BaseEntityAudit;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.middle.UserAgency;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.middle.UserTheater;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.Updatable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntityAudit implements UserDetails, Updatable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoles role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)// mappedBy = "user" -> user è il nome del campo nella classe Artist
    private Artist artist;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)// mappedBy = "user" -> user è il nome del campo nella classe UserAgency
    private Set<UserAgency> userAgencies = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Where(clause = "deleted_at IS NULL")// mappedBy = "user" -> user è il nome del campo nella classe UserTheater
    private Set<UserTheater> userTheaters = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)// mappedBy = "user" -> user è il nome del campo nella classe Token
    private Set<Token> tokens = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void setUpdateAt(LocalDateTime updateAt) {
        this.setUpdatedAt(updateAt);
    }

}