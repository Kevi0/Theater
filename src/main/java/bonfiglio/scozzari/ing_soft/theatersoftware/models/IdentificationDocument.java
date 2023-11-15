package bonfiglio.scozzari.ing_soft.theatersoftware.models;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.enums.DocumentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdentificationDocument {

    @Id
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    private Document document;

    @Enumerated(EnumType.STRING)
    @Column
    private DocumentType documentType;

    @Column
    private String documentNumber;

    @Column
    private String issuingAuthority;

    @Column
    private Date expirationDate;

    @Column
    private Date releaseDate;
}
