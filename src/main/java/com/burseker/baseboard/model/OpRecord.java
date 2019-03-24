package com.burseker.baseboard.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "OPRECORDS")
@Entity
public class OpRecord {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String memo;

    @Column(nullable = false)
    protected BigDecimal summ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    protected Date createdOn;

    @Column(nullable = false)
    protected String srcAccount;

    @Column(nullable = false)
    protected String dstAccount;

    @Column
    protected String dstAliasName;

    public Long getId() {
        return id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public BigDecimal getSumm() {
        return summ;
    }

    public void setSumm(BigDecimal summ) {
        this.summ = summ;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getSrcAccount() {
        return srcAccount;
    }

    public void setSrcAccount(String srcAccount) {
        this.srcAccount = srcAccount;
    }

    public String getDstAccount() {
        return dstAccount;
    }

    public void setDstAccount(String dstAccount) {
        this.dstAccount = dstAccount;
    }

    public String getDstAliasName() {
        return dstAliasName;
    }

    public void setDstAliasName(String dstAliasName) {
        this.dstAliasName = dstAliasName;
    }
}
