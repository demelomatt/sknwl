package com.sknwl.shareknowledge.repositories.database.relational.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="donation")
public class DonationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_from_id", referencedColumnName = "id")
    private MemberModel from;

    @ManyToOne
    @JoinColumn(name = "member_to_id", referencedColumnName = "id")
    private MemberModel to;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "member_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "member_amount_currency_id"))
    })
    @AssociationOverride(name = "currency", joinColumns = @JoinColumn(name = "member_amount_currency_id"))
    private MoneyModel memberAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "system_fee")),
            @AttributeOverride(name = "currency", column = @Column(name = "system_fee_currency_id"))
    })
    @AssociationOverride(name = "currency", joinColumns = @JoinColumn(name = "system_fee_currency_id"))
    private MoneyModel systemFee;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "total")),
            @AttributeOverride(name = "currency", column = @Column(name = "total_currency_id"))
    })
    @AssociationOverride(name = "currency", joinColumns = @JoinColumn(name = "total_currency_id"))
    private MoneyModel total;
    private LocalDateTime executedDateTime;
}