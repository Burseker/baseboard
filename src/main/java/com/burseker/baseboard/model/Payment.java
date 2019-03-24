package com.burseker.baseboard.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "PAYMENTS")
@Entity
public class Payment {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACC_ID", nullable = false)
    protected Account account;

    @Column(nullable = false)
    protected String name;

    @Column(nullable = false)
    protected BigDecimal summ;
}
