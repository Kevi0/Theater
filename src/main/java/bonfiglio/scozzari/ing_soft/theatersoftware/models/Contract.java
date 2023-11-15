package bonfiglio.scozzari.ing_soft.theatersoftware.models;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.enums.Art5;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Documented;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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