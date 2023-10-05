package com.sknwl.shareknowledge.repositories.database.relational.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
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

    private BigDecimal amount;
    private BigDecimal systemFee;
    private BigDecimal total;
    private LocalDateTime executedDateTime;
}