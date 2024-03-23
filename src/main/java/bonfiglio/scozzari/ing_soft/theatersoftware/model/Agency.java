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

    @Column(nullable = false, unique = true, updatable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = true, unique = true)
    private String pec;

    @Column(nullable = false, unique = true)
    private String tel1;

    @Column(nullable = true, unique = true)
    private String tel2;

    @Column(nullable = true, unique = false)
    private String website;

    @OneToMany(mappedBy = "agency") // mappedBy = "agency" -> agency è il nome del campo nella classe UserAgency
    private Set<UserAgency> userAgencies = new HashSet<>();

    @ManyToMany(mappedBy = "agencies") // mappedBy = "agencies" -> agencies è il nome del campo nella classe Artist
    private Set<Artist> artists = new HashSet<>();

    @Override
    public void setUpdateAt(LocalDateTime updateAt) {
        this.setUpdatedAt(updateAt);
    }

}