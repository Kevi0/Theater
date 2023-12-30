package bonfiglio.scozzari.ing_soft.theatersoftware.models;

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
public class Invoice extends BaseEntityAudit {

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
    private String cap;

    @Column
    private String city;

    @Column
    private String taxCode;

    @Column
    private String stateOfCitizenship;

    @Column
    private String residence;

    @Column
    private String iban;

    @Column
    private String theater;

    @Column
    private String cityTheater;

    @Column
    private String venueTheater;

    @Column
    private String capTheater;

    @Column
    private String ivaTheater;

    @Column
    private String taxCodeTheater;

    @Column
    private String uniqueCode;

    @Column
    private String recipientCode;

    @Column
    private String opera;

    @Column
    private String role;

    @Column
    private Date startOpera;

    @Column
    private Integer dayExcept;

    @Column
    private Integer totalDays;

    @Column
    private String season;

    @Column
    private Boolean memberFrom;

    @Column
    private BigDecimal compensationNumber;

    @Column
    private BigDecimal inpsRate;

    @Column
    private BigDecimal taxable2;

    @Column
    private BigDecimal rate2;

    @Column
    private BigDecimal taxable3;

    @Column
    private BigDecimal rate3;

    @Column
    private BigDecimal rate4;

    @Column
    private BigDecimal rate5;

    @Column
    private BigDecimal net;

    @Column
    private Boolean stamp;
}
