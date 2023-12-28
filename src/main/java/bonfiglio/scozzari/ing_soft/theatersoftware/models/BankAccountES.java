package bonfiglio.scozzari.ing_soft.theatersoftware.models;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.audit.BaseEntity;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.audit.BaseEntityAudit;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.interfaces.BankAccount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountES extends BaseEntityAudit implements BankAccount{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String beneficiaryName;

    @Column
    private String iban;

    @Column
    private String bicSwift;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Artist artist;
}
