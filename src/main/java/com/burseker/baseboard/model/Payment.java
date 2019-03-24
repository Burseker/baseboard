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

    public Long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSumm() {
        return summ;
    }

    public void setSumm(BigDecimal summ) {
        this.summ = summ;
    }
}
