package bonfiglio.scozzari.ing_soft.theatersoftware.model;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.audit.BaseEntityAudit;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.middle.UserAgency;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.Updatable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Agency extends BaseEntityAudit implements Updatable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String pec;

    @Column(nullable = false)
    private String tel1;

    @Column
    private String tel2;

    @Column
    private String website;

    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserAgency> userAgencies = new HashSet<>();

    @ManyToMany(mappedBy = "agencies", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Artist> artists = new HashSet<>();

    @Override
    public void setUpdateAt(LocalDateTime updateAt) {
        this.setUpdatedAt(updateAt);
    }

    @Override
    public String toString() {
        return "Agency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pec='" + pec + '\'' +
                ", tel1='" + tel1 + '\'' +
                ", tel2='" + tel2 + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}