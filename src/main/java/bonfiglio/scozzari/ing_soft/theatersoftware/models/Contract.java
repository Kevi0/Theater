package bonfiglio.scozzari.ing_soft.theatersoftware.models;

import bonfiglio.scozzari.ing_soft.theatersoftware.enums.Art5;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.audit.BaseEntity;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.audit.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract {

    @Id
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    private Document document;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String birthPlace;

    @Column
    private Date birthDate;

    @Column
    private String taxCode;

    @Column
    private String iva;

    @Column
    private String stateOfCitizenship;

    @Column
    private String residence;

    @Column
    private String domicile;

    @Column
    private String email;

    @Column
    private String iban;

    @Column
    private String idDocument;

    @Column
    private Date expirationDateResidencePermit;

    @Column
    private String season;

    @Column
    private String opera;

    @Column
    private Date startOpera;

    @Column
    private String venue;

    @Column
    private Date startRehearsal;

    @Column
    private Integer dayExcept;

    @Column
    private Integer totalDays;

    @Column
    private String nameTheater;

    @Column
    private String cityTheater;

    @Column
    private String role;

    @Column
    private BigDecimal compensationNumber;

    @Column
    private String compensationLetter;

    @Enumerated(EnumType.STRING)
    @Column
    private Art5 art5;

    @Column
    private BigDecimal perDiem;

    @Column
    private BigDecimal accomodation;

    @Column
    private Boolean charged;

    @Column
    private Date issueDate;
}