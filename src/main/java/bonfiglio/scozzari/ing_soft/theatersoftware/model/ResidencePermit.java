package bonfiglio.scozzari.ing_soft.theatersoftware.model;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.audit.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResidencePermit extends BaseEntityAudit {

    @Id
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    private Document document;

    @Column
    private String documentNumber;

    @Column
    private String issuingAuthority;

    @Column
    private Date expirationDate;

    @Column
    private String permitHolderName;

    @Column
    private String permitHolderSurname;

    @Column
    private String nationality;

    @Column
    private Date birthDate;

    @Column
    private String birthPlace;

    @Column
    private String residenceAddress;

    @Column
    private String permitCategory;

    @Column
    private Date permitStartDate;

    @Lob
    @Column
    private String additionalInfo;
}
